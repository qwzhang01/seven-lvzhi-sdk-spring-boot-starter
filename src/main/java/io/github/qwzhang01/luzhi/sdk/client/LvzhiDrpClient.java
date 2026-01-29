package io.github.qwzhang01.luzhi.sdk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.qwzhang01.luzhi.sdk.autoconfigure.LvzhiDrpProperties;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.auth.GetTokenRequest;
import io.github.qwzhang01.luzhi.sdk.dto.auth.TokenData;
import io.github.qwzhang01.luzhi.sdk.exception.LvzhiDrpException;
import io.github.qwzhang01.luzhi.sdk.util.SignatureUtil;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 旅智分销平台API客户端
 * <p>
 * 负责处理HTTP请求、签名、Token管理等底层功能
 *
 * @author lvzhi-drp-starter
 */
public class LvzhiDrpClient {

    private static final Logger logger =
            LoggerFactory.getLogger(LvzhiDrpClient.class);
    // Token提前5分钟刷新
    private static final long TOKEN_REFRESH_BUFFER = 5 * 60 * 1000;
    private final CloseableHttpClient httpClient;
    private final LvzhiDrpProperties properties;
    private final ObjectMapper objectMapper;
    private final ReentrantLock tokenLock = new ReentrantLock();
    private volatile String accessToken;
    private volatile long tokenExpireTime;

    public LvzhiDrpClient(CloseableHttpClient httpClient,
                          LvzhiDrpProperties properties) {
        this.httpClient = httpClient;
        this.properties = properties;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * 获取有效的访问令牌
     *
     * @return 访问令牌
     */
    public String getValidAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < tokenExpireTime - TOKEN_REFRESH_BUFFER) {
            return accessToken;
        }

        tokenLock.lock();
        try {
            // 双重检查
            if (accessToken != null && System.currentTimeMillis() < tokenExpireTime - TOKEN_REFRESH_BUFFER) {
                return accessToken;
            }

            // 刷新Token
            TokenData tokenData = refreshToken();
            this.accessToken = tokenData.getAccessToken();
            this.tokenExpireTime = tokenData.getExpiresIn();

            logger.info("Token刷新成功，过期时间戳: {}", tokenExpireTime);
            return accessToken;
        } finally {
            tokenLock.unlock();
        }
    }

    /**
     * 刷新Token
     */
    private TokenData refreshToken() {
        GetTokenRequest request = new GetTokenRequest();
        request.setClientId(properties.getClientId());
        request.setClientSecret(properties.getClientSecret());

        BaseResponse<TokenData> response = executeWithoutToken(
                ApiMethod.AUTHORIZE_GET_TOKEN,
                request,
                new TypeReference<BaseResponse<TokenData>>() {
                }
        );

        if (!response.isSuccess()) {
            throw new LvzhiDrpException(response.getCode(),
                    "获取Token失败: " + response.getMessage());
        }

        return response.getData();
    }

    /**
     * 执行API请求（自动管理Token）
     *
     * @param apiMethod    API方法
     * @param request      请求对象
     * @param responseType 响应类型
     * @param <T>          请求类型
     * @param <R>          响应数据类型
     * @return 响应对象
     */
    public <T, R> BaseResponse<R> execute(ApiMethod apiMethod, T request,
                                          TypeReference<BaseResponse<R>> responseType) {
        String token = getValidAccessToken();
        return executeWithToken(apiMethod, request, token, responseType);
    }

    /**
     * 执行API请求（指定Token）
     */
    public <T, R> BaseResponse<R> executeWithToken(ApiMethod apiMethod,
                                                   T request, String token,
                                                   TypeReference<BaseResponse<R>> responseType) {
        String url = properties.getBaseUrl() + apiMethod.getUrl();
        String trackingId = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();

        // 生成签名
        String signature = SignatureUtil.generateSignature(
                apiMethod.getUrl(),
                properties.getSecretKey(),
                trackingId,
                apiMethod.getMethod(),
                timestamp,
                properties.getVersion()
        );

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("trackingId", trackingId);
        requestBody.put("version", properties.getVersion());
        requestBody.put("timestamp", timestamp);
        requestBody.put("signature", signature);

        // 合并业务参数
        if (request != null) {
            try {
                Map<String, Object> businessParams =
                        objectMapper.convertValue(request,
                                new TypeReference<Map<String, Object>>() {
                                });
                requestBody.putAll(businessParams);
            } catch (Exception e) {
                throw new LvzhiDrpException("序列化请求参数失败", e);
            }
        }

        return doPost(url, requestBody, token, responseType);
    }

    /**
     * 执行API请求（不带Token，用于获取Token）
     */
    public <T, R> BaseResponse<R> executeWithoutToken(ApiMethod apiMethod,
                                                      T request,
                                                      TypeReference<BaseResponse<R>> responseType) {
        String url = properties.getBaseUrl() + apiMethod.getUrl();
        String trackingId = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();

        // 生成签名
        String signature = SignatureUtil.generateSignature(
                apiMethod.getUrl(),
                properties.getSecretKey(),
                trackingId,
                apiMethod.getMethod(),
                timestamp,
                properties.getVersion()
        );

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("trackingId", trackingId);
        requestBody.put("version", properties.getVersion());
        requestBody.put("timestamp", timestamp);
        requestBody.put("signature", signature);

        // 合并业务参数
        if (request != null) {
            try {
                Map<String, Object> businessParams =
                        objectMapper.convertValue(request,
                                new TypeReference<Map<String, Object>>() {
                                });
                requestBody.putAll(businessParams);
            } catch (Exception e) {
                throw new LvzhiDrpException("序列化请求参数失败", e);
            }
        }

        return doPost(url, requestBody, null, responseType);
    }

    /**
     * 执行POST请求
     */
    private <R> BaseResponse<R> doPost(String url,
                                       Map<String, Object> requestBody,
                                       String token,
                                       TypeReference<BaseResponse<R>> responseType) {
        try {
            String jsonBody = objectMapper.writeValueAsString(requestBody);
            logger.debug("请求URL: {}", url);
            logger.debug("请求体: {}", jsonBody);

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(jsonBody,
                    ContentType.APPLICATION_JSON));
            httpPost.setHeader("Content-Type", "application/json;" +
                    "charset=UTF-8");

            if (token != null) {
                httpPost.setHeader("x-acs-titc-access-token", token);
            }

            return httpClient.execute(httpPost, response -> {
                int statusCode = response.getCode();
                String responseBody =
                        EntityUtils.toString(response.getEntity(),
                                StandardCharsets.UTF_8);

                logger.debug("响应状态码: {}", statusCode);
                logger.debug("响应体: {}", responseBody);

                if (statusCode != 200) {
                    throw new LvzhiDrpException("HTTP请求失败，状态码: " + statusCode + ", 响应: " + responseBody);
                }

                try {
                    return objectMapper.readValue(responseBody, responseType);
                } catch (JsonProcessingException e) {
                    throw new LvzhiDrpException("解析响应失败: " + responseBody, e);
                }
            });
        } catch (LvzhiDrpException e) {
            throw e;
        } catch (Exception e) {
            throw new LvzhiDrpException("API请求失败: " + e.getMessage(), e);
        }
    }

    /**
     * 清除Token缓存（用于测试或强制刷新）
     */
    public void clearTokenCache() {
        tokenLock.lock();
        try {
            this.accessToken = null;
            this.tokenExpireTime = 0;
        } finally {
            tokenLock.unlock();
        }
    }
}
