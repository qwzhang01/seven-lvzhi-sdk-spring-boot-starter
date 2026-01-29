package io.github.qwzhang01.luzhi.sdk.dto.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 房型每日信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeDaily {

    /**
     * 日期；yyyymmdd
     */
    private Integer date;

    /**
     * 总库存（房型）
     */
    private Integer totalCount;

    /**
     * 可用库存（房型）
     */
    private Integer availableCount;

    /**
     * 是否有效；0: 无效；1: 有效
     */
    private Integer state;

    // Getters and Setters

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
