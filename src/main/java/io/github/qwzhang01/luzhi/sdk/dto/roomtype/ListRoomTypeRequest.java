package io.github.qwzhang01.luzhi.sdk.dto.roomtype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 房型列表请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRoomTypeRequest {

    /**
     * 酒店VID
     */
    private Long hotelVid;

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }
}
