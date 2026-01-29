package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 产品样本信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail {

    /**
     * 酒店ID
     */
    private Long hotelVid;

    /**
     * 母酒店ID
     */
    private Long hotelid;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 产品类型：1-直采，2-代理
     */
    private Integer productType;

    /**
     * 结算类型：
     * <ul>
     *   <li>1: 先付</li>
     *   <li>2: 现付</li>
     *   <li>3: 信用住</li>
     *   <li>4: 闪住</li>
     *   <li>5: 后付</li>
     * </ul>
     */
    private Integer settlementType;

    /**
     * 分时类型：
     * <ul>
     *   <li>0: 非钟点房</li>
     *   <li>1: 钟点房</li>
     *   <li>2: 既是也是（非必传，默认0）</li>
     * </ul>
     */
    private Integer partTimeType;

    /**
     * 库存类型：
     * <ul>
     *   <li>1: 买断/包房</li>
     *   <li>2: 超卖</li>
     *   <li>3: 现询</li>
     *   <li>4: 保留房</li>
     * </ul>
     */
    private Integer roomShareType;

    /**
     * 早餐类型：
     * <ul>
     *   <li>0: 不含早</li>
     *   <li>1: 单早</li>
     *   <li>2: 双早</li>
     *   <li>3: 三早</li>
     *   <li>4: 四早</li>
     *   <li>5: 多早</li>
     * </ul>
     */
    private Integer breakfastType;

    /**
     * 分销产品关联ID
     */
    private Long ratePlanId;

    /**
     * 分时区间信息集合
     */
    private List<PartTimeSection> partTimeSections;

    /**
     * 担保规则
     */
    private SampleGuaranteeRule guaranteeRule;

    /**
     * 取消规则
     */
    private SampleCancelRule cancelRule;

    /**
     * 价格日历
     */
    private List<SamplePriceCalendar> priceCalendars;

    /**
     * 试单结果类型
     */
    private Integer checkOrderResultType;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public Long getHotelid() {
        return hotelid;
    }

    public void setHotelid(Long hotelid) {
        this.hotelid = hotelid;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    public Integer getPartTimeType() {
        return partTimeType;
    }

    public void setPartTimeType(Integer partTimeType) {
        this.partTimeType = partTimeType;
    }

    public Integer getRoomShareType() {
        return roomShareType;
    }

    public void setRoomShareType(Integer roomShareType) {
        this.roomShareType = roomShareType;
    }

    public Integer getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(Integer breakfastType) {
        this.breakfastType = breakfastType;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public List<PartTimeSection> getPartTimeSections() {
        return partTimeSections;
    }

    public void setPartTimeSections(List<PartTimeSection> partTimeSections) {
        this.partTimeSections = partTimeSections;
    }

    public SampleGuaranteeRule getGuaranteeRule() {
        return guaranteeRule;
    }

    public void setGuaranteeRule(SampleGuaranteeRule guaranteeRule) {
        this.guaranteeRule = guaranteeRule;
    }

    public SampleCancelRule getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(SampleCancelRule cancelRule) {
        this.cancelRule = cancelRule;
    }

    public List<SamplePriceCalendar> getPriceCalendars() {
        return priceCalendars;
    }

    public void setPriceCalendars(List<SamplePriceCalendar> priceCalendars) {
        this.priceCalendars = priceCalendars;
    }

    public Integer getCheckOrderResultType() {
        return checkOrderResultType;
    }

    public void setCheckOrderResultType(Integer checkOrderResultType) {
        this.checkOrderResultType = checkOrderResultType;
    }
}
