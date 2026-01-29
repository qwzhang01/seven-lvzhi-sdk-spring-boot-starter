package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * 产品每日信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDaily {

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 价格方案ID
     */
    private Long ratePlanId;

    /**
     * 日期；yyyymmdd
     */
    private String date;

    /**
     * 价格
     */
    private BigDecimal rate;

    /**
     * 总库存（产品）
     * [产品库存使用，目前均是房型库存暂不使用]
     */
    private Integer totalCount;

    /**
     * 可用库存（产品）
     * [产品库存使用，目前均是房型库存暂不使用]
     */
    private Integer availableCount;

    /**
     * 餐食类型；0: 未知；1: 早餐；2: 午餐；3: 晚餐
     */
    private Integer mealType;

    /**
     * 餐食份数
     */
    private Integer mealCount;

    /**
     * 预定确认类型；0: 默认（无配置），1: 即时确认（保留房），2: 待查，3: 限时确认
     */
    private Integer bookingConfirmType;

    /**
     * 是否有效；0: 无效；1: 有效
     */
    private Integer state;

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getMealType() {
        return mealType;
    }

    public void setMealType(Integer mealType) {
        this.mealType = mealType;
    }

    public Integer getMealCount() {
        return mealCount;
    }

    public void setMealCount(Integer mealCount) {
        this.mealCount = mealCount;
    }

    public Integer getBookingConfirmType() {
        return bookingConfirmType;
    }

    public void setBookingConfirmType(Integer bookingConfirmType) {
        this.bookingConfirmType = bookingConfirmType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
