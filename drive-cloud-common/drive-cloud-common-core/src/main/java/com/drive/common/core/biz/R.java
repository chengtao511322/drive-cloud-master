package com.drive.common.core.biz;


/**
 * 通用返回结果
 *
 * @author xiaoguo
 */
public class R implements java.io.Serializable{

	private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
	private static final String DEFAULT_FAILURE_MESSAGE = "操作失败";
	private static final boolean DEFAULT_SUCCESS = true;
	private static final boolean DEFAULT_FAILURE = false;


	public static ResObject success() {
		return new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE,SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.SYSTEM_SUCCESS.subMsg());
	}




	public static  ResObject success(Object data) {
		ResObject resObject = new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE,SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.SYSTEM_SUCCESS.subMsg());
		resObject.setData(data);
		return resObject;
	}

	public static <T> ResObject<T> success1(T data) {
		ResObject resObject = new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE,SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.SYSTEM_SUCCESS.subMsg());
		resObject.setData(data);
		return resObject;
	}


	public static ResObject success(Object data,Long total) {
		ResObject resObject = new ResObject(ResCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS, DEFAULT_SUCCESS_MESSAGE,SubResultCode.SYSTEM_SUCCESS.subCode(),SubResultCode.SYSTEM_SUCCESS.subMsg());
		resObject.setData(data);
		resObject.setTotal(total);
		return resObject;
	}

	public static ResObject success(String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(message);
		// resObject
		resObject.setSubCode(SubResultCode.SYSTEM_SUCCESS.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_SUCCESS.subMsg());
		return resObject;
	}

	public static ResObject success(String message, Object data) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(message);
		resObject.setData(data);
		resObject.setSubCode(SubResultCode.SYSTEM_SUCCESS.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_SUCCESS.subMsg());
		return resObject;
	}

	/**
	 * 成功；自定义业务码
	 * @param subCode 
	 * @param subMsg
	 * @return
	 */
	public static ResObject success(String subCode, String subMsg) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(ResCodeEnum.SUCCESS.getMsg());
		resObject.setSubCode(subCode);
		resObject.setSubMsg(subMsg);
		return resObject;
	}

	/**
	 * 成功 自定义业务码处理
	 * @param subCode
	 * @param subMsg
	 * @param object
	 * @return
	 */
	public static ResObject success(String subCode, String subMsg,Object object) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.SUCCESS.getCode());
		resObject.setIsSuccess(DEFAULT_SUCCESS);
		resObject.setMsg(ResCodeEnum.SUCCESS.getMsg());
		resObject.setSubCode(subCode);
		resObject.setSubMsg(subMsg);
		resObject.setData(object);
		return resObject;
	}


	/**
	 * 失败
	 * @return
	 */
	public static ResObject failure() {
		return new ResObject(ResCodeEnum.FAILURE.getCode(), DEFAULT_FAILURE, DEFAULT_FAILURE_MESSAGE,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg());
	}

	public static ResObject failure(ResCodeEnum resCodeEnum) {
		ResObject resObject = new ResObject();
		resObject.setCode(resCodeEnum.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(resCodeEnum.getMsg());
		// ,SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg()
		resObject.setSubCode(SubResultCode.SYSTEM_FAILL.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_FAILL.subMsg());
		return resObject;
	}

	/**
	 * 返回错误消息
	 * @param data
	 * @return
	 */
	public static ResObject failure(Object data) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.ERROR.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(ResCodeEnum.ERROR.getMsg());
		// SubResultCode.SYSTEM_FAILL.subCode(),SubResultCode.SYSTEM_FAILL.subMsg()
		resObject.setSubCode(SubResultCode.SYSTEM_FAILL.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_FAILL.subMsg());
		resObject.setData(data);
		return resObject;
	}

	public static ResObject failure(String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.FAILURE.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(message);
		resObject.setSubCode(SubResultCode.SYSTEM_FAILL.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_FAILL.subMsg());
		return resObject;
	}


	public static ResObject failure(ResCodeEnum resCodeEnum, String message) {
		ResObject resObject = new ResObject();
		resObject.setCode(resCodeEnum.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(message);
		resObject.setSubCode(SubResultCode.SYSTEM_FAILL.subCode());
		resObject.setSubMsg(SubResultCode.SYSTEM_FAILL.subMsg());
		return resObject;
	}

	public static ResObject failure(String subCode, String subMsg) {
		ResObject resObject = new ResObject();
		resObject.setCode(ResCodeEnum.FAILURE.getCode());
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(ResCodeEnum.FAILURE.getMsg());
		resObject.setSubCode(subCode);
		resObject.setSubMsg(subMsg);
		return resObject;
	}

	public static ResObject failure(Integer code,String msg,String subCode, String subMsg) {
		ResObject resObject = new ResObject();
		resObject.setCode(code);
		resObject.setIsSuccess(DEFAULT_FAILURE);
		resObject.setMsg(msg);
		resObject.setSubCode(subCode);
		resObject.setSubMsg(subMsg);
		return resObject;
	}

	public static ResObject toRes(int rows){
		return rows > 0 ? success() : failure();
	}

	public static ResObject toRes(boolean res){
		return res == true ? success() : failure();
	}

}
