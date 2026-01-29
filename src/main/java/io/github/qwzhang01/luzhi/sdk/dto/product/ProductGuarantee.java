package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

/**
 * 产品担保信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductGuarantee {

    /**
     * 担保类型：
     * <ul>
     *   <li>0: 默认(无担保)</li>
     *   <li>1: 无担保</li>
     *   <li>2: 首晚担保</li>
     *   <li>3: 全额担保</li>
     * </ul>
     */
    private Integer guaranteeType;

    /**
     * 担保金额
     */
    private BigDecimal guaranteeAmount;

    /**
     * 最晚保留时间；eg:18:00
     */
    private String latestReservationTime;

    // Getters and Setters

    public Integer getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(Integer guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getLatestReservationTime() {
        return latestReservationTime;
    }

    public void setLatestReservationTime(String latestReservationTime) {
        this.latestReservationTime = latestReservationTime;
    }
}