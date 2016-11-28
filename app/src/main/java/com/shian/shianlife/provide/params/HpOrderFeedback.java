package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpOrderFeedback extends BaseHttpParams{
	/**
	 * "orderId":152, "satTotal":6, "satCeremony":6, "satArrange":6, "satFr":6,
	 * "satLife":6, "satSku":6, "opinion":"string", "signUrl":"string"
	 */

	private long orderId;
	private int satTotal;
	private int satCeremony;
	private int satArrange;
	private int satFr;
	private int satLife;
	private int satSku;
	private String opinion;
	private String signUrl;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getSatTotal() {
		return satTotal;
	}

	public void setSatTotal(int satTotal) {
		this.satTotal = satTotal;
	}

	public int getSatCeremony() {
		return satCeremony;
	}

	public void setSatCeremony(int satCeremony) {
		this.satCeremony = satCeremony;
	}

	public int getSatArrange() {
		return satArrange;
	}

	public void setSatArrange(int satArrange) {
		this.satArrange = satArrange;
	}

	public int getSatFr() {
		return satFr;
	}

	public void setSatFr(int satFr) {
		this.satFr = satFr;
	}

	public int getSatLife() {
		return satLife;
	}

	public void setSatLife(int satLife) {
		this.satLife = satLife;
	}

	public int getSatSku() {
		return satSku;
	}

	public void setSatSku(int satSku) {
		this.satSku = satSku;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getSignUrl() {
		return signUrl;
	}

	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}

}
