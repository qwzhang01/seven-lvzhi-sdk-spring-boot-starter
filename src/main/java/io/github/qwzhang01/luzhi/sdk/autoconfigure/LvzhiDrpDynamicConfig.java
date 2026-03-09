package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import org.springframework.stereotype.Component;

/**
 * 旅智分销平台动态配置类
 * 用于支持通过数据库或其他方式动态注入配置参数
 *
 * @author lvzhi-drp-starter
 */
@Component
public class LvzhiDrpDynamicConfig {

    /**
     * API基础URL
     */
    private String baseUrl;

    /**
     * 客户端ID（应用ID/客户端用户名）
     */
    private String clientId;

    /**
     * 客户端密钥（应用秘钥/客户端密码）
     */
    private String clientSecret;

    /**
     * 签名密钥（用于生成签名）
     */
    private String secretKey;

    /**
     * API版本号
     */
    private String version;

    public LvzhiDrpDynamicConfig() {
    }

    public LvzhiDrpDynamicConfig(String baseUrl, String clientId, String clientSecret, String secretKey, String version) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.secretKey = secretKey;
        this.version = version;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LvzhiDrpDynamicConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}