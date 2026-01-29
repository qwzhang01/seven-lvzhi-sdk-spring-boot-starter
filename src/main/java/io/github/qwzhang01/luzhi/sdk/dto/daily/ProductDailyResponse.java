package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 产品每日信息响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDailyResponse {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 产品每日分组列表
     */
    private List<ProductDailyGroup> productDailyGroups;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public List<ProductDailyGroup> getProductDailyGroups() {
        return productDailyGroups;
    }

    public void setProductDailyGroups(List<ProductDailyGroup> productDailyGroups) {
        this.productDailyGroups = productDailyGroups;
    }
}
