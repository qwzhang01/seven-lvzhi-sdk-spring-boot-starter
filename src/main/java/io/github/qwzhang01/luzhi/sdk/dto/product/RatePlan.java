package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 价格方案
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatePlan {

    /**
     * 价格方案ID
     */
    private Long ratePlanId;

    /**
     * 价格方案名称
     */
    private String ratePlanName;

    /**
     * 价格方案原始ID
     */
    private String ratePlanOriginalId;

    /**
     * 价格方案类型：0:普通房价格，1:钟点房价格
     */
    private Integer ratePlanType;

    /**
     * 开始日期 yyyymmdd
     */
    private String startDate;

    /**
     * 结束日期 yyyymmdd
     */
    private String endDate;

    /**
     * 价格方案每日时间段；为空表示全天有效，价格方案类型为钟点房价格则非空
     */
    private List<RatePlanTimeBucket> ratePlanTimeBuckets;

    // 兼容性字段
    private String id;
    private String name;
    private String description;
    private List<RatePlanTimeBucket> timeBuckets;

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public String getRatePlanOriginalId() {
        return ratePlanOriginalId;
    }

    public void setRatePlanOriginalId(String ratePlanOriginalId) {
        this.ratePlanOriginalId = ratePlanOriginalId;
    }

    public Integer getRatePlanType() {
        return ratePlanType;
    }

    public void setRatePlanType(Integer ratePlanType) {
        this.ratePlanType = ratePlanType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<RatePlanTimeBucket> getRatePlanTimeBuckets() {
        return ratePlanTimeBuckets;
    }

    public void setRatePlanTimeBuckets(List<RatePlanTimeBucket> ratePlanTimeBuckets) {
        this.ratePlanTimeBuckets = ratePlanTimeBuckets;
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

    public List<RatePlanTimeBucket> getTimeBuckets() {
        return timeBuckets;
    }

    public void setTimeBuckets(List<RatePlanTimeBucket> timeBuckets) {
        this.timeBuckets = timeBuckets;
    }
}
