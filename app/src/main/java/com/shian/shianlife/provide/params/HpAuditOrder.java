package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpAuditOrder extends BaseHttpParams {
	/**
	 * "orderItemId":35, "pass":false, "auditNote":"string"
	 */
	private long orderItemId;
	private boolean pass;
	private String auditNote;
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getAuditNote() {
		return auditNote;
	}
	public void setAuditNote(String auditNote) {
		this.auditNote = auditNote;
	}
	
}
