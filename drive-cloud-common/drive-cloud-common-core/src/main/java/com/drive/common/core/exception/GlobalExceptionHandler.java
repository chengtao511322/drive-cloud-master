/*
package com.drive.common.core.exception;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @ClassName
 * @Description TODO：
 * @Author @{USER} 小郭
 * @Date @{DATE} 18:08
 * @Version 1.0
 **//*

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    */
/**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     *//*

*/
/*    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("发生错误[{},返回错误{}]", e,ResultCode.FAILL);
        base.setResultError(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
           return base.setResultError(ResultCode.NOTFOUND.code(),ResultCode.NOTFOUND.message());
        }
        return base.setResultError(ResultCode.FAILL.code(),ResultCode.FAILL.message());
    }*//*



    */
/**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     *//*


    @ExceptionHandler(CustomException.class)
    public ResObject customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        //response.setStatus(500);
        CustomException exception = (CustomException) e;
        log.info("发生异常[{}],返回错误[{}]", e, exception.getMessage());
        log.error("程序运行发生异常[{}]",exception);
        log.info("异常返回日志[{}]",e.toString());
        return R.failure(Integer.parseInt(exception.getCode()),exception.getMessage(),exception.getSubCode(),exception.getSubMsg());
    }



    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //在这个方法里定义我们需要返回的格式
    public ResObject handleUserNotExistException(CustomException ex){
        ResObject resObject = new ResObject();
        resObject.setCode(Integer.parseInt(ex.getCode()));
        resObject.setMsg(ex.getMessage());
        resObject.setSubCode(ex.getSubCode());
        return resObject;
    }

    */
/**
     * 捕获  RuntimeException 异常
     * TODO  如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     * TODO  那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     *//*

    @ExceptionHandler(RuntimeException.class)
    public ResObject runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        //response.setStatus(ResultCode.BADREQUEST.code());
        RuntimeException exception = (RuntimeException) e;
        log.error("运行期异常[{}],返回错误[{}]", e, exception.getMessage());
        log.error("程序运行发生异常[{},{}]",exception,e.toString());
        return R.failure(SubResultCode.SYSTEM_ERROR.subCode(),SubResultCode.SYSTEM_ERROR.subMsg());
    }

    */
/**
     * 通用的接口映射异常处理方
     *//*

    @ExceptionHandler(Exception.class)
    protected ResObject<Object> handleExceptionInternal(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return R.failure(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            log.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            log.error("程序运行发生异常[{}]",exception);
            //return base.setResultError(Integer.parseInt(status.toString()),"参数转换失败");
            return  R.failure(SubResultCode.ANALYSIS_PARAMS_ERROR.subCode(),SubResultCode.ANALYSIS_PARAMS_ERROR.subMsg());
        }
        return  R.failure(SubResultCode.ANALYSIS_PARAMS_ERROR.subCode(),SubResultCode.ANALYSIS_PARAMS_ERROR.subMsg());
       // return base.setResultError(Integer.parseInt(status.toString()),"参数转换失败");
    }

    */
/**
     * hibernate 参数校验出错会抛出 ConstraintViolationException 异常
     * 在此方法中处理，将错误信息输出
     * @param exception
     * @return
     *//*

 */
/*   @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handle(ValidationException exception) {
       String errorInfo = "";
       *//*
*/
/*  if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();

            for (ConstraintViolation<?> item : violations) {
                errorInfo = errorInfo + "[" + item.getMessage() + "]";
            }
        }*//*
*/
/*
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                //打印验证不通过的信息
                System.out.println(item.getMessage());
                errorInfo +=  item.getMessage();
            }
        }
        return base.setResultError(HttpStatus.BAD_REQUEST.value(),errorInfo);
    }

    *//*
*/
/**
     * 数据校验异常
     *
     * @param e 数据校验异常
     * @return
     *//*
*/
/*
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleResourceBindException(BindException e) {
        log.error("业务异常，{},{},{}",  400, e.getMessage());
        return base.setResultError(400,"参数错误", e);
    }

    *//*
*/
/**
     * 数据校验异常
     *
     * @param e 数据校验异常
     * @return
     *//*
*/
/*
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleResourceConstraintViolationException(ConstraintViolationException e) {
        log.error("业务异常，{},{},{}",  400, e.getMessage());
        return base.setResultError(400,"参数错误", e);
    }*//*


    */
/**
     * 400 - Bad Request
     *//*

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResObject<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return R.failure(SubResultCode.PARAMNOTCOMPLETE.subCode(),SubResultCode.PARAMNOTCOMPLETE.subMsg());
        // return base.setResultError(HttpStatus.BAD_REQUEST.value(),"缺少请求参数"+e.getMessage());
    }
    */
/**
     * 400 - Bad Request
     *//*

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResObject<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        return R.failure(SubResultCode.ANALYSIS_PARAMS_ERROR.subCode(),SubResultCode.ANALYSIS_PARAMS_ERROR.subMsg());
       // return base.setResultError(HttpStatus.BAD_REQUEST.value(),"参数解析失败"+e.getMessage());
    }

    */
/**
     * 400 - Bad Request
     *//*

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResObject<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return R.failure(SubResultCode.PARAMNOTCOMPLETE.subCode(),SubResultCode.PARAMNOTCOMPLETE.subMsg());
        //  return base.setResultError(HttpStatus.BAD_REQUEST.value(),"参数验证失败="+message);
    }

    */
/**
     * 400 - Bad Request
     *//*

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResObject<Object> handleBindException(BindException e) {
        log.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return R.failure(SubResultCode.SYSTEM_FAILL.subCode(),"参数绑定失败="+message);
    }





    */
/**
     * 415 - Unsupported Media Type
     *//*

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResObject<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型", e);
        return R.failure(SubResultCode.SYSTEM_FAILL.subCode(),"系统检测到你的请求非法哦~" + e.getMessage());
    }

}
*/
