package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpOrderIdParams extends BaseHttpParams {

	long orderId;// 咨询ID
	long orderItemId;
	long payId;
	String invoiceNo;
	boolean returnOnlyRefund;

	public boolean isReturnOnlyRefund() {
		return returnOnlyRefund;
	}

	public void setReturnOnlyRefund(boolean returnOnlyRefund) {
		this.returnOnlyRefund = returnOnlyRefund;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	String payAmount;

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

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

}
