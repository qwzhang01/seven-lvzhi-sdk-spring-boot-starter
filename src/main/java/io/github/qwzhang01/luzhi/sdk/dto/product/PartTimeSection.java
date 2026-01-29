package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 分时区间信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartTimeSection {

    /**
     * 预计入住时间类型
     */
    private Integer promotionType;

    /**
     * 预计入住时间
     */
    private String promotionDesc;

    /**
     * 预计离店时间
     */
    private String joinTime;

    /**
     * 连住时长
     */
    private String leaveTime;

    /**
     * 持续时长
     */
    private Integer stayMinutes;

    /**
     * 分时卖价
     */
    private Long salePrice;

    /**
     * 分时底价
     */
    private Long basePrice;

    // Getters and Setters

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getPromotionDesc() {
        return promotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        this.promotionDesc = promotionDesc;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getStayMinutes() {
        return stayMinutes;
    }

    public void setStayMinutes(Integer stayMinutes) {
        this.stayMinutes = stayMinutes;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }
}
