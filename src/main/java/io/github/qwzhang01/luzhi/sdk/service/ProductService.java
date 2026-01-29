package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.common.PageData;
import io.github.qwzhang01.luzhi.sdk.dto.product.GetProductByIdRequest;
import io.github.qwzhang01.luzhi.sdk.dto.product.ListProductRequest;
import io.github.qwzhang01.luzhi.sdk.dto.product.ProductDetail;
import io.github.qwzhang01.luzhi.sdk.dto.product.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 产品服务
 * <p>
 * 对应API分组：产品接口
 *
 * @author lvzhi-drp-starter
 */
public class ProductService {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductService.class);

    private final LvzhiDrpClient client;

    public ProductService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 查询酒店产品列表
     *
     * @param hotelVid 酒店VID（必填）
     * @return 产品列表
     */
    public BaseResponse<ProductInfo> list(Long hotelVid) {
        logger.debug("查询产品列表, hotelVid: {}", hotelVid);

        ListProductRequest request = new ListProductRequest();
        request.setHotelVid(hotelVid);

        return client.execute(
                ApiMethod.PRODUCT_LIST,
                request,
                new TypeReference<BaseResponse<ProductInfo>>() {
                }
        );
    }

    /**
     * 获取产品详情
     *
     * @param hotelVid  酒店VID（必填）
     * @param productId 产品ID（必填）
     * @return 产品详情
     */
    public BaseResponse<ProductDetail> getById(Long hotelVid, Long productId) {
        logger.debug("获取产品详情, hotelVid: {}, productId: {}", hotelVid,
                productId);

        GetProductByIdRequest request = new GetProductByIdRequest();
        request.setHotelVid(hotelVid);
        request.setProductId(productId);

        return client.execute(
                ApiMethod.PRODUCT_GET_BY_ID,
                request,
                new TypeReference<BaseResponse<ProductDetail>>() {
                }
        );
    }
}
