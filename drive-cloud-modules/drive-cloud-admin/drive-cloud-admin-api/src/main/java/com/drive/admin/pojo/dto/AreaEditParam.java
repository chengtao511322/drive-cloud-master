package com.drive.admin.pojo.dto;

import lombok.Data;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class AreaEditParam {


    // 区域编码
    private String baCode;

    // 区域名称
    private String baName;

    private String baParentId;


}