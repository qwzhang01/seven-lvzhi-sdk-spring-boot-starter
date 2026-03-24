package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 旅智分销平台API自动配置类
 *
 * @author lvzhi-drp-starter
 */
@AutoConfiguration
@ConditionalOnClass(LvzhiDrpClient.class)
@EnableConfigurationProperties(LvzhiDrpProperties.class)
@ConditionalOnProperty(prefix = "lvzhi.drp", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LvzhiDrpAutoConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(LvzhiDrpAutoConfiguration.class);

    /**
     * 配置HTTP客户端连接管理器
     */
    @Bean
    @ConditionalOnMissingBean(name = "lvzhiDrpConnectionManager")
    public PoolingHttpClientConnectionManager lvzhiDrpConnectionManager(LvzhiDrpProperties properties) {
        LvzhiDrpProperties.HttpClientProperties httpClientProps =
                properties.getHttpClient();

        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(httpClientProps.getConnectTimeout()))
                .setSocketTimeout(Timeout.ofMilliseconds(httpClientProps.getReadTimeout()))
                .build();

        return PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(httpClientProps.getMaxConnTotal())
                .setMaxConnPerRoute(httpClientProps.getMaxConnPerRoute())
                .setDefaultConnectionConfig(connectionConfig)
                .build();
    }

    /**
     * 配置HTTP客户端
     */
    @Bean
    @ConditionalOnMissingBean(name = "lvzhiDrpHttpClient")
    public CloseableHttpClient lvzhiDrpHttpClient(PoolingHttpClientConnectionManager lvzhiDrpConnectionManager,
                                                  LvzhiDrpProperties properties) {
        LvzhiDrpProperties.HttpClientProperties httpClientProps =
                properties.getHttpClient();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(httpClientProps.getConnectionRequestTimeout()))
                .setResponseTimeout(Timeout.ofMilliseconds(httpClientProps.getReadTimeout()))
                .build();

        logger.info("初始化旅智DRP HTTP客户端 - 最大连接数: {}, 每路由最大连接数: {}",
                httpClientProps.getMaxConnTotal(),
                httpClientProps.getMaxConnPerRoute());

        return HttpClients.custom()
                .setConnectionManager(lvzhiDrpConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 配置API客户端（使用Redis缓存）
     * 条件：RedisTemplate Bean 存在，且配置了 lvzhi.drp.redis-cache.enabled=true
     */
    @Bean
    @ConditionalOnMissingBean(LvzhiDrpClient.class)
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(prefix = "lvzhi.drp.redis-cache", name = "enabled", havingValue = "true")
    public LvzhiDrpClient lvzhiDrpClientWithRedis(CloseableHttpClient lvzhiDrpHttpClient,
                                                  LvzhiDrpProperties properties,
                                                  RedisTemplate<String, Object> redisTemplate) {
        logger.info("初始化旅智DRP API客户端（使用Redis缓存） - 基础URL: {}", properties.getBaseUrl());
        return new LvzhiDrpClient(lvzhiDrpHttpClient, properties, redisTemplate);
    }

    /**
     * 配置API客户端（使用内存缓存）
     * 兜底方案：未启用Redis缓存，或Redis依赖不存在时使用
     */
    @Bean
    @ConditionalOnMissingBean(LvzhiDrpClient.class)
    public LvzhiDrpClient lvzhiDrpClientWithMemory(CloseableHttpClient lvzhiDrpHttpClient,
                                                   LvzhiDrpProperties properties) {
        logger.info("初始化旅智DRP API客户端（使用内存缓存） - 基础URL: {}", properties.getBaseUrl());
        return new LvzhiDrpClient(lvzhiDrpHttpClient, properties);
    }
}