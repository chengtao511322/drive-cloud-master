package com.drive.monitor.pojo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 系统访问记录
 *
 * @author DreamChan
 */
@Data
public class LoginLogEditParam {


    // 访问ID
    private Long loginLogId;

    // 用户Id
    private Long userId;

    // 用户账号
    private String userName;

    // 登录状态（0成功 1失败）
    private String status;

    // 登录IP地址
    private String ipaddr;

    // 登录时间
    private LocalDateTime loginTime;

    // 浏览器类型
    private String browserName;

    // 操作系统
    private String osName;

    // 记录信息
    private String message;


}