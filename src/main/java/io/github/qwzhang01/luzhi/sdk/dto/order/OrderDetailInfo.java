package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单详情
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailInfo {

    /**
     * 分销商订单号
     */
    private String drpOrderId;

    /**
     * 分销系统订单号（文档字段名：orderId）
     */
    private String outOrderId;

    /**
     * 分销系统订单号（文档字段名，映射到outOrderId）
     */
    private String orderId;

    /**
     * 酒店vid
     */
    private Long hotelVid;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型vid
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 入住日期，格式 yyyy-MM-dd
     */
    private String checkInDate;

    /**
     * 离店日期，格式 yyyy-MM-dd
     */
    private String checkOutDate;

    /**
     * 入住天数，根据入离日期计算，checkOut - checkIn（文档字段名：days）
     */
    private Integer stayDays;

    /**
     * 间夜（文档字段名，映射到stayDays）
     */
    private Integer days;

    /**
     * 房间数
     */
    private Integer count;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     */
    private BigDecimal payAmount;

    /**
     * 折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付类型(0: 未知 1. 现付 2.预付)
     */
    private Integer paymentType;

    /**
     * 担保类型；担保金类型；0: 默认值(无意义)；1:无担保；2:首晚担保；3:全额担保
     */
    private Integer guaranteeType;

    /**
     * 担保金额
     */
    private BigDecimal guaranteeAmount;

    /**
     * 订单每日明细（内部字段名）
     */
    private List<OrderDailyInfo> orderDailyInfos;

    /**
     * 订单每日明细集合（文档字段名，映射到orderDailyInfos）
     */
    private List<OrderDailyDetail> orderDailyDetails;

    /**
     * 到店时间，最早/最晚，格式 HH:mm
     */
    private String arriveStartTime;

    /**
     * 到店时间，最早/最晚，格式 HH:mm
     */
    private String arriveEndTime;

    /**
     * 特殊要求/备注
     */
    private String customerRemark;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 入住人，参考入住人结构
     */
    private List<Guest> guests;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 会员卡号
     */
    private String memberNo;

    /**
     * 会员等级
     */
    private String memberLevel;

    /**
     * 订单状态
     * 1-待确认、2-已确认、3-已取消、4-已拒绝、5-已完成、6-取消中、7-NoShow
     */
    private Integer state;

    /**
     * 酒店确认号
     */
    private String confirmNo;

    /**
     * 取消规则
     */
    private CancelRule cancelRule;

    /**
     * 最晚保留时间类型；0:当天；1:次日
     */
    private Integer latestReservationTimeType;

    /**
     * 最晚保留时间；eg:18:00
     */
    private String latestReservationTime;

    /**
     * 最早到店时间；yyyy-MM-dd HH:mm:ss
     */
    private String earlyArrivalTime;

    /**
     * 最晚到店时间；yyyy-MM-dd HH:mm:ss
     */
    private String lastArrivalTime;

    /**
     * 用户备注信息
     */
    private String guestNoticeInfo;

    /**
     * 用户开票信息
     */
    private String guestInvoiceInfo;

    /**
     * 取消/拒绝原因
     */
    private String reason;

    /**
     * 实际扣款金额，订单取消时有值
     */
    private BigDecimal deductionAmount;

    /**
     * 预订确认类型
     * 0-未知、1-即时确认、2-二次确认
     */
    private Integer bookingConfirmType;

    /**
     * 二次确认截止日期
     */
    private String secondConfirmDeadline;

    /**
     * 最晚取消时间
     */
    private String lastCancelTime;

    /**
     * 扣款类型
     * 0-未知、1-无条件、2-限时取消、3-扣首日、4-扣全额、5-扣比例、6-扣金额
     */
    private Integer deductionType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分销商创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    // ==================== 内部类 ====================

    public String getDrpOrderId() {
        return drpOrderId;
    }

    public void setDrpOrderId(String drpOrderId) {
        this.drpOrderId = drpOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    // ==================== 主类 Getters and Setters ====================

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getOrderId() {
        return orderId != null ? orderId : outOrderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        this.outOrderId = orderId;
    }

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getStayDays() {
        return stayDays;
    }

    public void setStayDays(Integer stayDays) {
        this.stayDays = stayDays;
    }

    public Integer getDays() {
        return days != null ? days : stayDays;
    }

    public void setDays(Integer days) {
        this.days = days;
        this.stayDays = days;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

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

    public List<OrderDailyInfo> getOrderDailyInfos() {
        return orderDailyInfos;
    }

    public void setOrderDailyInfos(List<OrderDailyInfo> orderDailyInfos) {
        this.orderDailyInfos = orderDailyInfos;
    }

    public List<OrderDailyDetail> getOrderDailyDetails() {
        return orderDailyDetails;
    }

    public void setOrderDailyDetails(List<OrderDailyDetail> orderDailyDetails) {
        this.orderDailyDetails = orderDailyDetails;
    }

    public String getArriveStartTime() {
        return arriveStartTime;
    }

    public void setArriveStartTime(String arriveStartTime) {
        this.arriveStartTime = arriveStartTime;
    }

    public String getArriveEndTime() {
        return arriveEndTime;
    }

    public void setArriveEndTime(String arriveEndTime) {
        this.arriveEndTime = arriveEndTime;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getConfirmNo() {
        return confirmNo;
    }

    public void setConfirmNo(String confirmNo) {
        this.confirmNo = confirmNo;
    }

    public CancelRule getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(CancelRule cancelRule) {
        this.cancelRule = cancelRule;
    }

    public Integer getLatestReservationTimeType() {
        return latestReservationTimeType;
    }

    public void setLatestReservationTimeType(Integer latestReservationTimeType) {
        this.latestReservationTimeType = latestReservationTimeType;
    }

    public String getLatestReservationTime() {
        return latestReservationTime;
    }

    public void setLatestReservationTime(String latestReservationTime) {
        this.latestReservationTime = latestReservationTime;
    }

    public String getEarlyArrivalTime() {
        return earlyArrivalTime;
    }

    public void setEarlyArrivalTime(String earlyArrivalTime) {
        this.earlyArrivalTime = earlyArrivalTime;
    }

    public String getLastArrivalTime() {
        return lastArrivalTime;
    }

    public void setLastArrivalTime(String lastArrivalTime) {
        this.lastArrivalTime = lastArrivalTime;
    }

    public String getGuestNoticeInfo() {
        return guestNoticeInfo;
    }

    public void setGuestNoticeInfo(String guestNoticeInfo) {
        this.guestNoticeInfo = guestNoticeInfo;
    }

    public String getGuestInvoiceInfo() {
        return guestInvoiceInfo;
    }

    public void setGuestInvoiceInfo(String guestInvoiceInfo) {
        this.guestInvoiceInfo = guestInvoiceInfo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public Integer getBookingConfirmType() {
        return bookingConfirmType;
    }

    public void setBookingConfirmType(Integer bookingConfirmType) {
        this.bookingConfirmType = bookingConfirmType;
    }

    public String getSecondConfirmDeadline() {
        return secondConfirmDeadline;
    }

    public void setSecondConfirmDeadline(String secondConfirmDeadline) {
        this.secondConfirmDeadline = secondConfirmDeadline;
    }

    public String getLastCancelTime() {
        return lastCancelTime;
    }

    public void setLastCancelTime(String lastCancelTime) {
        this.lastCancelTime = lastCancelTime;
    }

    public Integer getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(Integer deductionType) {
        this.deductionType = deductionType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 订单每日明细
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderDailyInfo {

        /**
         * 产品ID
         */
        private Long productId;

        /**
         * 价格计划ID
         */
        private Long ratePlanId;

        /**
         * 日期
         */
        private String date;

        /**
         * 价格
         */
        private BigDecimal rate;

        /**
         * 早餐类型
         * 0-无早、1-单早、2-双早、3-三早、4-多早
         */
        private Integer mealType;

        /**
         * 早餐数量
         */
        private Integer mealCount;

        /**
         * 预订确认类型
         * 0-未知、1-即时确认、2-二次确认
         */
        private Integer bookingConfirmType;

        // Getters and Setters

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getRatePlanId() {
            return ratePlanId;
        }

        public void setRatePlanId(Long ratePlanId) {
            this.ratePlanId = ratePlanId;
        }

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

        public Integer getBookingConfirmType() {
            return bookingConfirmType;
        }

        public void setBookingConfirmType(Integer bookingConfirmType) {
            this.bookingConfirmType = bookingConfirmType;
        }
    }

    /**
     * 订单每日明细（文档字段名）
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderDailyDetail {

        /**
         * 日期；yyyymmdd
         */
        private String date;

        /**
         * 价格
         */
        private BigDecimal rate;

        /**
         * 餐食类型；0: 未知；1:早餐；2 午餐；3:晚餐
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
     * 入住人信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Guest {

        /**
         * 入住人姓名
         */
        private String guestName;

        /**
         * 入住人英文姓
         */
        private String guestLastName;

        /**
         * 入住人英文名
         */
        private String guestFirstName;

        /**
         * 入住人电话
         */
        private String guestPhone;

        /**
         * 入住人类型
         * 1-成人、2-儿童
         */
        private Integer guestType;

        /**
         * 年龄
         */
        private Integer age;

        /**
         * 证件类型 0:身份证 [目前只支持身份证]
         */
        private Integer idType;

        /**
         * 证件号码
         */
        private String idNo;

        // Getters and Setters

        public String getGuestName() {
            return guestName;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }

        public String getGuestLastName() {
            return guestLastName;
        }

        public void setGuestLastName(String guestLastName) {
            this.guestLastName = guestLastName;
        }

        public String getGuestFirstName() {
            return guestFirstName;
        }

        public void setGuestFirstName(String guestFirstName) {
            this.guestFirstName = guestFirstName;
        }

        public String getGuestPhone() {
            return guestPhone;
        }

        public void setGuestPhone(String guestPhone) {
            this.guestPhone = guestPhone;
        }

        public Integer getGuestType() {
            return guestType;
        }

        public void setGuestType(Integer guestType) {
            this.guestType = guestType;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getIdType() {
            return idType;
        }

        public void setIdType(Integer idType) {
            this.idType = idType;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }
    }

    /**
     * 取消政策
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CancelRule {

        /**
         * 取消时间类型
         * 0-随时、1-到店日前、2-到店日后
         */
        private Integer cancelTimeType;

        /**
         * 可取消截止天数
         */
        private Integer cancelDaysAgo;

        /**
         * 可取消截止小时
         */
        private Integer cancelHoursAgo;

        /**
         * 扣款类型
         * 0-未知、1-无条件、2-限时取消、3-扣首日、4-扣全额、5-扣比例、6-扣金额
         */
        private Integer deductionType;

        /**
         * 不可取消扣款比例
         */
        private Integer deductionRatio;

        /**
         * 不可取消扣款金额
         */
        private BigDecimal deductionAmount;

        /**
         * 最晚可取消时间，格式 yyyy-MM-dd
         */
        private String lastCancelDate;

        /**
         * 最晚可取消时间，格式 HH:mm
         */
        private String lastCancelTime;

        // Getters and Setters

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

        public Integer getDeductionRatio() {
            return deductionRatio;
        }

        public void setDeductionRatio(Integer deductionRatio) {
            this.deductionRatio = deductionRatio;
        }

        public BigDecimal getDeductionAmount() {
            return deductionAmount;
        }

        public void setDeductionAmount(BigDecimal deductionAmount) {
            this.deductionAmount = deductionAmount;
        }

        public String getLastCancelDate() {
            return lastCancelDate;
        }

        public void setLastCancelDate(String lastCancelDate) {
            this.lastCancelDate = lastCancelDate;
        }

        public String getLastCancelTime() {
            return lastCancelTime;
        }

        public void setLastCancelTime(String lastCancelTime) {
            this.lastCancelTime = lastCancelTime;
        }
    }
}
