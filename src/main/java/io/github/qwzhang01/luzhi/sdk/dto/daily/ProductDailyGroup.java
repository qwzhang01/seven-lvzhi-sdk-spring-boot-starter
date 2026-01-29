package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 产品每日分组信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDailyGroup {

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品每日信息列表
     */
    private List<ProductDaily> productDaily;

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<ProductDaily> getProductDaily() {
        return productDaily;
    }

    public void setProductDaily(List<ProductDaily> productDaily) {
        this.productDaily = productDaily;
    }
}
