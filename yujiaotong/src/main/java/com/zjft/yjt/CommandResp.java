package com.zjft.yjt;

/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
public class CommandResp {
	
	@Override
	public String toString() {
		return "CommandResp [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
	private String errorCode;
	private String errorMsg;
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
