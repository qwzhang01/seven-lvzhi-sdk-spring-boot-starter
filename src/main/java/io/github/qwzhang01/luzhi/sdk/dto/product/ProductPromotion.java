package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 产品促销信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPromotion {

    /**
     * 促销类型
     * 示例："TTTJ"天天特价；"TQYD"提前预定；"LZYH"连住优惠
     */
    private String promotionType;

    /**
     * 促销规则
     */
    private String promotionRule;

    /**
     * 促销描述
     */
    private String promotionDesc;

    /**
     * 促销开始时间 yyyy-MM-dd
     */
    private String startDate;

    /**
     * 促销结束时间 yyyy-MM-dd
     */
    private String endDate;

    // Getters and Setters

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionRule() {
        return promotionRule;
    }

    public void setPromotionRule(String promotionRule) {
        this.promotionRule = promotionRule;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}