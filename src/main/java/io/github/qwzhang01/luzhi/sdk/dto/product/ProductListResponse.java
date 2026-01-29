package io.github.qwzhang01.luzhi.sdk.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * 产品列表响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductListResponse {

    /**
     * 酒店VID
     */
    private Long hotelVid;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 价格方案列表
     */
    private List<RatePlan> ratePlans;

    /**
     * 取消规则列表
     */
    private List<CancelRule> cancelRules;

    /**
     * 预订规则列表
     */
    private List<BookingRule> bookingRules;

    /**
     * 产品列表
     */
    private List<ProductInfo> products;

    // Getters and Setters

    public Long getHotelVid() {
        return hotelVid;
    }

    public void setHotelVid(Long hotelVid) {
        this.hotelVid = hotelVid;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<RatePlan> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<RatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }

    public List<CancelRule> getCancelRules() {
        return cancelRules;
    }

    public void setCancelRules(List<CancelRule> cancelRules) {
        this.cancelRules = cancelRules;
    }

    public List<BookingRule> getBookingRules() {
        return bookingRules;
    }

    public void setBookingRules(List<BookingRule> bookingRules) {
        this.bookingRules = bookingRules;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}