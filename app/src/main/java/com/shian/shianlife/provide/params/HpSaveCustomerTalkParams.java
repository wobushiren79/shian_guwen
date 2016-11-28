package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveCustomerTalkParams extends BaseHttpParams {
	/**
	 * "consultId":1, "talkGpsAddress":"xx", "frBelief":1, "frBeliefDesc":"xx",
	 * "frDirection":1, "frDirectionDesc":"xx", "other":"xx"
	 */
	private long consultId;
	private String talkGpsAddress;
	private String frBelief;
	private String frBeliefDesc;
	private String frDirection;
	private String frDirectionDesc;
	private String other;
	private HpAddConsultParams.TalkAddress talkAddress;

	public HpAddConsultParams.TalkAddress getTalkAddress() {
		return talkAddress;
	}

	public void setTalkAddress(HpAddConsultParams.TalkAddress talkAddress) {
		this.talkAddress = talkAddress;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public String getTalkGpsAddress() {
		return talkGpsAddress;
	}

	public void setTalkGpsAddress(String talkGpsAddress) {
		this.talkGpsAddress = talkGpsAddress;
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

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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
}
