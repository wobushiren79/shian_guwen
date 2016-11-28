package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveCustomerCemeteryParams extends BaseHttpParams{
	/**
	 * "consultId":1,
        "buyFlag":1,
        "buyIntention":"xx",
        "address":"xx",
        "cemeteryId":1,
        "cemeteryName":"xx",
        "graveAddress":"xx",
        "buyAmount":22.33,
        "buryTime":22222,
        "cemeteryUsageStatus":1,
        "note":"xx",
	 */
	
	private long consultId;
	private String buyIntention;
	private int buyFlag;
	private String address;
	private long cemeteryId;
	private String cemeteryName;
	private String graveAddress;
	private String buyAmount;
	private long buryTime;
	private int cemeteryUsageStatus;
	private String note;
	public long getConsultId() {
		return consultId;
	}
	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}
	public String getBuyIntention() {
		return buyIntention;
	}
	public void setBuyIntention(String buyIntention) {
		this.buyIntention = buyIntention;
	}
	public int getBuyFlag() {
		return buyFlag;
	}
	public void setBuyFlag(int buyFlag) {
		this.buyFlag = buyFlag;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getCemeteryId() {
		return cemeteryId;
	}
	public void setCemeteryId(long cemeteryId) {
		this.cemeteryId = cemeteryId;
	}
	public String getCemeteryName() {
		return cemeteryName;
	}
	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}
	public String getGraveAddress() {
		return graveAddress;
	}
	public void setGraveAddress(String graveAddress) {
		this.graveAddress = graveAddress;
	}
	public String getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(String buyAmount) {
		this.buyAmount = buyAmount;
	}
	public long getBuryTime() {
		return buryTime;
	}
	public void setBuryTime(long buryTime) {
		this.buryTime = buryTime;
	}
	public int getCemeteryUsageStatus() {
		return cemeteryUsageStatus;
	}
	public void setCemeteryUsageStatus(int cemeteryUsageStatus) {
		this.cemeteryUsageStatus = cemeteryUsageStatus;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
