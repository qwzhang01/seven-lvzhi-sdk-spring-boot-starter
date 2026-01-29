package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品可定检查响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveValidateResponse {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 预定时间内最大可预定数量
     */
    private Integer maxAvailableStock;

    /**
     * 可定状态；0: 可定，1: 满房，2: 关房，3: 无效价格方案，4: 价格不符，5: 不满足预订规则，6: 其他不可操作
     */
    private Integer state;

    /**
     * 产品每日明细
     */
    private List<ProductDaily> productDailys;

    /**
     * 取消规则
     */
    private CancelRule cancelRule;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getMaxAvailableStock() {
        return maxAvailableStock;
    }

    public void setMaxAvailableStock(Integer maxAvailableStock) {
        this.maxAvailableStock = maxAvailableStock;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<ProductDaily> getProductDailys() {
        return productDailys;
    }

    public void setProductDailys(List<ProductDaily> productDailys) {
        this.productDailys = productDailys;
    }

    public CancelRule getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(CancelRule cancelRule) {
        this.cancelRule = cancelRule;
    }

    /**
     * 产品每日明细
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductDaily {

        /**
         * 日期；yyyy-MM-dd
         */
        private String date;

        /**
         * 价格
         */
        private BigDecimal rate;

        /**
         * 状态；0: 无效；1: 有效
         */
        private Integer state;

        /**
         * 可用库存
         */
        private Integer availableStock;

        /**
         * 餐食类型；0: 未知；1: 早餐；2: 午餐；3: 晚餐
         */
        private Integer mealType;

        /**
         * 餐食份数
         */
        private Integer mealCount;

        // Getters and Setters

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getAvailableStock() {
            return availableStock;
        }

        public void setAvailableStock(Integer availableStock) {
            this.availableStock = availableStock;
        }

        public Integer getMealType() {
            return mealType;
        }

        public void setMealType(Integer mealType) {
            this.mealType = mealType;
        }

        public Integer getMealCount() {
            return mealCount;
        }

        public void setMealCount(Integer mealCount) {
            this.mealCount = mealCount;
        }
    }

    /**
     * 取消规则
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CancelRule {

        /**
         * 取消类型；0: 免费取消；1: 限时取消；2: 不可取消
         */
        private Integer cancelType;

        /**
         * 取消时间类型；0: 时间（cancel_days_ago+cancel_time_ago）；1: 小时（cancel_hour_ago）
         */
        private Integer cancelTimeType;

        /**
         * N 天前
         */
        private Integer cancelDaysAgo;

        /**
         * N 点前
         */
        private String cancelTimeAgo;

        /**
         * 入住前 n 小时（入住当天 24:00 往前推 N 小时）
         */
        private Integer cancelHoursAgo;

        /**
         * 扣费的类型；0: 默认（不扣费）；1: 首晚；2: 全额
         */
        private Integer deductionType;

        // Getters and Setters

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
    }
}
