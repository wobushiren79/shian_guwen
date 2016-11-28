package com.shian.shianlife.provide.result;

public class HrCommentResult {
	private long payId;
	private String codeUrl;
	private String verifyUrl;
	private long invoiceId;
	private long payFeedbackId;
	private String actualAmount;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public long getPayFeedbackId() {
		return payFeedbackId;
	}

	public void setPayFeedbackId(long payFeedbackId) {
		this.payFeedbackId = payFeedbackId;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getVerifyUrl() {
		return verifyUrl;
	}

	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
	}

}
