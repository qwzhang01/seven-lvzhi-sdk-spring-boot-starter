package io.github.qwzhang01.luzhi.sdk.dto.region;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 查询区域树请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRegionTreesRequest {

    /**
     * 省份ID（可选）
     */
    private Integer provinceId;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
