package io.github.qwzhang01.luzhi.sdk.common;

/**
 * API方法枚举
 *
 * @author lvzhi-drp-starter
 */
public enum ApiMethod {

    // 授权接口
    AUTHORIZE_GET_TOKEN("/authorize/getToken", "authorize.getToken", "获取Token"),

    // 静态信息接口
    REGION_LIST_TREES("/region/listRegionTrees", "region.listRegionTrees",
            "查询三级城市信息"),

    // 酒店基础数据接口
    HOTEL_LIST("/hotel/list", "hotel.list", "酒店基础数据列表"),
    HOTEL_GET_BY_VID("/hotel/getByVid", "hotel.getByVid", "酒店详情"),

    // 房型接口
    ROOM_TYPE_LIST_BY_HOTEL_VID("/roomType/listByHotelVid", "roomType" +
            ".listByHotelVid", "酒店房型列表"),
    ROOM_TYPE_GET_BY_ID("/roomType/getById", "roomType.getById", "酒店房型详情"),

    // 产品接口
    PRODUCT_LIST("/product/list", "product.list", "酒店产品列表"),
    PRODUCT_GET_BY_ID("/product/getById", "product.getById", "酒店产品详情"),

    // 酒店动态信息接口
    ROOM_TYPE_LIST_DAILY_DETAILS("/roomType/listDailyDetails", "roomType" +
            ".listDailyDetails", "酒店房型每日明细"),
    PRODUCT_HOTEL_LIST_DAILY_DETAILS("/product/hotelListDailyDetails",
            "product.hotelListDailyDetails", "酒店产品每日明细"),

    // 订单接口
    ORDER_CREATE("/order/create", "order.create", "创建订单"),
    ORDER_CANCEL("/order/cancel", "order.cancel", "取消订单"),
    ORDER_RESERVE_VALIDATE("/order/reserveValidateByProductId", "order" +
            ".reserveValidateByProductId", "产品可定检查"),
    ORDER_GET_BY_ID("/order/getByOrderId", "order.getByOrderId", "订单查询"),
    // ORDER_LIST("/order/list", "order.list", "订单列表"),

    // 会员接口
    MEMBER_BIND("/member/bind", "member.bind", "会员绑定"),
    MEMBER_SEARCH("/member/search", "member.search", "会员信息查询"),
    MEMBER_NOTIFY("/member/notify", "member.notify", "会员信息变化通知");

    private final String url;
    private final String method;
    private final String description;

    ApiMethod(String url, String method, String description) {
        this.url = url;
        this.method = method;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }
}
