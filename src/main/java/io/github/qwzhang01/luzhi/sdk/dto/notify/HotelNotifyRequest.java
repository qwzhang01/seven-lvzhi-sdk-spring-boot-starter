package io.github.qwzhang01.luzhi.sdk.dto.notify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 酒店信息变更通知请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelNotifyRequest {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 开始时间(变更类型为:AvailRooms,ProductRoomState,Rate时必传)
     * 格式：yyyy-MM-dd
     */
    private String startDate;

    /**
     * 结束时间(变更类型为:AvailRooms,ProductRoomState,Rate时必传)
     * 格式：yyyy-MM-dd
     */
    private String endDate;

    /**
     * 酒店信息变更通知类型
     * <ul>
     *   <li>AvailRooms: 房态房量变化</li>
     *   <li>ProductRoomState: 产品房态的每日开关变化</li>
     *   <li>Rate: 房价变化</li>
     *   <li>HotelOffline: 酒店下线</li>
     *   <li>HotelOnline: 酒店上线</li>
     *   <li>RoomType: 房型静态信息变化</li>
     *   <li>Product: 产品静态信息变化(包含产品静态信息、取消规则、预订规则、价格方案变化)</li>
     *   <li>Hotel: 酒店静态信息变化</li>
     * </ul>
     */
    private String hotelChangeType;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 请求唯一ID
     */
    private String trackingId;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
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

    public String getHotelChangeType() {
        return hotelChangeType;
    }

    public void setHotelChangeType(String hotelChangeType) {
        this.hotelChangeType = hotelChangeType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
}
