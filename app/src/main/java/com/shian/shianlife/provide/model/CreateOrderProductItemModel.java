package com.shian.shianlife.provide.model;

/**
 * 提交订单时候的商品参数
 * 
 * @author w9433
 *
 */
public class CreateOrderProductItemModel {

	long skuId;// 商品ID
	long projectId;// 项目ID，1：治丧主套餐，2：殡仪馆项目，3：公墓项目，4：增值服务项目
	long categoryId;// 类别ID
	int number;// 商品数量
	float price;// 单价
	float totalPrice;// 商品总价
	int statusFlag = 1;// 1有效 2无效
	Long id ;// 订单详情ID
	boolean isChange;

	/**
	 * 新增 商品名称
	 */
	String name;//商品名字

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

}
