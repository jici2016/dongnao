package com.zjft.yjt;

import java.util.Map;

/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
public class SnInfoResp {
	private String errorCode;
	private String errorMsg;
	private Map<String,SnInfo> snData;
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public Map<String, SnInfo> getSnData() {
		return snData;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setSnData(Map<String, SnInfo> snData) {
		this.snData = snData;
	}
	@Override
	public String toString() {
		return "SnInfoResp [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", snData=" + snData + "]";
	}
	
}
