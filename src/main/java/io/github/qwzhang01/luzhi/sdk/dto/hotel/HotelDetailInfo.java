package io.github.qwzhang01.luzhi.sdk.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 酒店详细信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelDetailInfo {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 母酒店ID
     */
    private Long hotelid;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 国家ID
     */
    private Integer countryId;

    /**
     * 省份ID
     */
    private Integer provinceId;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 区ID
     */
    private Integer districtId;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 国家码
     */
    private String nativeCode;

    /**
     * 区域码
     */
    private String areaCode;

    /**
     * 手机号和座机
     */
    private String telMainCode;

    /**
     * 分机号
     */
    private String telExtCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 开票类型：1-销售端提供发票，2-酒店开具
     */
    private Integer invoiceType;

    /**
     * 发票模式：GENERAL-普票，SPECIAL-专票
     */
    private String invoiceMode;

    /**
     * 客户类型：
     * 0-不区分，都能接待，
     * 1-仅内宾，
     * 2-仅外宾，
     * 3-仅日宾，
     * 4-仅大陆客人
     */
    private Integer customerType;

    /**
     * 支持偏好：0-不支持，1-支持
     */
    private Integer supportPreference;

    /**
     * 酒店图片集合
     */
    private List<HotelImage> hotelImages;

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNativeCode() {
        return nativeCode;
    }

    public void setNativeCode(String nativeCode) {
        this.nativeCode = nativeCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelMainCode() {
        return telMainCode;
    }

    public void setTelMainCode(String telMainCode) {
        this.telMainCode = telMainCode;
    }

    public String getTelExtCode() {
        return telExtCode;
    }

    public void setTelExtCode(String telExtCode) {
        this.telExtCode = telExtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getSupportPreference() {
        return supportPreference;
    }

    public void setSupportPreference(Integer supportPreference) {
        this.supportPreference = supportPreference;
    }

    public List<HotelImage> getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(List<HotelImage> hotelImages) {
        this.hotelImages = hotelImages;
    }
}
