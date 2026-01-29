package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 房型每日分组信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeDailyGroup {

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型每日信息列表
     */
    private List<RoomTypeDaily> roomTypeDaily;

    // Getters and Setters

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public List<RoomTypeDaily> getRoomTypeDaily() {
        return roomTypeDaily;
    }

    public void setRoomTypeDaily(List<RoomTypeDaily> roomTypeDaily) {
        this.roomTypeDaily = roomTypeDaily;
    }
}
