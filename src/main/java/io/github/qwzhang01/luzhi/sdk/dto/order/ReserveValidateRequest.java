package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品可定检查请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveValidateRequest {

    /**
     * 酒店ID（必填）
     */
    private Long hotelVid;

    /**
     * 房型ID（必填）
     */
    private Long roomTypeId;

    /**
     * 产品ID（必填）
     */
    private Long productId;

    /**
     * 入住日期；格式为 yyyy-MM-dd（必填）
     */
    private String checkInDate;

    /**
     * 离店日期；格式为 yyyy-MM-dd（必填）
     */
    private String checkOutDate;

    /**
     * 预定数量（必填）
     */
    private Integer count;

    /**
     * 订单每日明细集合（必填）
     */
    private List<OrderDailyDetail> orderDailyDetails;

    /**
     * 入住者信息（非必填）
     */
    private Occupant occupant;

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

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<OrderDailyDetail> getOrderDailyDetails() {
        return orderDailyDetails;
    }

    public void setOrderDailyDetails(List<OrderDailyDetail> orderDailyDetails) {
        this.orderDailyDetails = orderDailyDetails;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    /**
     * 订单每日明细
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderDailyDetail {

        /**
         * 日期；yyyy-MM-dd（必填）
         */
        private String date;

        /**
         * 价格（必填）
         */
        private BigDecimal rate;

        /**
         * 餐食类型；0: 未知；1: 早餐；2: 午餐；3: 晚餐（非必填）
         */
        private Integer mealType;

        /**
         * 餐食份数（非必填）
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
     * 入住者信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Occupant {

        /**
         * 总成人数 = 间数 * 每间最大入住成人数（非必填）
         */
        private Integer adults;

        /**
         * 总儿童人数（非必填）
         */
        private Integer children;

        /**
         * 入住者年龄列表（非必填）
         */
        private List<OccupantAge> occupantAges;

        // Getters and Setters

        public Integer getAdults() {
            return adults;
        }

        public void setAdults(Integer adults) {
            this.adults = adults;
        }

        public Integer getChildren() {
            return children;
        }

        public void setChildren(Integer children) {
            this.children = children;
        }

        public List<OccupantAge> getOccupantAges() {
            return occupantAges;
        }

        public void setOccupantAges(List<OccupantAge> occupantAges) {
            this.occupantAges = occupantAges;
        }
    }

    /**
     * 入住者年龄信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OccupantAge {

        /**
         * 用户类型；0: 表示成人；1: 表示儿童（非必填）
         */
        private Integer type;

        /**
         * 年龄（非必填）
         */
        private Integer age;

        // Getters and Setters

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
