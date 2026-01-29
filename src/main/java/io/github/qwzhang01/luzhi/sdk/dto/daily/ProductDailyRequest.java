package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 产品每日信息请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDailyRequest {

    /**
     * 酒店ID（必填）
     */
    private Long hotelVid;

    /**
     * 房型ID（非必填）
     */
    private Long roomTypeId;

    /**
     * 产品ID（非必填）
     */
    private Long productId;

    /**
     * 开始日期；格式为 yyyy-MM-dd（必填）
     */
    private String startDate;

    /**
     * 结束日期；格式为 yyyy-MM-dd（必填）
     */
    private String endDate;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
