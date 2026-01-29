package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 阶梯取消规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelLadder {

    /**
     * 阶梯取消类型
     */
    private Integer cancelLadderType;

    /**
     * 阶梯取消时间点
     */
    private String cancelTime;

    /**
     * 阶梯取消金额类型：
     * <ul>
     *   <li>1: 首晚</li>
     *   <li>2: 全额</li>
     *   <li>3: 百分比</li>
     *   <li>4: 固定金额</li>
     * </ul>
     */
    private Integer cancelAmountType;

    /**
     * 阶梯取消百分比
     */
    private Integer cancelPercent;

    /**
     * 阶梯取消金额
     */
    private Long cancelAmount;

    // Getters and Setters

    public Integer getCancelLadderType() {
        return cancelLadderType;
    }

    public void setCancelLadderType(Integer cancelLadderType) {
        this.cancelLadderType = cancelLadderType;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getCancelAmountType() {
        return cancelAmountType;
    }

    public void setCancelAmountType(Integer cancelAmountType) {
        this.cancelAmountType = cancelAmountType;
    }

    public Integer getCancelPercent() {
        return cancelPercent;
    }

    public void setCancelPercent(Integer cancelPercent) {
        this.cancelPercent = cancelPercent;
    }

    public Long getCancelAmount() {
        return cancelAmount;
    }

    public void setCancelAmount(Long cancelAmount) {
        this.cancelAmount = cancelAmount;
    }
}
