package io.github.qwzhang01.luzhi.sdk.dto.roomtype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 获取房型详情请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRoomTypeByIdRequest {

    /**
     * 酒店VID
     */
    private Long hotelVid;
    /**
     * 房型ID
     */
    private Long roomTypeId;

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
}
