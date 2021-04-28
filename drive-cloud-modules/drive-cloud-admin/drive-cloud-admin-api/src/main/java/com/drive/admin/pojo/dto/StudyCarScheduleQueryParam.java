package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 平台账务流水明细
 *
 * @author xiaoguo
 */
@Data
public class StudyCarScheduleQueryParam {

    //姓名
    @ApiModelProperty(value = "姓名")
    private String realName;

    //运营中心
    @ApiModelProperty(value = "电话")
    private String phone;


    //运营中心
    @ApiModelProperty(value = "运营中心")
    private String operatorId;

}