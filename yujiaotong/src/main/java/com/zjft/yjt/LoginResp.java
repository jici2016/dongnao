package com.zjft.yjt;

/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
public class LoginResp {
	private String errorCode;
	private String errorMsg;
	private String usid;
	private String pushKey;
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public String getUsid() {
		return usid;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	
}
