package io.github.qwzhang01.luzhi.sdk.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 绑定会员响应
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BindMemberResponse {

    /**
     * 会员等级，枚举值
     */
    private Integer memberLevel;

    /**
     * 会员卡号
     */
    private String memberNo;

    /**
     * 是否是新会员
     */
    private Boolean newMember;

    /**
     * 等级有效期的开始时间，格式：yyyy-MM-dd
     */
    private String effectBeginDate;

    /**
     * 等级有效期的结束时间，格式：yyyy-MM-dd
     */
    private String effectEndDate;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phoneNo;

    /**
     * 注册时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private String registerTime;

    // Getters and Setters

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Boolean getNewMember() {
        return newMember;
    }

    public void setNewMember(Boolean newMember) {
        this.newMember = newMember;
    }

    public String getEffectBeginDate() {
        return effectBeginDate;
    }

    public void setEffectBeginDate(String effectBeginDate) {
        this.effectBeginDate = effectBeginDate;
    }

    public String getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(String effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
