package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.GetRoomTypeByIdRequest;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.ListRoomTypeRequest;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.RoomTypeDetail;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.RoomTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 房型服务
 * <p>
 * 对应API分组：房型接口
 *
 * @author lvzhi-drp-starter
 */
public class RoomTypeService {

    private static final Logger logger =
            LoggerFactory.getLogger(RoomTypeService.class);

    private final LvzhiDrpClient client;

    public RoomTypeService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 查询酒店房型列表
     *
     * @param hotelVid 酒店VID（必填）
     * @return 房型列表
     */
    public BaseResponse<List<RoomTypeInfo>> listByHotelVid(Long hotelVid) {
        logger.debug("查询房型列表, hotelVid: {}", hotelVid);

        ListRoomTypeRequest request = new ListRoomTypeRequest();
        request.setHotelVid(hotelVid);

        return client.execute(
                ApiMethod.ROOM_TYPE_LIST_BY_HOTEL_VID,
                request,
                new TypeReference<BaseResponse<List<RoomTypeInfo>>>() {
                }
        );
    }

    /**
     * 获取房型详情
     *
     * @param hotelVid   酒店VID（必填）
     * @param roomTypeId 房型ID（必填）
     * @return 房型详情
     */
    public BaseResponse<RoomTypeDetail> getById(Long hotelVid,
                                                Long roomTypeId) {
        logger.debug("获取房型详情, hotelVid: {}, roomTypeId: {}", hotelVid,
                roomTypeId);

        GetRoomTypeByIdRequest request = new GetRoomTypeByIdRequest();
        request.setHotelVid(hotelVid);
        request.setRoomTypeId(roomTypeId);

        return client.execute(
                ApiMethod.ROOM_TYPE_GET_BY_ID,
                request,
                new TypeReference<BaseResponse<RoomTypeDetail>>() {
                }
        );
    }
}
