package com.shian.shianlife.provide.result;

public class HrConsultFuneral {
	 HrConsultFunerals consultFuneral;
	 
public HrConsultFunerals getConsultFuneral() {
		return consultFuneral;
	}

	public void setConsultFuneral(HrConsultFunerals consultFuneral) {
		this.consultFuneral = consultFuneral;
	}

public class HrConsultFunerals {
	/**
	 * "consultId":71, "frAddress":"string", "frBelief":7,
	 * "frBeliefDesc":"string", "frDirection":7, "frDirectionDesc":"string",
	 * "id":71, "orderId":71, "other":"string", "talkAddress":"string",
	 * "talkAddressArea":71, "talkAddressCity":71, "talkAddressProvince":71,
	 * "talkAddressSuffix":"string", "talkGpsAddress":"string",
	 * "talkUpdateTime":1461393349165
	 */
	private long consultId;
	private String frAddress;
	private String frBelief;
	private String frBeliefDesc;
	private String frDirection;
	private String frDirectionDesc;
	private long id;
	private long orderId;
	private String other;
	private String talkAddress;
	private int talkAddressArea;
	private int talkAddressCity;
	private int talkAddressProvince;
	private String talkAddressSuffix;
	private String talkGpsAddress;
	private long talkUpdateTime;

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public String getFrAddress() {
		return frAddress;
	}

	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}

	public String getFrBelief() {
		return frBelief;
	}

	public void setFrBelief(String frBelief) {
		this.frBelief = frBelief;
	}

	public String getFrDirection() {
		return frDirection;
	}

	public void setFrDirection(String frDirection) {
		this.frDirection = frDirection;
	}

	public String getFrBeliefDesc() {
		return frBeliefDesc;
	}

	public void setFrBeliefDesc(String frBeliefDesc) {
		this.frBeliefDesc = frBeliefDesc;
	}



	public String getFrDirectionDesc() {
		return frDirectionDesc;
	}

	public void setFrDirectionDesc(String frDirectionDesc) {
		this.frDirectionDesc = frDirectionDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getTalkAddress() {
		return talkAddress;
	}

	public void setTalkAddress(String talkAddress) {
		this.talkAddress = talkAddress;
	}

	public int getTalkAddressArea() {
		return talkAddressArea;
	}

	public void setTalkAddressArea(int talkAddressArea) {
		this.talkAddressArea = talkAddressArea;
	}

	public int getTalkAddressCity() {
		return talkAddressCity;
	}

	public void setTalkAddressCity(int talkAddressCity) {
		this.talkAddressCity = talkAddressCity;
	}

	public int getTalkAddressProvince() {
		return talkAddressProvince;
	}

	public void setTalkAddressProvince(int talkAddressProvince) {
		this.talkAddressProvince = talkAddressProvince;
	}

	public String getTalkAddressSuffix() {
		return talkAddressSuffix;
	}

	public void setTalkAddressSuffix(String talkAddressSuffix) {
		this.talkAddressSuffix = talkAddressSuffix;
	}

	public String getTalkGpsAddress() {
		return talkGpsAddress;
	}

	public void setTalkGpsAddress(String talkGpsAddress) {
		this.talkGpsAddress = talkGpsAddress;
	}

	public long getTalkUpdateTime() {
		return talkUpdateTime;
	}

	public void setTalkUpdateTime(long talkUpdateTime) {
		this.talkUpdateTime = talkUpdateTime;
	}

}
}
