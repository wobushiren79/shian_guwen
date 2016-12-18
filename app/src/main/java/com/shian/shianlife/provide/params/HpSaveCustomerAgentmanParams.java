package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.params.HpSaveCustomerUsageParams.CurAddress;

public class HpSaveCustomerAgentmanParams extends BaseHttpParams {

	/**
	 * "consultId":146, "name":"string", "linkInfo":"string",
	 * "birthday":1461394884008, "relation":0 "address":{ "address":"string",
	 * "areaId":146, "cityId":146, "provinceId":146, "suffix":"string"
	 */
	private long consultId;
	private String name;
	private String relation;
	private long birthday;
	private String linkInfo;
	private HpAddConsultParams.TalkAddress address;

	private String zsLocation;
	private String location;
	private String cardId;
	private String email;
	private String remark;

	public String getZsLocation() {
		return zsLocation;
	}

	public void setZsLocation(String zsLocation) {
		this.zsLocation = zsLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getLinkInfo() {
		return linkInfo;
	}

	public void setLinkInfo(String linkInfo) {
		this.linkInfo = linkInfo;
	}

	public HpAddConsultParams.TalkAddress getAddress() {
		return address;
	}

	public void setAddress(HpAddConsultParams.TalkAddress address) {
		this.address = address;
	}

	

}
