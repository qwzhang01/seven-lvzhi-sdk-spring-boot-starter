package io.github.qwzhang01.luzhi.sdk.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 酒店图片
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelImage {

    /**
     * 图片类型：
     * <ul>
     *   <li>HEAD_IMG_ALBUM: 头图</li>
     *   <li>OUTWARD_ALBUM: 外观</li>
     *   <li>PUBLIC_AREA_ALBUM: 公共区域</li>
     *   <li>FOOD_ALBUM: 餐饮</li>
     *   <li>MEETING_ALBUM: 会议</li>
     *   <li>ENTERTAINMENT_ALBUM: 休闲娱乐</li>
     *   <li>OTHER_ALBUM: 其他</li>
     * </ul>
     */
    private String albumCode;

    /**
     * 类型名称
     */
    private String albumName;

    /**
     * 图片url集合
     */
    private List<String> images;

    public String getAlbumCode() {
        return albumCode;
    }

    public void setAlbumCode(String albumCode) {
        this.albumCode = albumCode;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
