package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveCustomerFuneral extends BaseHttpParams {
	/**
	 * "orderId":116, "cityId":116, "funeralTime":1461396977334,
	 * "funeralParlorId":116, "funeralParlorAddress":"string",
	 * "cremationTime":116, "dieAddress":{ "address":"string", "provinceId":116,
	 * "cityId":116, "areaId":116, "suffix":"string" }, "dieTime":1461396977334,
	 * "funeralAddress":{ "address":"string", "provinceId":116, "cityId":116,
	 * "areaId":116, "suffix":"string" }, "note":"string" }
	 */

	private long orderId;
	private long cityId;
	private long funeralTime;
	private long funeralParlorId;
	private long cremationTime;
	private long dieTime;
	private String note;
	private String funeralParlorAddress;
	private HpAddConsultParams.TalkAddress funeralAddress;
	private HpAddConsultParams.TalkAddress dieAddress;
	private HpAddConsultParams.TalkAddress parlorAddress;

	public HpAddConsultParams.TalkAddress getParlorAddress() {
		return parlorAddress;
	}

	public void setParlorAddress(HpAddConsultParams.TalkAddress parlorAddress) {
		this.parlorAddress = parlorAddress;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getFuneralTime() {
		return funeralTime;
	}

	public void setFuneralTime(long funeralTime) {
		this.funeralTime = funeralTime;
	}

	public long getFuneralParlorId() {
		return funeralParlorId;
	}

	public void setFuneralParlorId(long funeralParlorId) {
		this.funeralParlorId = funeralParlorId;
	}

	public long getCremationTime() {
		return cremationTime;
	}

	public void setCremationTime(long cremationTime) {
		this.cremationTime = cremationTime;
	}

	public long getDieTime() {
		return dieTime;
	}

	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFuneralParlorAddress() {
		return funeralParlorAddress;
	}

	public void setFuneralParlorAddress(String funeralParlorAddress) {
		this.funeralParlorAddress = funeralParlorAddress;
	}

	public HpAddConsultParams.TalkAddress getFuneralAddress() {
		return funeralAddress;
	}

	public void setFuneralAddress(HpAddConsultParams.TalkAddress funeralAddress) {
		this.funeralAddress = funeralAddress;
	}

	public HpAddConsultParams.TalkAddress getDieAddress() {
		return dieAddress;
	}

	public void setDieAddress(HpAddConsultParams.TalkAddress dieAddress) {
		this.dieAddress = dieAddress;
	}

}
