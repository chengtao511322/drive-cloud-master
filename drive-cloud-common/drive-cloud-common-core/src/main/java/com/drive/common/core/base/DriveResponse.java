package com.drive.common.core.base;

import lombok.Data;

import java.util.Map;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 返回值
 * @Param $
 * @return $
 **/
@Data
public abstract class DriveResponse<T> implements java.io.Serializable{
    // code
    private String code;
    // 消息
    private String msg;
    // 业务码
    private String subCode;
    // 业务消息
    private String subMsg;
    // 返回数据
    private T data;
}
