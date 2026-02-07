package io.github.qwzhang01.luzhi.sdk.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

/**
 * 创建订单请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderRequest {

    /**
     * 外部订单号
     */
    private String drpOrderId;

    /**
     * 酒店VID
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
     * 入住日期；格式为 yyyy-MM-dd
     */
    private String checkInDate;

    /**
     * 离店日期；格式为 yyyy-MM-dd
     */
    private String checkOutDate;

    /**
     * 房间数量
     */
    private Integer roomCount;

    /**
     * 预订数量
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
     * 结算金额
     */
    private BigDecimal settleAmount;

    /**
     * 付款类型；0: 线下，1: 预付，2: 现付
     */
    private Integer paymentType;

    /**
     * 担保类型
     */
    private Integer guaranteeType;

    /**
     * 担保金额
     */
    private BigDecimal guaranteeAmount;

    /**
     * 外部担保
     */
    private String otherGuarantee;

    /**
     * 最晚到店时间；格式为 HH:mm
     */
    private String latestArrivalTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 币种；默认CNY
     */
    private String currency;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 会员卡号
     */
    private String memberNo;

    /**
     * 会员等级
     */
    private Integer memberLevel;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 入住人列表
     */
    private List<Guest> guests;

    /**
     * 每日明细列表
     */
    private List<DailyInfo> dailyInfos;

    /**
     * 订单每日明细集合
     */
    private List<ReserveValidateRequest.OrderDailyDetail> orderDailyDetails;

    /**
     * 客户权益集合
     */
    private List<GuestBenefit> guestBenefits;

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
     * 最晚到店时间；yyyy-MM-dd HH:mm:ss（注意：文档中字段名为lastArrivalTime）
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
     * 联系人信息
     */
    private Contact contact;

    /**
     * 发票信息
     */
    private Invoice invoice;

    /**
     * 取消规则
     */
    private CancelRule cancelRule;

    // Getters and Setters

    public String getDrpOrderId() {
        return drpOrderId;
    }

    public void setDrpOrderId(String drpOrderId) {
        this.drpOrderId = drpOrderId;
    }

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

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
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

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
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

    public String getOtherGuarantee() {
        return otherGuarantee;
    }

    public void setOtherGuarantee(String otherGuarantee) {
        this.otherGuarantee = otherGuarantee;
    }

    public String getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(String latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<DailyInfo> getDailyInfos() {
        return dailyInfos;
    }

    public void setDailyInfos(List<DailyInfo> dailyInfos) {
        this.dailyInfos = dailyInfos;
    }

    public List<ReserveValidateRequest.OrderDailyDetail> getOrderDailyDetails() {
        return orderDailyDetails;
    }

    public void setOrderDailyDetails(List<ReserveValidateRequest.OrderDailyDetail> orderDailyDetails) {
        this.orderDailyDetails = orderDailyDetails;
    }

    public List<GuestBenefit> getGuestBenefits() {
        return guestBenefits;
    }

    public void setGuestBenefits(List<GuestBenefit> guestBenefits) {
        this.guestBenefits = guestBenefits;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public CancelRule getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(CancelRule cancelRule) {
        this.cancelRule = cancelRule;
    }

    /**
     * 入住人信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Guest {

        /**
         * 餐食类型；0: 无，1: 早餐，2: 午餐，3: 晚餐，4: 用餐
         */
        private Integer mealType;

        /**
         * 餐食份数
         */
        private Integer mealCount;

        /**
         * 联系人
         */
        private String contactName;

        /**
         * 联系人电话
         */
        private String contactPhone;

        /**
         * 联系人Email
         */
        private String contactMail;

        /**
         * 联系人传真
         */
        private String fax;

        /**
         * 入住人数
         */
        private Integer guests;

        /**
         * 入住人类型；0: 成人，1: 儿童
         */
        private Integer guestType;

        /**
         * 年龄
         */
        private Integer age;

        /**
         * 证件类型；0: 身份证，1: 护照，2: 其他（文档中字段名为idType）
         */
        private Integer guestIdType;

        /**
         * 证件类型（文档字段名，映射到guestIdType）
         */
        private Integer idType;

        /**
         * 入住人姓名
         */
        private String guestName;

        /**
         * 入住人姓名（英）
         */
        private String guestFirstName;

        /**
         * 入住人姓氏（英）
         */
        private String guestLastName;

        /**
         * 入住人证件号
         */
        private String guestIdNo;

        /**
         * 证件号码（文档字段名，映射到guestIdNo）
         */
        private String idNo;

        /**
         * 入住人电话
         */
        private String guestPhone;

        /**
         * ota
         */
        private String ota;

        /**
         * 顾客标签/备注
         */
        private String guestRemark;

        // Getters and Setters

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

        public String getContactMail() {
            return contactMail;
        }

        public void setContactMail(String contactMail) {
            this.contactMail = contactMail;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public Integer getGuests() {
            return guests;
        }

        public void setGuests(Integer guests) {
            this.guests = guests;
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

        public Integer getGuestIdType() {
            return guestIdType;
        }

        public void setGuestIdType(Integer guestIdType) {
            this.guestIdType = guestIdType;
        }

        public Integer getIdType() {
            return idType != null ? idType : guestIdType;
        }

        public void setIdType(Integer idType) {
            this.idType = idType;
            this.guestIdType = idType;
        }

        public String getGuestName() {
            return guestName;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }

        public String getGuestFirstName() {
            return guestFirstName;
        }

        public void setGuestFirstName(String guestFirstName) {
            this.guestFirstName = guestFirstName;
        }

        public String getGuestLastName() {
            return guestLastName;
        }

        public void setGuestLastName(String guestLastName) {
            this.guestLastName = guestLastName;
        }

        public String getGuestIdNo() {
            return guestIdNo;
        }

        public void setGuestIdNo(String guestIdNo) {
            this.guestIdNo = guestIdNo;
        }

        public String getIdNo() {
            return idNo != null ? idNo : guestIdNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
            this.guestIdNo = idNo;
        }

        public String getGuestPhone() {
            return guestPhone;
        }

        public void setGuestPhone(String guestPhone) {
            this.guestPhone = guestPhone;
        }

        public String getOta() {
            return ota;
        }

        public void setOta(String ota) {
            this.ota = ota;
        }

        public String getGuestRemark() {
            return guestRemark;
        }

        public void setGuestRemark(String guestRemark) {
            this.guestRemark = guestRemark;
        }
    }

    /**
     * 每日明细
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyInfo {

        /**
         * 房间序号
         */
        private String roomName;

        /**
         * 房间序号
         */
        private Integer roomNo;

        /**
         * 入住日期
         */
        private String checkDate;

        /**
         * 价格；格式为 yyyy-MM-dd
         */
        private BigDecimal rate;

        /**
         * 结算价格
         */
        private BigDecimal settleRate;

        /**
         * 底价类型/结算方式；底价结算: 1，卖价扣点: 0
         */
        private Integer basePriceSettleType;

        /**
         * 底价取消时间戳
         */
        private String basePriceSettleTime;

        /**
         * 底价/扣点后的价格
         */
        private String costOrDeductRate;

        /**
         * 底价取消时间戳；Unix时间戳
         */
        private String basePriceCancelTime;

        /**
         * 币种；默认：CNY
         */
        private String currency;

        /**
         * 备注
         */
        private String remark;

        /**
         * 客户外部渠道房价
         */
        private BigDecimal outerChannelRate;

        /**
         * 每日明细扩展
         */
        private DailyInfoExt dailyInfoExt;

        // Getters and Setters

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public Integer getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(Integer roomNo) {
            this.roomNo = roomNo;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public BigDecimal getSettleRate() {
            return settleRate;
        }

        public void setSettleRate(BigDecimal settleRate) {
            this.settleRate = settleRate;
        }

        public Integer getBasePriceSettleType() {
            return basePriceSettleType;
        }

        public void setBasePriceSettleType(Integer basePriceSettleType) {
            this.basePriceSettleType = basePriceSettleType;
        }

        public String getBasePriceSettleTime() {
            return basePriceSettleTime;
        }

        public void setBasePriceSettleTime(String basePriceSettleTime) {
            this.basePriceSettleTime = basePriceSettleTime;
        }

        public String getCostOrDeductRate() {
            return costOrDeductRate;
        }

        public void setCostOrDeductRate(String costOrDeductRate) {
            this.costOrDeductRate = costOrDeductRate;
        }

        public String getBasePriceCancelTime() {
            return basePriceCancelTime;
        }

        public void setBasePriceCancelTime(String basePriceCancelTime) {
            this.basePriceCancelTime = basePriceCancelTime;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public BigDecimal getOuterChannelRate() {
            return outerChannelRate;
        }

        public void setOuterChannelRate(BigDecimal outerChannelRate) {
            this.outerChannelRate = outerChannelRate;
        }

        public DailyInfoExt getDailyInfoExt() {
            return dailyInfoExt;
        }

        public void setDailyInfoExt(DailyInfoExt dailyInfoExt) {
            this.dailyInfoExt = dailyInfoExt;
        }
    }

    /**
     * 每日明细扩展
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyInfoExt {

        /**
         * 餐食类型
         */
        private Integer mealType;

        /**
         * 餐食份数
         */
        private Integer mealCount;

        /**
         * 预定确认类型
         */
        private Integer bookingConfirmType;

        // Getters and Setters

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
     * 联系人信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contact {

        /**
         * 联系人姓名
         */
        private String name;

        /**
         * 联系人电话
         */
        private String phone;

        /**
         * 联系人邮箱
         */
        private String email;

        // Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    /**
     * 发票信息
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Invoice {

        /**
         * 发票类型
         */
        private Integer invoiceType;

        /**
         * 发票抬头
         */
        private String invoiceTitle;

        // Getters and Setters

        public Integer getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(Integer invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }
    }

    /**
     * 客户权益
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GuestBenefit {

        /**
         * 权益名称
         */
        private String benefitName;

        /**
         * 权益份数
         */
        private Integer quantity;

        /**
         * 权益生效日期；yyyy-MM-dd
         */
        private String effectDate;

        /**
         * 权益描述
         */
        private String benefitDesc;

        // Getters and Setters

        public String getBenefitName() {
            return benefitName;
        }

        public void setBenefitName(String benefitName) {
            this.benefitName = benefitName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getEffectDate() {
            return effectDate;
        }

        public void setEffectDate(String effectDate) {
            this.effectDate = effectDate;
        }

        public String getBenefitDesc() {
            return benefitDesc;
        }

        public void setBenefitDesc(String benefitDesc) {
            this.benefitDesc = benefitDesc;
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
         * 取消时间类型
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
         * 入住前 n 小时
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
