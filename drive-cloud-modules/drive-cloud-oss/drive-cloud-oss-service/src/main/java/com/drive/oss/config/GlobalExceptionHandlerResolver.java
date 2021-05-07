package com.drive.oss.config;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerResolver {
    /**
     * @Descript   统一处理文件过大问题.
     */
 @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResObject handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("上传文件过大 ex={}", e);
        return R.failure(SubResultCode.UPDATE_BIG.subCode(),SubResultCode.UPDATE_BIG.subMsg());
    }
}