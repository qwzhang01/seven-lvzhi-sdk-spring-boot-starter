package io.github.qwzhang01.luzhi.sdk.common;

/**
 * 回调方法枚举
 *
 * @author lvzhi-drp-starter
 */
public enum CallbackMethod {

    // 酒店信息变化通知
    HOTEL_NOTIFY("/hotelNotify", "hotelNotify", "酒店信息变化通知");

    private final String url;
    private final String method;
    private final String description;

    CallbackMethod(String url, String method, String description) {
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
