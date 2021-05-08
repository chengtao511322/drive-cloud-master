package com.drive.common.core.exception;

/**
 * 自定义异常
 *
 * @author xiaoguo
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;

    private String message;
    private String subCode;
    private String subMsg;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String code, String message) {
        this.message = message;
        this.code = code;
    }
    public CustomException(String code, String message,String subCode,String subMsg) {
        this.message = message;
        this.code = code;
        this.subCode = subCode;
        this.subMsg = subMsg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public String getSubCode() {
        return subCode;
    }



    public String getSubMsg() {
        return subMsg;
    }


}
