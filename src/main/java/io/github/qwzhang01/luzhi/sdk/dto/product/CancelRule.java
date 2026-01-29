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
     * 取消规则ID
     */
    private Long cancelRuleId;

    /**
     * 取消类型：
     * <ul>
     *   <li>0: 免费取消</li>
     *   <li>1: 限时取消</li>
     *   <li>2: 不可取消</li>
     * </ul>
     */
    private Integer cancelType;

    /**
     * 取消时间类型：
     * <ul>
     *   <li>0: 时间 (cancelDaysAgo + cancelTimeAgo)</li>
     *   <li>1: 小时 (cancelHoursAgo)</li>
     * </ul>
     */
    private Integer cancelTimeType;

    /**
     * N天前
     */
    private Integer cancelDaysAgo;

    /**
     * N点前 eg:18:30
     */
    private String cancelTimeAgo;

    /**
     * 入住前n小时 (入住当天24:00往前推N小时)
     */
    private Integer cancelHoursAgo;

    /**
     * 扣费的类型：
     * <ul>
     *   <li>0: 默认(不扣费)</li>
     *   <li>1: 首晚</li>
     *   <li>2: 全额</li>
     * </ul>
     */
    private Integer deductionType;

    // 兼容性字段
    private String id;
    private String name;
    private String description;
    private Integer cancelable;
    private String freeCancelDeadline;

    public Long getCancelRuleId() {
        return cancelRuleId;
    }

    public void setCancelRuleId(Long cancelRuleId) {
        this.cancelRuleId = cancelRuleId;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public Integer getCancelTimeType() {
        return cancelTimeType;
    }

    public void setCancelTimeType(Integer cancelTimeType) {
        this.cancelTimeType = cancelTimeType;
    }

    public Integer getCancelDaysAgo() {
        return cancelDaysAgo;
    }

    public void setCancelDaysAgo(Integer cancelDaysAgo) {
        this.cancelDaysAgo = cancelDaysAgo;
    }

    public String getCancelTimeAgo() {
        return cancelTimeAgo;
    }

    public void setCancelTimeAgo(String cancelTimeAgo) {
        this.cancelTimeAgo = cancelTimeAgo;
    }

    public Integer getCancelHoursAgo() {
        return cancelHoursAgo;
    }

    public void setCancelHoursAgo(Integer cancelHoursAgo) {
        this.cancelHoursAgo = cancelHoursAgo;
    }

    public Integer getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(Integer deductionType) {
        this.deductionType = deductionType;
    }

    // 兼容性方法
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

    public String getFreeCancelDeadline() {
        return freeCancelDeadline;
    }

    public void setFreeCancelDeadline(String freeCancelDeadline) {
        this.freeCancelDeadline = freeCancelDeadline;
    }
}
