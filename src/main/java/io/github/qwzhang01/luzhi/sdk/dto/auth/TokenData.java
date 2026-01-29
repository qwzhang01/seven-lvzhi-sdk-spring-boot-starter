package io.github.qwzhang01.luzhi.sdk.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Token数据
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenData {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 过期时间戳（毫秒）
     */
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
