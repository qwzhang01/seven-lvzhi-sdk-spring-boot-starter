package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.daily.ProductDailyRequest;
import io.github.qwzhang01.luzhi.sdk.dto.daily.ProductDailyResponse;
import io.github.qwzhang01.luzhi.sdk.dto.daily.RoomTypeDailyRequest;
import io.github.qwzhang01.luzhi.sdk.dto.daily.RoomTypeDailyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 酒店动态信息服务
 * <p>
 * 对应API分组：酒店动态信息接口
 *
 * @author lvzhi-drp-starter
 */
public class DailyService {

    private static final Logger logger =
            LoggerFactory.getLogger(DailyService.class);

    private final LvzhiDrpClient client;

    public DailyService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 查询酒店房型每日明细
     *
     * @param hotelVid   酒店VID（必填）
     * @param roomTypeId 房型ID（必填）
     * @param startDate  开始日期，格式：yyyy-MM-dd（必填）
     * @param endDate    结束日期，格式：yyyy-MM-dd（必填）
     * @return 房型每日明细
     */
    public BaseResponse<RoomTypeDailyResponse> listRoomTypeDailyDetails(Long hotelVid, Long roomTypeId,
                                                                        String startDate, String endDate) {
        logger.debug("查询房型每日明细, hotelVid: {}, roomTypeId: {}, startDate: {}, " +
                        "endDate: {}",
                hotelVid, roomTypeId, startDate, endDate);

        RoomTypeDailyRequest request = new RoomTypeDailyRequest();
        request.setHotelVid(hotelVid);
        request.setRoomTypeId(roomTypeId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        return client.execute(
                ApiMethod.ROOM_TYPE_LIST_DAILY_DETAILS,
                request,
                new TypeReference<BaseResponse<RoomTypeDailyResponse>>() {
                }
        );
    }

    /**
     * 查询酒店产品每日明细
     *
     * @param hotelVid   酒店VID（必填）
     * @param roomTypeId 房型ID（非必填）
     * @param productId  产品ID（非必填）
     * @param startDate  开始日期，格式：yyyy-MM-dd（必填）
     * @param endDate    结束日期，格式：yyyy-MM-dd（必填）
     * @return 产品每日明细
     */
    public BaseResponse<ProductDailyResponse> listProductDailyDetails(Long hotelVid, Long roomTypeId,
                                                                      Long productId, String startDate,
                                                                      String endDate) {
        logger.debug("查询产品每日明细, hotelVid: {}, roomTypeId: {}, productId: {}, " +
                        "startDate: {}, endDate: {}",
                hotelVid, roomTypeId, productId, startDate, endDate);

        ProductDailyRequest request = new ProductDailyRequest();
        request.setHotelVid(hotelVid);
        request.setRoomTypeId(roomTypeId);
        request.setProductId(productId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        return client.execute(
                ApiMethod.PRODUCT_HOTEL_LIST_DAILY_DETAILS,
                request,
                new TypeReference<BaseResponse<ProductDailyResponse>>() {
                }
        );
    }
}
