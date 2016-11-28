package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpConsultIdParams extends BaseHttpParams {

	long consultId;//咨询ID
	int appStatus;
	String email;
	String introduce;
	String curAddress;

	public String getCurAddress() {
		return curAddress;
	}

	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

}
