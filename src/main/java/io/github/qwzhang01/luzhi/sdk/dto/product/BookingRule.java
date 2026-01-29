package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 预订规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRule {

    /**
     * 预订规则ID
     */
    private Long bookingRuleId;

    /**
     * 预订确认类型：
     * <ul>
     *   <li>0: 默认(无配置)</li>
     *   <li>1: 即时确认(保留房)</li>
     *   <li>2: 待查</li>
     *   <li>3: 限时确认</li>
     * </ul>
     */
    private Integer bookingConfirmType;

    /**
     * 最少预定房间数量；0表示无限制
     */
    private Integer minAmount;

    /**
     * 最大预定房间数量；0表示无限制
     */
    private Integer maxAmount;

    /**
     * 最少入住天数；0表示无限制
     */
    private Integer minDays;

    /**
     * 最大入住天数；0表示无限制
     */
    private Integer maxDays;

    /**
     * 最少提前预订时间(以用户选择的入住日期的23:59:59计算)；0表示无限制
     */
    private Integer minAdvHours;

    /**
     * 最大提前预定时间(以用户选择的入住日期的23:59:59计算)；0表示无限制
     */
    private Integer maxAdvHours;

    // 兼容性字段
    private String id;
    private String name;
    private Integer minNights;
    private Integer maxNights;
    private Integer minAdvanceDays;
    private Integer maxAdvanceDays;
    private Integer minRooms;
    private Integer maxRooms;

    public Long getBookingRuleId() {
        return bookingRuleId;
    }

    public void setBookingRuleId(Long bookingRuleId) {
        this.bookingRuleId = bookingRuleId;
    }

    public Integer getBookingConfirmType() {
        return bookingConfirmType;
    }

    public void setBookingConfirmType(Integer bookingConfirmType) {
        this.bookingConfirmType = bookingConfirmType;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMinDays() {
        return minDays;
    }

    public void setMinDays(Integer minDays) {
        this.minDays = minDays;
    }

    public Integer getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(Integer maxDays) {
        this.maxDays = maxDays;
    }

    public Integer getMinAdvHours() {
        return minAdvHours;
    }

    public void setMinAdvHours(Integer minAdvHours) {
        this.minAdvHours = minAdvHours;
    }

    public Integer getMaxAdvHours() {
        return maxAdvHours;
    }

    public void setMaxAdvHours(Integer maxAdvHours) {
        this.maxAdvHours = maxAdvHours;
    }

    // 兼容性方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinNights() {
        return minNights;
    }

    public void setMinNights(Integer minNights) {
        this.minNights = minNights;
    }

    public Integer getMaxNights() {
        return maxNights;
    }

    public void setMaxNights(Integer maxNights) {
        this.maxNights = maxNights;
    }

    public Integer getMinAdvanceDays() {
        return minAdvanceDays;
    }

    public void setMinAdvanceDays(Integer minAdvanceDays) {
        this.minAdvanceDays = minAdvanceDays;
    }

    public Integer getMaxAdvanceDays() {
        return maxAdvanceDays;
    }

    public void setMaxAdvanceDays(Integer maxAdvanceDays) {
        this.maxAdvanceDays = maxAdvanceDays;
    }

    public Integer getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(Integer minRooms) {
        this.minRooms = minRooms;
    }

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
    }
}
