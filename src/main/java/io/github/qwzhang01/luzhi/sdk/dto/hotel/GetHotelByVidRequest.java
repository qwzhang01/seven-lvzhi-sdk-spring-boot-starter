package io.github.qwzhang01.luzhi.sdk.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 获取酒店详情请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetHotelByVidRequest {

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

    // 别名方法
    public void setVid(Long vid) {
        this.hotelVid = vid;
    }
}
