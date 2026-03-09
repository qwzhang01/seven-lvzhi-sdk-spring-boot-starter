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

/**
 * 旅智分销平台API自动配置类
 *
 * @author lvzhi-drp-starter
 */
@AutoConfiguration
@ConditionalOnClass(LvzhiDrpClient.class)
@EnableConfigurationProperties(LvzhiDrpProperties.class)
@ConditionalOnProperty(prefix = "lvzhi.drp", name = "clientId")
public class LvzhiDrpAutoConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(LvzhiDrpAutoConfiguration.class);

    /**
     * 配置HTTP客户端连接管理器
     */
    @Bean
    @ConditionalOnBean(LvzhiDrpProperties.class)
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
    @ConditionalOnBean(LvzhiDrpProperties.class)
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
     * 配置API客户端
     */
    @Bean
    @ConditionalOnBean(LvzhiDrpProperties.class)
    @ConditionalOnMissingBean
    public LvzhiDrpClient lvzhiDrpClient(CloseableHttpClient lvzhiDrpHttpClient, LvzhiDrpProperties properties) {
        logger.info("初始化旅智DRP API客户端 - 基础URL: {}",
                properties.getBaseUrl());
        return new LvzhiDrpClient(lvzhiDrpHttpClient, properties);
    }
}