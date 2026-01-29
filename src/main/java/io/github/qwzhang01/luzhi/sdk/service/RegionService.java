package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.region.ListRegionTreesRequest;
import io.github.qwzhang01.luzhi.sdk.dto.region.RegionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 静态信息服务
 * <p>
 * 对应API分组：静态信息接口
 *
 * @author lvzhi-drp-starter
 */
public class RegionService {

    private static final Logger logger =
            LoggerFactory.getLogger(RegionService.class);

    private final LvzhiDrpClient client;

    public RegionService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 查询三级城市信息（树形结构）
     *
     * @param provinceId 省份ID（可选）
     * @return 区域树列表
     */
    public BaseResponse<List<RegionInfo>> listRegionTrees(Integer provinceId) {
        logger.debug("查询区域树, provinceId: {}", provinceId);

        ListRegionTreesRequest request = new ListRegionTreesRequest();
        request.setProvinceId(provinceId);

        return client.execute(
                ApiMethod.REGION_LIST_TREES,
                request,
                new TypeReference<BaseResponse<List<RegionInfo>>>() {
                }
        );
    }
}
