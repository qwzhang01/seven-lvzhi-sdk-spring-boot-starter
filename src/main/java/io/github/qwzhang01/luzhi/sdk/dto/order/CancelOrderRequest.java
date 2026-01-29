package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 取消订单请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderRequest {

    /**
     * 外部订单号（必填）
     */
    private String drpOrderId;

    /**
     * 分销系统订单号（必填）
     */
    private String orderId;

    /**
     * 酒店ID（必填）
     */
    private Long hotelVid;

    /**
     * 取消原因（必填）
     */
    private String reason;

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

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
