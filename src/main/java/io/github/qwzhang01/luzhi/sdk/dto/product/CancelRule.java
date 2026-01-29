package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 取消规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelRule {

    /**
     * 规则ID
     */
    private String id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则描述
     */
    private String description;

    /**
     * 是否可取消：0否 1是
     */
    private Integer cancelable;

    /**
     * 取消类型：0不可取消 1免费取消 2阶梯取消
     */
    private Integer cancelType;

    /**
     * 免费取消截止时间（如：入住日18:00前）
     */
    private String freeCancelDeadline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCancelable() {
        return cancelable;
    }

    public void setCancelable(Integer cancelable) {
        this.cancelable = cancelable;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getFreeCancelDeadline() {
        return freeCancelDeadline;
    }

    public void setFreeCancelDeadline(String freeCancelDeadline) {
        this.freeCancelDeadline = freeCancelDeadline;
    }
}
