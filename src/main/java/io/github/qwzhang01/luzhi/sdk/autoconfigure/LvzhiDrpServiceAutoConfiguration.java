package io.github.qwzhang01.luzhi.sdk.autoconfigure;

import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 旅智分销平台API 接口自动配置类
 *
 * @author lvzhi-drp-starter
 */
@AutoConfiguration
@ConditionalOnBean(LvzhiDrpClient.class)
public class LvzhiDrpServiceAutoConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger(LvzhiDrpServiceAutoConfiguration.class);

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