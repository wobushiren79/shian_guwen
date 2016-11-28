package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveComment extends BaseHttpParams {
	/**
	 * "content":"string", "label":"string", "orderId":140, "orderItemId":140,
	 * "starLevel":4
	 */
	private String content;
	private String label="";
	private long orderId;
	private long orderItemId;
	private int starLevel;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

}
