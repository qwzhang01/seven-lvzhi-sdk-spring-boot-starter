package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 样本取消规则
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleCancelRule {

    /**
     * 取消规则类型：
     * <ul>
     *   <li>1: 不可取消</li>
     *   <li>2: 限时取消</li>
     *   <li>3: 免费取消</li>
     *   <li>4: 阶梯取消</li>
     * </ul>
     */
    private Integer cancelType;

    /**
     * 取消规则描述
     */
    private String cancelDesc;

    /**
     * 是否收取违约金
     */
    private Integer isDeposit;

    /**
     * 违约金
     */
    private Long depositAmount;

    /**
     * 最大违约金比例
     */
    private Integer maxDepositPercent;

    /**
     * 最大违约金
     */
    private Long maxDeposit;

    /**
     * 最小违约金
     */
    private Long minDeposit;

    /**
     * 限时取消描述
     */
    private String cancelDate;

    /**
     * 阶梯取消规则集合
     */
    private List<CancelLadder> cancelLadders;

    // Getters and Setters

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelDesc() {
        return cancelDesc;
    }

    public void setCancelDesc(String cancelDesc) {
        this.cancelDesc = cancelDesc;
    }

    public Integer getIsDeposit() {
        return isDeposit;
    }

    public void setIsDeposit(Integer isDeposit) {
        this.isDeposit = isDeposit;
    }

    public Long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getMaxDepositPercent() {
        return maxDepositPercent;
    }

    public void setMaxDepositPercent(Integer maxDepositPercent) {
        this.maxDepositPercent = maxDepositPercent;
    }

    public Long getMaxDeposit() {
        return maxDeposit;
    }

    public void setMaxDeposit(Long maxDeposit) {
        this.maxDeposit = maxDeposit;
    }

    public Long getMinDeposit() {
        return minDeposit;
    }

    public void setMinDeposit(Long minDeposit) {
        this.minDeposit = minDeposit;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public List<CancelLadder> getCancelLadders() {
        return cancelLadders;
    }

    public void setCancelLadders(List<CancelLadder> cancelLadders) {
        this.cancelLadders = cancelLadders;
    }
}
