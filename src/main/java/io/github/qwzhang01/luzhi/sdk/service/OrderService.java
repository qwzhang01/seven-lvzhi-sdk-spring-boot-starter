package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.order.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 订单服务
 * <p>
 * 对应API分组：订单接口
 *
 * @author lvzhi-drp-starter
 */
public class OrderService {

    private static final Logger logger =
            LoggerFactory.getLogger(OrderService.class);

    private final LvzhiDrpClient client;

    public OrderService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 创建订单
     *
     * @param request 创建订单请求（包含所有订单参数）
     * @return 创建订单响应
     */
    public BaseResponse<CreateOrderResponse> create(CreateOrderRequest request) {
        logger.debug("创建订单, drpOrderId: {}, hotelVid: {}, productId: {}",
                request.getDrpOrderId(), request.getHotelVid(),
                request.getProductId());

        return client.execute(
                ApiMethod.ORDER_CREATE,
                request,
                new TypeReference<BaseResponse<CreateOrderResponse>>() {
                }
        );
    }

    /**
     * 取消订单
     *
     * @param drpOrderId 外部订单号（必填）
     * @param orderId    分销系统订单号（必填）
     * @param hotelVid   酒店ID（必填）
     * @param reason     取消原因（必填）
     * @return 取消订单响应
     */
    public BaseResponse<CancelOrderResponse> cancel(String drpOrderId,
                                                    String orderId,
                                                    Long hotelVid,
                                                    String reason) {
        logger.debug("取消订单, drpOrderId: {}, orderId: {}, hotelVid: {}, " +
                        "reason: {}",
                drpOrderId, orderId, hotelVid, reason);

        CancelOrderRequest request = new CancelOrderRequest();
        request.setDrpOrderId(drpOrderId);
        request.setOrderId(orderId);
        request.setHotelVid(hotelVid);
        request.setReason(reason);

        return client.execute(
                ApiMethod.ORDER_CANCEL,
                request,
                new TypeReference<BaseResponse<CancelOrderResponse>>() {
                }
        );
    }

    /**
     * 产品可定检查
     *
     * @param request 检查请求（包含所有检查参数）
     * @return 检查结果
     */
    public BaseResponse<ReserveValidateResponse> reserveValidate(ReserveValidateRequest request) {
        logger.debug("产品可定检查, hotelVid: {}, productId: {}, checkInDate: {}, " +
                        "checkOutDate: {}",
                request.getHotelVid(), request.getProductId(),
                request.getCheckInDate(), request.getCheckOutDate());

        return client.execute(
                ApiMethod.ORDER_RESERVE_VALIDATE,
                request,
                new TypeReference<BaseResponse<ReserveValidateResponse>>() {
                }
        );
    }

    /**
     * 查询订单详情
     *
     * @param orderId 平台订单号（必填）
     * @return 订单详情
     */
    public BaseResponse<OrderDetailInfo> getByOrderId(String orderId) {
        logger.debug("查询订单详情, orderId: {}", orderId);

        GetOrderByIdRequest request = new GetOrderByIdRequest();
        request.setOrderId(orderId);

        return client.execute(
                ApiMethod.ORDER_GET_BY_ID,
                request,
                new TypeReference<BaseResponse<OrderDetailInfo>>() {
                }
        );
    }
}
