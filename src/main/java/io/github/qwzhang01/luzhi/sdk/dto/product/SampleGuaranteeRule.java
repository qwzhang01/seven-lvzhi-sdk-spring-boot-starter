package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 样本担保规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleGuaranteeRule {

    /**
     * 是否担保标识
     */
    private Integer isGuarantee;

    /**
     * 担保生效时间
     */
    private String startTime;

    /**
     * 担保金额
     */
    private Long guaranteeAmount;

    /**
     * 担保金额百分比
     */
    private Integer guaranteePercent;

    /**
     * 担保方式：
     * <ul>
     *   <li>1: 按金额</li>
     *   <li>2: 首晚</li>
     *   <li>3: 全额</li>
     * </ul>
     */
    private Integer guaranteeWay;

    /**
     * 担保最大金额
     */
    private Long maxAmount;

    /**
     * 担保最小金额
     */
    private Long minAmount;

    /**
     * 担保描述
     */
    private String desc;

    // Getters and Setters

    public Integer getIsGuarantee() {
        return isGuarantee;
    }

    public void setIsGuarantee(Integer isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(Long guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public Integer getGuaranteePercent() {
        return guaranteePercent;
    }

    public void setGuaranteePercent(Integer guaranteePercent) {
        this.guaranteePercent = guaranteePercent;
    }

    public Integer getGuaranteeWay() {
        return guaranteeWay;
    }

    public void setGuaranteeWay(Integer guaranteeWay) {
        this.guaranteeWay = guaranteeWay;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
