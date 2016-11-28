package com.shian.shianlife.provide.model;

public class SetmealItemModel {

	long skuId;// 商品ID
	long projectId;// 项目ID，1：治丧主套餐，2：殡仪馆项目，3：公墓项目，4：增值服务项目
	long categoryId;// 类别ID
	int number;// 商品数量
	String price;// 商品单价
	String totalPrice;// 商品总价

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
