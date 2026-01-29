package io.github.qwzhang01.luzhi.sdk.dto.roomtype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 房型详细信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeDetail {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 母酒店ID
     */
    private Long hotelid;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 窗户类型，枚举值：
     * <ul>
     *   <li>WIN: 有窗</li>
     *   <li>SWIN: 部分有窗</li>
     *   <li>WINL: 无窗</li>
     *   <li>AWIN: 有过道窗户</li>
     *   <li>SKYLIGHT: 天窗</li>
     *   <li>CWIN: 封闭窗</li>
     *   <li>FWIN: 飘窗</li>
     *   <li>UNKNOWN: 未知</li>
     * </ul>
     */
    private String windowType;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 面积
     */
    private Long area;

    /**
     * 宽带类型：
     * <ul>
     *   <li>1: 免费提供</li>
     *   <li>2: 不提供此服务</li>
     *   <li>3: 部分收费</li>
     *   <li>4: 部分提供并收费</li>
     *   <li>5: 收费提供</li>
     *   <li>6: 不确定</li>
     *   <li>7: 部分提供并免费</li>
     *   <li>8: 部分提供并只有部分收费</li>
     * </ul>
     */
    private Integer broadband;

    /**
     * wifi类型：
     * <ul>
     *   <li>1: 免费提供</li>
     *   <li>2: 不提供此服务</li>
     *   <li>3: 部分收费</li>
     *   <li>4: 部分提供并收费</li>
     *   <li>5: 收费提供</li>
     *   <li>6: 不确定</li>
     *   <li>7: 部分提供并免费</li>
     *   <li>8: 部分提供并只有部分收费</li>
     * </ul>
     */
    private Integer wifi;

    /**
     * 最大可入住人数
     */
    private Integer maxOccupancy;

    /**
     * 床型关系：
     * <ul>
     *   <li>AND: 与关系</li>
     *   <li>OR: 或关系（非必传，床型数量大于1时非空）</li>
     * </ul>
     */
    private String bedsRelation;

    /**
     * 床型集合
     */
    private List<BedType> bedTypes;

    /**
     * 图片url集合
     */
    private List<String> imageUrls;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Integer getBroadband() {
        return broadband;
    }

    public void setBroadband(Integer broadband) {
        this.broadband = broadband;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(Integer maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public String getBedsRelation() {
        return bedsRelation;
    }

    public void setBedsRelation(String bedsRelation) {
        this.bedsRelation = bedsRelation;
    }

    public List<BedType> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<BedType> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
