package com.drive.marketing.pojo.vo;

import com.drive.admin.pojo.vo.RecommendUserVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ActivityCouponGetVo implements java.io.Serializable{

    // 用户ID
    @ApiModelProperty(value = "用户ID")
    private String userId;
    // 优惠券ID
    @ApiModelProperty(value = "优惠券ID")
    private String couponId;
    // 状态
    private String status;
    @ApiModelProperty(value = "优惠券状态")
    private String statusName;
    // 领取时间
    @ApiModelProperty(value = "领取时间")
    private String getTime;
    //
    @ApiModelProperty(value = "优惠券码")
    private String couponCode;
    //
    @ApiModelProperty(value = "使用开始时间")
    private String periodTimeStart;

    @ApiModelProperty(value = "使用结束时间")
    private String periodTimeEnd;


    private Integer type;
    // 优惠券名称
    @ApiModelProperty(value = "优惠券名称")
    private String name;
    // 优惠券金额
    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal amount;
    //
    @ApiModelProperty(value = "优惠券最小使用金额")
    private BigDecimal minPoint;

    @ApiModelProperty(value = "优惠券备注")
    private String note;

    @ApiModelProperty(value = "优惠券使用期限")
    private String useDay;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "学员名称")
    private String userName;
    @ApiModelProperty(value = "学员手机号")
    private String phone;
    @ApiModelProperty(value = "推广商ID")
    private String promoteUserId;
    @ApiModelProperty(value = "推广商名称")
    private String promoteUserName;
    @ApiModelProperty(value = "推广商手机号")
    private String promoteUserPhone;
    @ApiModelProperty(value = "推广商数据")
    private RecommendUserVo promoteUser;

    // 获取类型
    private String getType;
    private String getTypeName;

    public void setStatus(String status) {
        this.status = status;
        switch(status){
            case "1" :
                //语句
                this.statusName = "未领取";
                break; //可选
            case "2" :
                //语句
                this.statusName = "已领取";
                break; //可选
            //你可以有任意数量的case语句
            case "3" :
                //语句
                this.statusName = "已使用";
                break; //可选
            //你可以有任意数量的case语句
            case "4" :
                //语句
                this.statusName = "已失效";
                break; //可选
            //你可以有任意数量的case语句
        }
    }

    public void setGetType(String getType) {
        //获取类型：0->后台赠送；1->主动获取
        this.getType = getType;
        if (getType.equals("0")){
            this.getTypeName = "后台赠送";
        }
        if (getType.equals("1")){
            this.getTypeName = "主动获取";
        }
    }
}
