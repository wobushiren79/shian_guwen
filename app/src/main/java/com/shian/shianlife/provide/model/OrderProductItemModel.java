package com.shian.shianlife.provide.model;

/**
 * 订单套餐商品
 * 
 * @author w9433
 *
 */
public class OrderProductItemModel {

	int id;// 订单ID
	String name;// 商品名称
	int categoryId;// 类别ID
	float price;// 单价
	String specification;// 规格
	String unit;// 单位
	int skuId;// 商品ID
	
	float totalPrice;

	int number;

	boolean canEdit=true;
	String refundId;
	String refundNumber;
	String refundTotalPrice;
	String refundReason;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}

	public String getRefundTotalPrice() {
		return refundTotalPrice;
	}

	public void setRefundTotalPrice(String refundTotalPrice) {
		this.refundTotalPrice = refundTotalPrice;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public int getSkuId() {
		return skuId;
	}

	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public boolean  isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
