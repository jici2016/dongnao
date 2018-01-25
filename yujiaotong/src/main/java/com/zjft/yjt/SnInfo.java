package com.zjft.yjt;

/**
 *
 * @Package: com.zjft.yjt
 * @author liuming
 * @date 2018年1月25日
 *
 */
public class SnInfo {
	private String sn;
	private String title ;
	private String location;
	private String 	desc;
	private String thumbnail;
	private String state ;
	private String cloud_status;
	private String fw_status;
	public String getSn() {
		return sn;
	}
	public String getTitle() {
		return title;
	}
	public String getLocation() {
		return location;
	}
	public String getDesc() {
		return desc;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public String getState() {
		return state;
	}
	public String getFw_status() {
		return fw_status;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setFw_status(String Fw_status) {
		this.fw_status = Fw_status;
	}
	/**
	 * @return the cloud_status
	 */
	public String getCloud_status() {
		return cloud_status;
	}
	@Override
	public String toString() {
		return "SnInfo [sn=" + sn + ", title=" + title + ", location=" + location + ", desc=" + desc + ", thumbnail="
				+ thumbnail + ", state=" + state + ", cloud_status=" + cloud_status + ", fw_status=" + fw_status + "]";
	}
	/**
	 * @param cloud_status the cloud_status to set
	 */
	public void setCloud_status(String cloud_status) {
		this.cloud_status = cloud_status;
	}
	
}
