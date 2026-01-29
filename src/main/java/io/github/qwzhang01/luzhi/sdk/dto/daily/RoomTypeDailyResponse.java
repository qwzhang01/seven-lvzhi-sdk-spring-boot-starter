package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 房型每日信息响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeDailyResponse {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 房型每日分组列表
     */
    private List<RoomTypeDailyGroup> roomTypeDailyGroups;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public List<RoomTypeDailyGroup> getRoomTypeDailyGroups() {
        return roomTypeDailyGroups;
    }

    public void setRoomTypeDailyGroups(List<RoomTypeDailyGroup> roomTypeDailyGroups) {
        this.roomTypeDailyGroups = roomTypeDailyGroups;
    }
}
