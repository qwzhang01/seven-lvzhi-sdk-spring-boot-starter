package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.common.PageData;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.GetHotelByVidRequest;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelBasicInfo;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelDetailInfo;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 酒店基础数据服务
 * <p>
 * 对应API分组：酒店基础数据接口
 *
 * @author lvzhi-drp-starter
 */
public class HotelService {

    private static final Logger logger =
            LoggerFactory.getLogger(HotelService.class);

    private final LvzhiDrpClient client;

    public HotelService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 查询酒店列表
     *
     * @param provinceId 省份ID（非必填）
     * @param cityId     城市ID（非必填）
     * @param pageIndex  页数（必填），默认：1
     * @param pageSize   每页条数（必填），默认：20，最大：500
     * @return 酒店列表
     */
    public BaseResponse<PageData<HotelBasicInfo>> list(Integer provinceId,
                                                       String cityId,
                                                       Integer pageIndex,
                                                       Integer pageSize) {
        logger.debug("查询酒店列表, provinceId: {}, cityId: {}, pageIndex: {}, " +
                        "pageSize: {}",
                provinceId, cityId, pageIndex, pageSize);

        HotelListRequest request = new HotelListRequest();
        request.setProvinceId(provinceId);
        request.setCityId(cityId);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        return client.execute(
                ApiMethod.HOTEL_LIST,
                request,
                new TypeReference<BaseResponse<PageData<HotelBasicInfo>>>() {
                }
        );
    }

    /**
     * 获取酒店详情
     *
     * @param hotelVid 酒店VID（必填）
     * @return 酒店详情
     */
    public BaseResponse<HotelDetailInfo> getByVid(Long hotelVid) {
        logger.debug("获取酒店详情, hotelVid: {}", hotelVid);

        GetHotelByVidRequest request = new GetHotelByVidRequest();
        request.setHotelVid(hotelVid);

        return client.execute(
                ApiMethod.HOTEL_GET_BY_VID,
                request,
                new TypeReference<BaseResponse<HotelDetailInfo>>() {
                }
        );
    }
}
