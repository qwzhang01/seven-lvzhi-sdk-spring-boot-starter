package io.github.qwzhang01.luzhi.sdk.callback;

import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelChangeType;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyRequest;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyResponse;
import io.github.qwzhang01.luzhi.sdk.util.LocalDateTimeUtil;

import java.time.LocalDateTime;

/**
 * 酒店信息变更回调接口
 * <p>
 * 用于处理不同类型的酒店信息变更通知
 * </p>
 *
 * @author lvzhi-drp-starter
 */
public interface HotelChangeCallback {

    default HotelNotifyResponse onNotify(HotelNotifyRequest request) {
        String hotelChangeType = request.getHotelChangeType();
        HotelChangeType type = HotelChangeType.fromCode(hotelChangeType);
        if (type == null) {
            HotelNotifyResponse hotelNotifyResponse = new HotelNotifyResponse();
            hotelNotifyResponse.setCode("400");
            hotelNotifyResponse.setMessage("未知的变更类型：" + hotelChangeType);
            hotelNotifyResponse.setData("fail");
            return hotelNotifyResponse;
        }

        if (HotelChangeType.HOTEL.equals(type)) {
            onHotelChange(request.getHotelVid());
        } else if (HotelChangeType.ROOM_TYPE.equals(type)) {
            onRoomTypeChange(request.getHotelVid());
        } else if (HotelChangeType.PRODUCT.equals(type)) {
            onProductChange(request.getHotelVid());
        } else if (HotelChangeType.HOTEL_OFFLINE.equals(type)) {
            onHotelOffline(request.getHotelVid());
        } else if (HotelChangeType.HOTEL_ONLINE.equals(type)) {
            onHotelOnline(request.getHotelVid());
        } else if (HotelChangeType.RATE.equals(type)) {
            onRateChange(request.getHotelVid(),
                    LocalDateTimeUtil.parseDateWithTime(request.getStartDate()),
                    LocalDateTimeUtil.parseDateWithTime(request.getEndDate()));
        } else if (HotelChangeType.PRODUCT_ROOM_STATE.equals(type)) {
            onProductRoomStateChange(request.getHotelVid(),
                    LocalDateTimeUtil.parseDateWithTime(request.getStartDate()),
                    LocalDateTimeUtil.parseDateWithTime(request.getEndDate()));
        } else if (HotelChangeType.AVAIL_ROOMS.equals(type)) {
            onAvailRoomsChange(request.getHotelVid(),
                    LocalDateTimeUtil.parseDateWithTime(request.getStartDate()),
                    LocalDateTimeUtil.parseDateWithTime(request.getEndDate()));
        }

        HotelNotifyResponse hotelNotifyResponse = new HotelNotifyResponse();
        hotelNotifyResponse.setCode("200");
        hotelNotifyResponse.setMessage("成功");
        hotelNotifyResponse.setData("success");
        return hotelNotifyResponse;
    }

    /**
     * 处理房态房量变化
     *
     * @param hotelVid  酒店ID
     * @param startDate 开始时间（格式：yyyy-MM-dd）
     * @param endDate   结束时间（格式：yyyy-MM-dd）
     */
    void onAvailRoomsChange(Long hotelVid, LocalDateTime startDate,
                            LocalDateTime endDate);

    /**
     * 处理产品房态的每日开关变化
     *
     * @param hotelVid  酒店ID
     * @param startDate 开始时间（格式：yyyy-MM-dd）
     * @param endDate   结束时间（格式：yyyy-MM-dd）
     */
    void onProductRoomStateChange(Long hotelVid, LocalDateTime startDate,
                                  LocalDateTime endDate);

    /**
     * 处理房价变化
     *
     * @param hotelVid  酒店ID
     * @param startDate 开始时间（格式：yyyy-MM-dd）
     * @param endDate   结束时间（格式：yyyy-MM-dd）
     */
    void onRateChange(Long hotelVid, LocalDateTime startDate,
                      LocalDateTime endDate);

    /**
     * 处理酒店下线
     *
     * @param hotelVid 酒店ID
     */
    void onHotelOffline(Long hotelVid);

    /**
     * 处理酒店上线
     *
     * @param hotelVid 酒店ID
     */
    void onHotelOnline(Long hotelVid);

    /**
     * 处理房型静态信息变化
     *
     * @param hotelVid 酒店ID
     */
    void onRoomTypeChange(Long hotelVid);

    /**
     * 处理产品静态信息变化
     *
     * @param hotelVid 酒店ID
     */
    void onProductChange(Long hotelVid);

    /**
     * 处理酒店静态信息变化
     *
     * @param hotelVid 酒店ID
     */
    void onHotelChange(Long hotelVid);
}