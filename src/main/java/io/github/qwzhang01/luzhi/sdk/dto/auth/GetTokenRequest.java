package io.github.qwzhang01.luzhi.sdk.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 获取Token请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTokenRequest {

    /**
     * 应用ID/客户端用户名
     */
    private String clientId;

    /**
     * 应用秘钥/客户端密码
     */
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
