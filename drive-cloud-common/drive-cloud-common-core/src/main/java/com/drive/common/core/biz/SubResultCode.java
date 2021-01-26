package com.drive.common.core.biz;

/**
 * @ClassName SubResultCode
 * @Description TODO：
 * @Author @{用户} 小郭
 * @Date @{时间} 2020/2/21 16:37
 * @Version 1.0
 **/
public enum  SubResultCode {

    /*系统业务错误码*/
    SYSTEM_ERROR("SYSTEM_ERROR","系统繁忙"),
    SYSTEM_SUCCESS("SUCCESS","接口数据请求成功"),
    SYSTEM_FAILL("FAILL","接口数据请求失败"),
    DATA_NULL("DATA_NULL","数据空"),
    DATA_IDEMPOTENT("DATA_IDEMPOTENT","数据不允许重复提交"),
    BIZ_ERROR("BIZ_ERROR","业务异常"),
    UNAUTHORIZED("UNAUTHORIZED","签名错误"),
    VALIDATESIGNERROR("VALIDATESIGNERROR","签名失败"),

    /*参数相关*/
    PARAMISINVALID("PARAMISINVALID","参数·效"),
    ANALYSIS_PARAMS_ERROR("ANALYSIS_PARAMS_ERROR","参数解析/转换失败"),
    PARAMISBLANK("PARAMISBLANK","参数为空"),
    PARAMTYPEBINDERROR("PARAMTYPEBINDERROR","参数类型错误"),
    PARAM_TIME_BIND_ERROR("PARAM_TIME_BIND_ERROR","时间类型错误"),
    PARAMNOTCOMPLETE("PARAMNOTCOMPLETE","参数缺失"),
    /*用户相关*/
    USERNOTLOGGEDIN("USERNOTLOGGEDIN","用户未登陆，访问的接口需要登陆"),
    USERLOGINERROR("USERLOGINERROR","账号不存在，密码错误"),
    USERACCOUNTFORBIDDEN("USERACCOUNTFORBIDDEN","账号已被禁用"),
    USERNOTEXIST("USERNOTEXIST","用户不存在"),

    USER_YET_JSON_ACTIVITY("USER_YET_JSON_ACTIVITY","用户已经参加活动"),


    DATANULL("DATANULL","数据空"),
    DATA_INSTALL_SUCCESS("DATA_INSTALL_SUCCESS","数据添加成功"),
    DATA_INSTALL_FAILL("DATA_INSTALL_FAILL","数据添加失败"),
    DATA_MUST_DISABLE("DATA_MUST_DISABLE","数据必须先停用"),
    DATA_UPDATE_SUCCESS("DATA_UPDATE_SUCCESS","数据修改成功"),
    DATA_UPDATE_FAILL("DATA_UPDATE_FAILL","数据修改失败"),
    DATA_DELETE_SUCCESS("DATA_DELETE_SUCCESS","数据删除成功"),
    DATA_DELETE_FAILL("DATA_DELETE_FAILL","数据删除失败"),
    DATA_SEARCH_SUCCESS("DATA_SEARCH_SUCCESS","数据查询成功"),
    DATA_SEARCH_FAILL("DATA_SEARCH_FAILL","数据查询失败"),

    /*优惠券业务错误码*/
    COUPON_PRESENT_SUCCESS("COUPON_PRESENT_SUCCESS","优惠券领取成功"),
    COUPON_PRESENT_FAILS("COUPON_PRESENT_FAILS","优惠券领取失败"),
    COUPON_ALREADY_FBAGGAGE("COUPON_ALREADY_FBAGGAGE","优惠券已经被领完"),
    COUPON_USER_ALREADY_JSON("COUPON_USER_ALREADY_JSON","该用户已经领过优惠券"),
    COUPON_EXPIRATION_TIME("COUPON_EXPIRATION_TIME","优惠券过期"),
    NOT_COUPON_USE("NOT_COUPON_USE","没有可用优惠券"),
    AREA_NOT_ACTIVITY("AREA_NOT_ACTIVITY","该区域未开通活动"),

    /*活动相关*/
    ACTIVITY_SOLD_OUT("ACTIVITY_SOLD_OUT","活动已经下架"),
    ACTIVITY_PAST("ACTIVITY_PAST","活动过期"),
    USER_ALREADY_JSON_ACTIVITY("USER_ALREADY_JSON_ACTIVITY","该用户已经参加过活动，不可以参加多活动"),
    USER_NO_JSON_ACTIVITY("USER_ALREADY_JSON_ACTIVITY","该用户未参加过活动"),
    USER_SUCCESS_JSON_ACTIVITY("USER_SUCCESS_JSON_ACTIVITY","用户成功参加活动"),
    ACTIVITY_COUPON_ENPTY("ACTIVITY_COUPON_ENPTY","该活动下没有优惠券可领取"),
    ACTIVITY_USER_FORBID_JSON("ACTIVITY_USER_FORBID_JSON","该用户禁止参加该活动"),


    /*支付状态码*/
    TRADE_CLOSED("TRADE_CLOSED","交易关闭"),
    CALL_PAY_FAILL("CALL_PAY_FAILL","调用支付失败"),
    TRADE_FINISHED("TRADE_FINISHED","交易完结"),
    TRADE_SUCCESS("TRADE_SUCCESS","支付成功"),
    WAIT_BUYER_PAY("WAIT_BUYER_PAY","交易创建"),

    /*订单*/
    ORDER_UPDATE_STATUS_SUCCESS("ORDER_UPDATE_STATUS_SUCCESS","订单状态修改成功"),
    ORDER_UPDATE_STATUS_FAILL("ORDER_UPDATE_STATUS_FAILL","订单状态修改失败"),
    ORDER_PAY_DURING("ORDER_PAY_DURING","订单支付中......"),
    ORDER_CREATE_SUCCESS("ORDER_CREATE_SUCCESS","订单创建成功"),

    /*评论回复*/
    ADD_COMMENT_BACK_ERROR("ADD_COMMENT_BACK_ERROR","添加评论失败"),
    ADD_COMMENT_BACK_SUCCESS("ADD_COMMENT_BACK_SUCCESS","添加评论成功"),
    UPDATE_INVITATION_ERROR("UPDATE_INVITATION_ERROR","帖子修改失败"),

    /*用户签到*/
    USER_YET_SIGN("USER_YET_SIGN","已经签到过"),
    USER_SIGN_SUCCESS("USER_SIGN_SUCCESS","签到成功"),
    USER_SIGN_FAIL("USER_SIGN_FAIL","签到失败"),

    /*币*/
    COIN_COUNT_INSUFFICIENT("COIN_COUNT_INSUFFICIENT","币数量不足"),

    ;
    /*状态码*/
    private String subCode;
    /*消息内容*/
    private String subMsg;

    SubResultCode(String subCode, String subMsg){
        this.subCode = subCode;
        this.subMsg = subMsg;
    }
    public String subCode() {
        return this.subCode = subCode;
    }

    public String subMsg(){
        return this.subMsg = subMsg;
    }

}
