package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpAcceptParams extends BaseHttpParams {
	private long id;// 指派ID
	private long orderId;
	private long orderItemId;
	private long consultId;// 咨询ID
	private String talkGpsAddress;// 洽谈地点

	
	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
