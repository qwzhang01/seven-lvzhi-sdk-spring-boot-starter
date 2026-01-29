package io.github.qwzhang01.luzhi.sdk.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 酒店列表请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelListRequest {

    /**
     * 省份ID（非必填）
     */
    private Integer provinceId;

    /**
     * 城市ID（非必填）
     */
    private String cityId;

    /**
     * 页数（必填），默认：1
     */
    private Integer pageIndex;

    /**
     * 每页条数（必填），默认：20，最大：500
     */
    private Integer pageSize;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
