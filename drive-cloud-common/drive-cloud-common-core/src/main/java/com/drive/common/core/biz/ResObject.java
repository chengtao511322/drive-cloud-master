package com.drive.common.core.biz;


import lombok.Data;

import java.util.Calendar;

/**
 * 返回通用Json数据 封装
 *
 * @author xiaoguo
 */
@Data
public class ResObject<T> implements java.io.Serializable{

    // 自定义状态码
    private Integer code;
    // 成功标志  true 成功  false 失败
    private Boolean isSuccess;
    // 消息提示
    private String msg;
    // 时间戳
    private Long timestamp;
    // 返回数据
    private T data;

    private String subCode;

    private String subMsg;

    // 分页总数
    private Long total = 0L;


    public ResObject(){

    }

    public ResObject(Integer code, Boolean isSuccess, String msg){
        this.code = code;
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public ResObject(Integer code, Boolean isSuccess, String msg,String subCode,String subMsg){
        this.code = code;
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
    }
    public ResObject(Integer code, Boolean isSuccess, String msg,String subCode,String subMsg,Long total){
        this.code = code;
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.total = total;
    }

    public long getTimestamp() {
        if (null == timestamp) {
            return Calendar.getInstance().getTimeInMillis();
        }
        return timestamp;
    }


}
