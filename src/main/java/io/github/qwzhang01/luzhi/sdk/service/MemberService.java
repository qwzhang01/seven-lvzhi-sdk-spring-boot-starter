package io.github.qwzhang01.luzhi.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.dto.member.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会员服务
 * <p>
 * 对应API分组：会员接口
 *
 * @author lvzhi-drp-starter
 */
public class MemberService {

    private static final Logger logger =
            LoggerFactory.getLogger(MemberService.class);

    private final LvzhiDrpClient client;

    public MemberService(LvzhiDrpClient client) {
        this.client = client;
    }

    /**
     * 会员绑定
     *
     * @param blocCode    集团编码（必填）
     * @param eventType   操作类型：1-绑定，2-升级（必填）
     * @param memberNo    会员卡号（升级时必填）
     * @param phoneNo     手机号（必填）
     * @param name        姓名（非必填）
     * @param idType      证件类型：0-身份证（必填）
     * @param idNo        证件号码（非必填）
     * @param memberLevel 会员等级（必填）
     * @return 绑定结果
     */
    public BaseResponse<BindMemberResponse> bind(String blocCode,
                                                 Integer eventType,
                                                 String memberNo,
                                                 String phoneNo, String name,
                                                 Integer idType,
                                                 String idNo,
                                                 Integer memberLevel) {
        logger.debug("会员绑定, blocCode: {}, eventType: {}, phoneNo: {}, " +
                        "memberLevel: {}",
                blocCode, eventType, phoneNo, memberLevel);

        BindMemberRequest request = new BindMemberRequest();
        request.setBlocCode(blocCode);
        request.setEventType(eventType);
        request.setMemberNo(memberNo);
        request.setPhoneNo(phoneNo);
        request.setName(name);
        request.setIdType(idType);
        request.setIdNo(idNo);
        request.setMemberLevel(memberLevel);

        return client.execute(
                ApiMethod.MEMBER_BIND,
                request,
                new TypeReference<BaseResponse<BindMemberResponse>>() {
                }
        );
    }

    /**
     * 会员信息查询
     *
     * @param blocCode 集团code（必填）
     * @param memberNo 会员卡号（必填）
     * @param phoneNo  手机号（必填）
     * @return 会员信息
     */
    public BaseResponse<MemberInfo> search(String blocCode, String memberNo,
                                           Long phoneNo) {
        logger.debug("会员信息查询, blocCode: {}, memberNo: {}, phoneNo: {}",
                blocCode, memberNo, phoneNo);

        SearchMemberRequest request = new SearchMemberRequest();
        request.setBlocCode(blocCode);
        request.setMemberNo(memberNo);
        request.setPhoneNo(phoneNo);

        return client.execute(
                ApiMethod.MEMBER_SEARCH,
                request,
                new TypeReference<BaseResponse<MemberInfo>>() {
                }
        );
    }

    /**
     * 会员信息变化通知
     *
     * @param memberNo    会员卡号（必填）
     * @param phoneNo     手机号（必填）
     * @param blocCode    集团code（必填）
     * @param memberLevel 会员等级（必填）
     * @param name        姓名（非必填）
     * @return 通知结果
     */
    public BaseResponse<MemberNotifyResponse> notify(String memberNo,
                                                     String phoneNo,
                                                     String blocCode,
                                                     String memberLevel,
                                                     String name) {
        logger.debug("会员信息变化通知, memberNo: {}, phoneNo: {}, blocCode: {}, " +
                        "memberLevel: {}",
                memberNo, phoneNo, blocCode, memberLevel);

        MemberNotifyRequest request = new MemberNotifyRequest();
        request.setMemberNo(memberNo);
        request.setPhoneNo(phoneNo);
        request.setBlocCode(blocCode);
        request.setMemberLevel(memberLevel);
        request.setName(name);

        return client.execute(
                ApiMethod.MEMBER_NOTIFY,
                request,
                new TypeReference<BaseResponse<MemberNotifyResponse>>() {
                }
        );
    }
}
