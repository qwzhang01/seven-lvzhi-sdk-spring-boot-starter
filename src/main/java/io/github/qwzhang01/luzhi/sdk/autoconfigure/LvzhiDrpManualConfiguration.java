package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 旅智分销平台API手动配置工具类
 * <p>
 * 提供不依赖yml配置的注入方式，支持内存缓存和Redis缓存两种模式
 *
 * @author lvzhi-drp-starter
 */
public class LvzhiDrpManualConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(LvzhiDrpManualConfiguration.class);

    /**
     * 创建默认HTTP客户端
     */
    public static CloseableHttpClient createDefaultHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(50);
        
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }

    /**
     * 创建自定义HTTP客户端
     */
    public static CloseableHttpClient createCustomHttpClient(int maxConnTotal, int maxConnPerRoute, 
                                                             int connectTimeout, int readTimeout) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxConnTotal);
        connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);
        
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectTimeout, java.util.concurrent.TimeUnit.MILLISECONDS)
                .setConnectTimeout(connectTimeout, java.util.concurrent.TimeUnit.MILLISECONDS)
                .setResponseTimeout(readTimeout, java.util.concurrent.TimeUnit.MILLISECONDS)
                .build();
        
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 创建使用内存缓存的API客户端（基础版本）
     */
    public static LvzhiDrpClient createMemoryCacheClient(String baseUrl, String clientId, 
                                                        String clientSecret, String secretKey) {
        return createMemoryCacheClient(baseUrl, clientId, clientSecret, secretKey, "V1.0.0");
    }

    /**
     * 创建使用内存缓存的API客户端（完整版本）
     */
    public static LvzhiDrpClient createMemoryCacheClient(String baseUrl, String clientId, 
                                                        String clientSecret, String secretKey, String version) {
        CloseableHttpClient httpClient = createDefaultHttpClient();
        return new LvzhiDrpClient(httpClient, baseUrl, clientId, clientSecret, secretKey, version);
    }

    /**
     * 创建使用内存缓存的API客户端（自定义HTTP客户端）
     */
    public static LvzhiDrpClient createMemoryCacheClient(CloseableHttpClient httpClient, 
                                                        String baseUrl, String clientId, 
                                                        String clientSecret, String secretKey) {
        return new LvzhiDrpClient(httpClient, baseUrl, clientId, clientSecret, secretKey, "V1.0.0");
    }

    /**
     * 创建使用Redis缓存的API客户端
     */
    public static LvzhiDrpClient createRedisCacheClient(String baseUrl, String clientId, 
                                                       String clientSecret, String secretKey,
                                                       RedisTemplate<String, Object> redisTemplate) {
        return createRedisCacheClient(baseUrl, clientId, clientSecret, secretKey, "V1.0.0", redisTemplate);
    }

    /**
     * 创建使用Redis缓存的API客户端（完整版本）
     */
    public static LvzhiDrpClient createRedisCacheClient(String baseUrl, String clientId, 
                                                       String clientSecret, String secretKey, String version,
                                                       RedisTemplate<String, Object> redisTemplate) {
        CloseableHttpClient httpClient = createDefaultHttpClient();
        
        LvzhiDrpProperties properties = new LvzhiDrpProperties();
        properties.setBaseUrl(baseUrl);
        properties.setClientId(clientId);
        properties.setClientSecret(clientSecret);
        properties.setSecretKey(secretKey);
        properties.setVersion(version);
        properties.getRedisCache().setEnabled(true);
        
        return new LvzhiDrpClient(httpClient, properties, redisTemplate);
    }

    /**
     * 创建使用Redis缓存的API客户端（自定义HTTP客户端）
     */
    public static LvzhiDrpClient createRedisCacheClient(CloseableHttpClient httpClient, 
                                                       String baseUrl, String clientId, 
                                                       String clientSecret, String secretKey,
                                                       RedisTemplate<String, Object> redisTemplate) {
        LvzhiDrpProperties properties = new LvzhiDrpProperties();
        properties.setBaseUrl(baseUrl);
        properties.setClientId(clientId);
        properties.setClientSecret(clientSecret);
        properties.setSecretKey(secretKey);
        properties.setVersion("V1.0.0");
        properties.getRedisCache().setEnabled(true);
        
        return new LvzhiDrpClient(httpClient, properties, redisTemplate);
    }

    /**
     * 创建基于Properties对象的API客户端（自动选择缓存策略）
     */
    public static LvzhiDrpClient createClientFromProperties(LvzhiDrpProperties properties,
                                                            RedisTemplate<String, Object> redisTemplate) {
        CloseableHttpClient httpClient = createDefaultHttpClient();
        
        if (properties.getRedisCache().isEnabled() && redisTemplate != null) {
            logger.info("手动创建旅智DRP API客户端（使用Redis缓存）");
            return new LvzhiDrpClient(httpClient, properties, redisTemplate);
        } else {
            logger.info("手动创建旅智DRP API客户端（使用内存缓存）");
            return new LvzhiDrpClient(httpClient, properties);
        }
    }

    /**
     * 创建基于Properties对象的API客户端（自定义HTTP客户端）
     */
    public static LvzhiDrpClient createClientFromProperties(CloseableHttpClient httpClient,
                                                            LvzhiDrpProperties properties,
                                                            RedisTemplate<String, Object> redisTemplate) {
        if (properties.getRedisCache().isEnabled() && redisTemplate != null) {
            logger.info("手动创建旅智DRP API客户端（使用Redis缓存）");
            return new LvzhiDrpClient(httpClient, properties, redisTemplate);
        } else {
            logger.info("手动创建旅智DRP API客户端（使用内存缓存）");
            return new LvzhiDrpClient(httpClient, properties);
        }
    }
}