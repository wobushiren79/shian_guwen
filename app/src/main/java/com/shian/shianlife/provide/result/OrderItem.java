package com.shian.shianlife.provide.result;

import java.util.List;

public class OrderItem {
	/**
	 * 05-21 17:31:46.276: E/tag(29643):
	 * {"code":1000,"content":{"canEdit":false,"items":
	 * [{"id":9,"itemStatus":1,"number"
	 * :1,"skuCtgName":"主套餐类别2","skuName":"lege被2"}]},
	 * "message":"操作成功","serialNum"
	 * :"f47dccba1d714d2ba6b4f39f962383fc","validErrors":{}}
	 */
	private long id;
	private String skuCtgName;
	private String skuName;
	private int number;
	private String note;
	private String executorName;
	private int itemStatus;
	private long executorId;
	private String specification;
	private boolean hasComment;
	private String executorMobile;
	private String url1;
	private String url2;
	List<HrGetCustomerContract.OrderContractAddition> additions;

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getExecutorMobile() {
		return executorMobile;
	}

	public void setExecutorMobile(String executorMobile) {
		this.executorMobile = executorMobile;
	}

	public boolean isHasComment() {
		return hasComment;
	}

	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public List<HrGetCustomerContract.OrderContractAddition> getAdditions() {
		return additions;
	}

	public void setAdditions(
			List<HrGetCustomerContract.OrderContractAddition> additions) {
		this.additions = additions;
	}

	public long getExecutorId() {
		return executorId;
	}

	public void setExecutorId(long executorId) {
		this.executorId = executorId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSkuCtgName() {
		return skuCtgName;
	}

	public void setSkuCtgName(String skuCtgName) {
		this.skuCtgName = skuCtgName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public int getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}

}