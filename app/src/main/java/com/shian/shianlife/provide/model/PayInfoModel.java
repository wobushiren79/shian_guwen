package com.shian.shianlife.provide.model;

public class PayInfoModel {

	float totalAmount;// 总金额
	float prepayAmount;// 已经支付定金金额
	long prepayEndTime;// 定金支付时间
	float restPayAmount;// 应支付余款
	long restPayEndTime;// 余款支付时间
	String restPayeeName="";// 收款员
	String restPayeePhone="";// 联系电话
	String signUrl="";// 客户签字
	float refundAmount;
	float receivableAmount;

	public float getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(float receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public float getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(float refundAmount) {
		this.refundAmount = refundAmount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getPrepayAmount() {
		return prepayAmount;
	}

	public void setPrepayAmount(float prepayAmount) {
		this.prepayAmount = prepayAmount;
	}

	public long getPrepayEndTime() {
		return prepayEndTime;
	}

	public void setPrepayEndTime(long prepayEndTime) {
		this.prepayEndTime = prepayEndTime;
	}

	public float getRestPayAmount() {
		return restPayAmount;
	}

	public void setRestPayAmount(float restPayAmount) {
		this.restPayAmount = restPayAmount;
	}

	public long getRestPayEndTime() {
		return restPayEndTime;
	}

	public void setRestPayEndTime(long restPayEndTime) {
		this.restPayEndTime = restPayEndTime;
	}

	public String getRestPayeeName() {
		return restPayeeName;
	}

	public void setRestPayeeName(String restPayeeName) {
		this.restPayeeName = restPayeeName;
	}

	public String getRestPayeePhone() {
		return restPayeePhone;
	}

	public void setRestPayeePhone(String restPayeePhone) {
		this.restPayeePhone = restPayeePhone;
	}

	public String getSignUrl() {
		return signUrl;
	}

	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}

}
