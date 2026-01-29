package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.service.*;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
     * 配置API客户端
     */
    @Bean
    @ConditionalOnMissingBean
    public LvzhiDrpClient lvzhiDrpClient(CloseableHttpClient lvzhiDrpHttpClient, LvzhiDrpProperties properties) {
        logger.info("初始化旅智DRP API客户端 - 基础URL: {}", properties.getBaseUrl());
        return new LvzhiDrpClient(lvzhiDrpHttpClient, properties);
    }

    // ==================== 授权接口 ====================

    /**
     * 授权服务
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorizeService authorizeService(LvzhiDrpClient lvzhiDrpClient) {
        return new AuthorizeService(lvzhiDrpClient);
    }

    // ==================== 静态信息接口 ====================

    /**
     * 静态信息服务（区域服务）
     */
    @Bean
    @ConditionalOnMissingBean
    public RegionService regionService(LvzhiDrpClient lvzhiDrpClient) {
        return new RegionService(lvzhiDrpClient);
    }

    // ==================== 酒店基础数据接口 ====================

    /**
     * 酒店基础数据服务
     */
    @Bean
    @ConditionalOnMissingBean
    public HotelService hotelService(LvzhiDrpClient lvzhiDrpClient) {
        return new HotelService(lvzhiDrpClient);
    }

    // ==================== 房型接口 ====================

    /**
     * 房型服务
     */
    @Bean
    @ConditionalOnMissingBean
    public RoomTypeService roomTypeService(LvzhiDrpClient lvzhiDrpClient) {
        return new RoomTypeService(lvzhiDrpClient);
    }

    // ==================== 产品接口 ====================

    /**
     * 产品服务
     */
    @Bean
    @ConditionalOnMissingBean
    public ProductService productService(LvzhiDrpClient lvzhiDrpClient) {
        return new ProductService(lvzhiDrpClient);
    }

    // ==================== 酒店动态信息接口 ====================

    /**
     * 酒店动态信息服务（每日明细）
     */
    @Bean
    @ConditionalOnMissingBean
    public DailyService dailyService(LvzhiDrpClient lvzhiDrpClient) {
        return new DailyService(lvzhiDrpClient);
    }

    // ==================== 订单接口 ====================

    /**
     * 订单服务
     */
    @Bean
    @ConditionalOnMissingBean
    public OrderService orderService(LvzhiDrpClient lvzhiDrpClient) {
        return new OrderService(lvzhiDrpClient);
    }

    // ==================== 会员接口 ====================

    /**
     * 会员服务
     */
    @Bean
    @ConditionalOnMissingBean
    public MemberService memberService(LvzhiDrpClient lvzhiDrpClient) {
        return new MemberService(lvzhiDrpClient);
    }
}
