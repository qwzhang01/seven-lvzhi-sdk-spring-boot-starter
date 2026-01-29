package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * 取消订单响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderResponse {

    /**
     * 外部订单号
     */
    private String drpOrderId;

    /**
     * 分销系统订单号
     */
    private String orderId;

    /**
     * 状态；0: 成功，1: 取消失败（可以重试或者线下跟进），2: 取消拒绝（无法重试），3: 取消确认中（待定）
     */
    private Integer state;

    /**
     * 扣费金额
     */
    private BigDecimal deductionAmount;

    // Getters and Setters

    public String getDrpOrderId() {
        return drpOrderId;
    }

    public void setDrpOrderId(String drpOrderId) {
        this.drpOrderId = drpOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }
}
