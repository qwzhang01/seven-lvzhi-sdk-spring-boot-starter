package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 预订规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRule {

    /**
     * 规则ID
     */
    private String id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 最小入住天数
     */
    private Integer minNights;

    /**
     * 最大入住天数
     */
    private Integer maxNights;

    /**
     * 最小提前预订天数
     */
    private Integer minAdvanceDays;

    /**
     * 最大提前预订天数
     */
    private Integer maxAdvanceDays;

    /**
     * 最小间数
     */
    private Integer minRooms;

    /**
     * 最大间数
     */
    private Integer maxRooms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinNights() {
        return minNights;
    }

    public void setMinNights(Integer minNights) {
        this.minNights = minNights;
    }

    public Integer getMaxNights() {
        return maxNights;
    }

    public void setMaxNights(Integer maxNights) {
        this.maxNights = maxNights;
    }

    public Integer getMinAdvanceDays() {
        return minAdvanceDays;
    }

    public void setMinAdvanceDays(Integer minAdvanceDays) {
        this.minAdvanceDays = minAdvanceDays;
    }

    public Integer getMaxAdvanceDays() {
        return maxAdvanceDays;
    }

    public void setMaxAdvanceDays(Integer maxAdvanceDays) {
        this.maxAdvanceDays = maxAdvanceDays;
    }

    public Integer getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(Integer minRooms) {
        this.minRooms = minRooms;
    }

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
    }
}
