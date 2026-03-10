package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 旅智分销平台API配置属性
 *
 * @author lvzhi-drp-starter
 */
@ConfigurationProperties(prefix = "lvzhi.drp")
public class LvzhiDrpProperties {

    /**
     * API基础URL
     */
    private String baseUrl = "https://open.zktapi.com/drp";

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
    private String version = "V1.0.0";

    /**
     * HTTP客户端配置
     */
    private HttpClientProperties httpClient = new HttpClientProperties();

    /**
     * Redis缓存配置
     */
    private RedisCacheProperties redisCache = new RedisCacheProperties();

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

    public HttpClientProperties getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClientProperties httpClient) {
        this.httpClient = httpClient;
    }

    public RedisCacheProperties getRedisCache() {
        return redisCache;
    }

    public void setRedisCache(RedisCacheProperties redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * HTTP客户端配置
     */
    public static class HttpClientProperties {
        /**
         * 最大连接数
         */
        private int maxConnTotal = 200;

        /**
         * 每个路由最大连接数
         */
        private int maxConnPerRoute = 50;

        /**
         * 连接超时时间（毫秒）
         */
        private int connectTimeout = 5000;

        /**
         * 读取超时时间（毫秒）
         */
        private int readTimeout = 30000;

        /**
         * 连接请求超时时间（毫秒）
         */
        private int connectionRequestTimeout = 5000;

        public int getMaxConnTotal() {
            return maxConnTotal;
        }

        public void setMaxConnTotal(int maxConnTotal) {
            this.maxConnTotal = maxConnTotal;
        }

        public int getMaxConnPerRoute() {
            return maxConnPerRoute;
        }

        public void setMaxConnPerRoute(int maxConnPerRoute) {
            this.maxConnPerRoute = maxConnPerRoute;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public int getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }

        public int getConnectionRequestTimeout() {
            return connectionRequestTimeout;
        }

        public void setConnectionRequestTimeout(int connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }
    }

    /**
     * Redis缓存配置
     */
    public static class RedisCacheProperties {
        /**
         * 是否启用Redis缓存
         */
        private boolean enabled = false;

        /**
         * Redis缓存键前缀
         */
        private String keyPrefix = "lvzhi:drp:token:";

        /**
         * Token缓存过期时间（秒），默认24小时
         */
        private int expireTime = 86400;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getKeyPrefix() {
            return keyPrefix;
        }

        public void setKeyPrefix(String keyPrefix) {
            this.keyPrefix = keyPrefix;
        }

        public int getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }
    }
}
