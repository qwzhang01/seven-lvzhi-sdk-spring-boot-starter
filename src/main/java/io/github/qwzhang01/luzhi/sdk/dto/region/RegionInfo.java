package io.github.qwzhang01.luzhi.sdk.dto.region;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 区域信息
 * <p>
 * 层级结构说明：
 * <ul>
 *   <li>第一层：省/直辖市/自治区/特别行政区</li>
 *   <li>第二层：市/地级市</li>
 *   <li>第三层：区/县</li>
 * </ul>
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionInfo {

    /**
     * 国家id（第一层为国家id）/ 省id（第二层为省id）/ 市id（第三层为市id）
     */
    private Integer pid;

    /**
     * 省id（第一层）/ 市id（第二层）/ 区id（第三层）
     */
    private Integer id;

    /**
     * 级别
     * <ul>
     *   <li>0: 洲</li>
     *   <li>1: 国</li>
     *   <li>2: 省</li>
     *   <li>3: 市</li>
     *   <li>4: 区</li>
     *   <li>5: 乡</li>
     *   <li>6: 村</li>
     * </ul>
     */
    private Integer level;

    /**
     * 类型
     * <ul>
     *   <li>0: 正常级别(国, 省, 市, 县, 乡镇)</li>
     *   <li>1: 直辖市</li>
     *   <li>2: 自治区</li>
     *   <li>3: 特别行政区</li>
     *   <li>4: 自治州</li>
     *   <li>5: 地区</li>
     *   <li>6: 县级市</li>
     *   <li>7: 行政区</li>
     *   <li>8: 自治县</li>
     *   <li>9: 省直辖行政单位</li>
     *   <li>10: 市直辖</li>
     * </ul>
     */
    private Integer type;

    /**
     * 省名称（第一层）/ 市名称（第二层）/ 区名称（第三层）
     */
    private String name;

    /**
     * 子区域列表
     * <ul>
     *   <li>第一层children: 城市信息列表</li>
     *   <li>第二层children: 区信息列表</li>
     * </ul>
     */
    private List<RegionInfo> children;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RegionInfo> getChildren() {
        return children;
    }

    public void setChildren(List<RegionInfo> children) {
        this.children = children;
    }
}
