package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 产品信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 酒店VID
     */
    private Long hotelVid;

    /**
     * 房型ID（酒店下唯一）
     */
    private Long roomTypeId;

    /**
     * 产品类型：
     * <ul>
     *   <li>0: 正常</li>
     *   <li>1: 钟点房</li>
     * </ul>
     */
    private Integer productType;

    /**
     * 销售类型
     */
    private Integer saleType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 早餐类型：
     * <ul>
     *   <li>0: 无早</li>
     *   <li>1: 单早</li>
     *   <li>2: 双早</li>
     *   <li>3: 三早</li>
     *   <li>4: 四早及以上</li>
     * </ul>
     */
    private Integer breakfastType;

    /**
     * 早餐数量(份)
     */
    private Integer breakfastNum;

    /**
     * 早餐说明
     */
    private String breakfastDesc;

    /**
     * 床型说明
     */
    private String bedDesc;

    /**
     * 床型：
     * <ul>
     *   <li>0: 大床</li>
     *   <li>1: 双床</li>
     *   <li>2: 大/双床</li>
     *   <li>3: 上下铺</li>
     *   <li>4: 单人床</li>
     *   <li>5: 圆床</li>
     *   <li>6: 拼床</li>
     *   <li>7: 榻榻米</li>
     *   <li>8: 水床</li>
     *   <li>9: 炕</li>
     *   <li>10: 其他</li>
     * </ul>
     */
    private Integer bedType;

    /**
     * 服务费描述
     */
    private String serviceFeeDesc;

    /**
     * 加床描述
     */
    private String addBedDesc;

    /**
     * 网络描述
     */
    private String netDesc;

    /**
     * 最大入住人数
     */
    private Integer maxOccupancy;

    /**
     * 加床价格(分)
     */
    private Long addBedPrice;

    /**
     * 钟点房类型：
     * <ul>
     *   <li>1: 时段型</li>
     *   <li>2: 时长型</li>
     * </ul>
     */
    private Integer partTimeType;

    /**
     * 可入住时段（仅钟点房）
     */
    private List<PartTimeSection> partTimeSections;

    /**
     * 最早入住时间（时段型：最早入住时段开始时间，如08:00；时长型：最早入住时间，如08:00）
     */
    private String startCheckInTime;

    /**
     * 最晚入住时间（时段型：最晚入住时段开始时间，如19:00；时长型：最晚入住时间，如22:00）
     */
    private String lastCheckInTime;

    /**
     * 入住时长(小时)
     */
    private Integer checkInHour;

    /**
     * 发票类型（按位与）：
     * <ul>
     *   <li>1: 普通发票</li>
     *   <li>2: 增值税发票</li>
     *   <li>4: 不开发票</li>
     * </ul>
     */
    private Integer invoiceType;

    /**
     * 发票方式（按位与）：
     * <ul>
     *   <li>1: 前台发票</li>
     *   <li>2: 邮寄发票</li>
     * </ul>
     */
    private Integer invoiceMode;

    /**
     * 结算价格（分）
     */
    private Long basePrice;

    /**
     * 销售价格（分）
     */
    private Long salePrice;

    /**
     * 最大间数
     */
    private Integer maxRoom;

    /**
     * 支付类型：
     * <ul>
     *   <li>0: 预付</li>
     *   <li>1: 现付</li>
     *   <li>2: 信用住</li>
     * </ul>
     */
    private Integer payType;

    /**
     * 确认类型：
     * <ul>
     *   <li>1: 立即确认</li>
     *   <li>2: 延时确认</li>
     * </ul>
     */
    private Integer confirmType;

    /**
     * 库存数
     */
    private Integer stockCount;

    /**
     * 取消规则集合
     */
    private List<SampleCancelRule> cancelRules;

    /**
     * 担保规则
     */
    private SampleGuaranteeRule guaranteeRule;

    /**
     * 价格日历
     */
    private List<SamplePriceCalendar> priceCalendars;

    /**
     * 产品扩展信息
     */
    private String extraInfo;

    /**
     * 是否开通会员：
     * <ul>
     *   <li>0: 未开通会员</li>
     *   <li>1: 开通会员</li>
     * </ul>
     */
    private Integer isMember;

    /**
     * 会员价格（分）
     */
    private Long memberPrice;

    /**
     * 会员价结算价格（分）
     */
    private Long memberBasePrice;

    /**
     * 会员活动类型：
     * <ul>
     *   <li>1: 会员底价</li>
     *   <li>2: 打折</li>
     *   <li>3: 积分</li>
     *   <li>4: 积分+打折</li>
     * </ul>
     */
    private Integer memberActivityType;

    /**
     * 会员价日历（入住日维度）
     */
    private List<SamplePriceCalendar> memberPriceCalendars;

    /**
     * 活动信息集合
     */
    private List<ActivityInfo> activityInfos;

    /**
     * 优惠活动展示类型：
     * <ul>
     *   <li>1: 按比例显示</li>
     *   <li>2: 按首晚减多少显示</li>
     *   <li>3: 按总减多少显示</li>
     *   <li>4: 文案展示</li>
     * </ul>
     */
    private Integer activityShowType;

    /**
     * 活动内容（取决于activityShowType）：
     * <ul>
     *   <li>类型1: 折扣比例，如85折</li>
     *   <li>类型2: 首晚优惠金额</li>
     *   <li>类型3: 总优惠金额</li>
     *   <li>类型4: 展示文案</li>
     * </ul>
     */
    private String activityContent;

    /**
     * 现付产品预付转换的价日历
     */
    private List<SamplePriceCalendar> convertPriceCalendars;

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(Integer breakfastType) {
        this.breakfastType = breakfastType;
    }

    public Integer getBreakfastNum() {
        return breakfastNum;
    }

    public void setBreakfastNum(Integer breakfastNum) {
        this.breakfastNum = breakfastNum;
    }

    public String getBreakfastDesc() {
        return breakfastDesc;
    }

    public void setBreakfastDesc(String breakfastDesc) {
        this.breakfastDesc = breakfastDesc;
    }

    public String getBedDesc() {
        return bedDesc;
    }

    public void setBedDesc(String bedDesc) {
        this.bedDesc = bedDesc;
    }

    public Integer getBedType() {
        return bedType;
    }

    public void setBedType(Integer bedType) {
        this.bedType = bedType;
    }

    public String getServiceFeeDesc() {
        return serviceFeeDesc;
    }

    public void setServiceFeeDesc(String serviceFeeDesc) {
        this.serviceFeeDesc = serviceFeeDesc;
    }

    public String getAddBedDesc() {
        return addBedDesc;
    }

    public void setAddBedDesc(String addBedDesc) {
        this.addBedDesc = addBedDesc;
    }

    public String getNetDesc() {
        return netDesc;
    }

    public void setNetDesc(String netDesc) {
        this.netDesc = netDesc;
    }

    public Integer getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(Integer maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public Long getAddBedPrice() {
        return addBedPrice;
    }

    public void setAddBedPrice(Long addBedPrice) {
        this.addBedPrice = addBedPrice;
    }

    public Integer getPartTimeType() {
        return partTimeType;
    }

    public void setPartTimeType(Integer partTimeType) {
        this.partTimeType = partTimeType;
    }

    public List<PartTimeSection> getPartTimeSections() {
        return partTimeSections;
    }

    public void setPartTimeSections(List<PartTimeSection> partTimeSections) {
        this.partTimeSections = partTimeSections;
    }

    public String getStartCheckInTime() {
        return startCheckInTime;
    }

    public void setStartCheckInTime(String startCheckInTime) {
        this.startCheckInTime = startCheckInTime;
    }

    public String getLastCheckInTime() {
        return lastCheckInTime;
    }

    public void setLastCheckInTime(String lastCheckInTime) {
        this.lastCheckInTime = lastCheckInTime;
    }

    public Integer getCheckInHour() {
        return checkInHour;
    }

    public void setCheckInHour(Integer checkInHour) {
        this.checkInHour = checkInHour;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(Integer invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getMaxRoom() {
        return maxRoom;
    }

    public void setMaxRoom(Integer maxRoom) {
        this.maxRoom = maxRoom;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public List<SampleCancelRule> getCancelRules() {
        return cancelRules;
    }

    public void setCancelRules(List<SampleCancelRule> cancelRules) {
        this.cancelRules = cancelRules;
    }

    public SampleGuaranteeRule getGuaranteeRule() {
        return guaranteeRule;
    }

    public void setGuaranteeRule(SampleGuaranteeRule guaranteeRule) {
        this.guaranteeRule = guaranteeRule;
    }

    public List<SamplePriceCalendar> getPriceCalendars() {
        return priceCalendars;
    }

    public void setPriceCalendars(List<SamplePriceCalendar> priceCalendars) {
        this.priceCalendars = priceCalendars;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getMemberBasePrice() {
        return memberBasePrice;
    }

    public void setMemberBasePrice(Long memberBasePrice) {
        this.memberBasePrice = memberBasePrice;
    }

    public Integer getMemberActivityType() {
        return memberActivityType;
    }

    public void setMemberActivityType(Integer memberActivityType) {
        this.memberActivityType = memberActivityType;
    }

    public List<SamplePriceCalendar> getMemberPriceCalendars() {
        return memberPriceCalendars;
    }

    public void setMemberPriceCalendars(List<SamplePriceCalendar> memberPriceCalendars) {
        this.memberPriceCalendars = memberPriceCalendars;
    }

    public List<ActivityInfo> getActivityInfos() {
        return activityInfos;
    }

    public void setActivityInfos(List<ActivityInfo> activityInfos) {
        this.activityInfos = activityInfos;
    }

    public Integer getActivityShowType() {
        return activityShowType;
    }

    public void setActivityShowType(Integer activityShowType) {
        this.activityShowType = activityShowType;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public List<SamplePriceCalendar> getConvertPriceCalendars() {
        return convertPriceCalendars;
    }

    public void setConvertPriceCalendars(List<SamplePriceCalendar> convertPriceCalendars) {
        this.convertPriceCalendars = convertPriceCalendars;
    }

    // 别名方法（保持兼容性）
    public Long getId() {
        return productId;
    }

    public String getName() {
        return productName;
    }
}
