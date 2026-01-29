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
    private String id;

    /**
     * 价格方案名称
     */
    private String name;

    /**
     * 价格方案描述
     */
    private String description;

    /**
     * 时间段
     */
    private List<RatePlanTimeBucket> timeBuckets;

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
