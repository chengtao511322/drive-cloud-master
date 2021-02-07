package com.drive.admin.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


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