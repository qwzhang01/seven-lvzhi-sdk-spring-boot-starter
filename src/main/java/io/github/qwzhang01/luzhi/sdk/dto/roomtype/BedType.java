package io.github.qwzhang01.luzhi.sdk.dto.roomtype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 床型信息
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BedType {

    /**
     * 床型
     */
    private String bedType;

    /**
     * 床型描述
     */
    private String bedTypeDesc;

    /**
     * 床型规格编码
     */
    private String bedSpecCode;

    /**
     * 床型规格描述
     */
    private String bedSpecDesc;

    /**
     * 床型数量
     */
    private Integer bedNum;

    // Getters and Setters

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedTypeDesc() {
        return bedTypeDesc;
    }

    public void setBedTypeDesc(String bedTypeDesc) {
        this.bedTypeDesc = bedTypeDesc;
    }

    public String getBedSpecCode() {
        return bedSpecCode;
    }

    public void setBedSpecCode(String bedSpecCode) {
        this.bedSpecCode = bedSpecCode;
    }

    public String getBedSpecDesc() {
        return bedSpecDesc;
    }

    public void setBedSpecDesc(String bedSpecDesc) {
        this.bedSpecDesc = bedSpecDesc;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }
}
