package io.github.qwzhang01.luzhi.sdk.dto.notify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 酒店信息变更通知响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelNotifyResponse {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     * "success": 成功
     * "fail": 失败
     */
    private String data;

    // Getters and Setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
