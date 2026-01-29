package io.github.qwzhang01.luzhi.sdk.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 会员信息变化通知请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberNotifyRequest {

    /**
     * 会员卡号
     */
    private String memberNo;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 集团code
     */
    private String blocCode;

    /**
     * 会员等级
     */
    private String memberLevel;

    /**
     * 姓名
     */
    private String name;

    // Getters and Setters

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

    public String getBlocCode() {
        return blocCode;
    }

    public void setBlocCode(String blocCode) {
        this.blocCode = blocCode;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
