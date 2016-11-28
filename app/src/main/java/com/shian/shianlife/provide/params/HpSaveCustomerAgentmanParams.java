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
