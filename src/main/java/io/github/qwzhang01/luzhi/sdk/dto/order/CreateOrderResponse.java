package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * 创建订单响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderResponse {

    /**
     * 外部订单号
     */
    private String drpOrderId;

    /**
     * 分销系统订单号
     */
    private String orderId;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态：0: 预定状态，1: 待确认，2: 待入住，3: 已入住，4: 已离店，5: 已取消，6: noshow，7: 拒单
     */
    private Integer state;

    /**
     * 取消规则
     */
    private CancelRule cancelRule;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public CancelRule getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(CancelRule cancelRule) {
        this.cancelRule = cancelRule;
    }

    /**
     * 取消规则
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CancelRule {

        /**
         * 取消类型；0: 免费取消；1: 限时取消；2: 不可取消
         */
        private Integer cancelType;

        /**
         * 取消时间类型；
         * 0: 时间（cancel_days_ago+cancel_time_ago）；
         * 1: 小时（cancel_hour_ago）
         */
        private Integer cancelTimeType;

        /**
         * N 天前
         */
        private Integer cancelDaysAgo;

        /**
         * N 点前
         */
        private String cancelTimeAgo;

        /**
         * 入住前 n 小时（入住当天 24:00 往前推 N 小时）
         */
        private Integer cancelHoursAgo;

        /**
         * 扣费的类型；0: 默认（不扣费）；1: 首晚；2: 全额
         */
        private Integer deductionType;

        // Getters and Setters

        public Integer getCancelType() {
            return cancelType;
        }

        public void setCancelType(Integer cancelType) {
            this.cancelType = cancelType;
        }

        public Integer getCancelTimeType() {
            return cancelTimeType;
        }

        public void setCancelTimeType(Integer cancelTimeType) {
            this.cancelTimeType = cancelTimeType;
        }

        public Integer getCancelDaysAgo() {
            return cancelDaysAgo;
        }

        public void setCancelDaysAgo(Integer cancelDaysAgo) {
            this.cancelDaysAgo = cancelDaysAgo;
        }

        public String getCancelTimeAgo() {
            return cancelTimeAgo;
        }

        public void setCancelTimeAgo(String cancelTimeAgo) {
            this.cancelTimeAgo = cancelTimeAgo;
        }

        public Integer getCancelHoursAgo() {
            return cancelHoursAgo;
        }

        public void setCancelHoursAgo(Integer cancelHoursAgo) {
            this.cancelHoursAgo = cancelHoursAgo;
        }

        public Integer getDeductionType() {
            return deductionType;
        }

        public void setDeductionType(Integer deductionType) {
            this.deductionType = deductionType;
        }
    }
}
