package io.github.qwzhang01.luzhi.sdk.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 查询会员请求
 *
 * @author lvzhi-drp-starter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMemberRequest {

    /**
     * 集团code
     */
    private String blocCode;

    /**
     * 会员卡号
     */
    private String memberNo;

    /**
     * 手机号
     */
    private Long phoneNo;

    // Getters and Setters

    public String getBlocCode() {
        return blocCode;
    }

    public void setBlocCode(String blocCode) {
        this.blocCode = blocCode;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
}
