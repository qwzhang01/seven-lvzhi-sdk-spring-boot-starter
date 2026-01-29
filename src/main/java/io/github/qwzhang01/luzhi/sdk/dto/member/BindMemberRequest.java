package io.github.qwzhang01.luzhi.sdk.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 绑定会员请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BindMemberRequest {

    /**
     * 集团编码
     */
    private String blocCode;

    /**
     * 操作类型：1-绑定，2-升级
     */
    private Integer eventType;

    /**
     * 会员卡号（升级时必填）
     */
    private String memberNo;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型（目前只支持身份证）
     * 0: 身份证
     */
    private Integer idType;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 会员等级
     */
    private Integer memberLevel;

    // Getters and Setters

    public String getBlocCode() {
        return blocCode;
    }

    public void setBlocCode(String blocCode) {
        this.blocCode = blocCode;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }
}
