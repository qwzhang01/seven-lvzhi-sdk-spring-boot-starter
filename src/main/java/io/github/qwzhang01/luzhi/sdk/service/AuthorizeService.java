package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.auth.GetTokenRequest;
import io.github.qwzhang01.luzhi.sdk.dto.auth.TokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 授权服务
 * <p>
 * 对应API分组：授权接口
 *
 * @author lvzhi-drp-starter
 */
public class AuthorizeService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthorizeService.class);

    private final LvzhiDrpClient client;

    public AuthorizeService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 获取Token
     * <p>
     * 注意：通常不需要手动调用此方法，LvzhiDrpClient会自动管理Token
     *
     * @param clientId     客户端ID
     * @param clientSecret 客户端密钥
     * @return Token数据
     */
    public BaseResponse<TokenData> getToken(String clientId,
                                            String clientSecret) {
        logger.debug("获取Token, clientId: {}", clientId);

        GetTokenRequest request = new GetTokenRequest();
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        return client.executeWithoutToken(
                ApiMethod.AUTHORIZE_GET_TOKEN,
                request,
                new TypeReference<BaseResponse<TokenData>>() {
                }
        );
    }

    /**
     * 获取当前有效的访问令牌
     *
     * @return 访问令牌
     */
    public String getValidAccessToken() {
        return client.getValidAccessToken();
    }

    /**
     * 清除Token缓存，强制下次请求时重新获取Token
     */
    public void clearTokenCache() {
        logger.debug("清除Token缓存");
        client.clearTokenCache();
    }
}
