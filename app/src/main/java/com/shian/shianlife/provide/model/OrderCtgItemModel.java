package com.shian.shianlife.provide.model;

import java.util.List;

/**
 * 套餐
 * 
 * @author w9433
 *
 */
public class OrderCtgItemModel {

	String name;// 类别名字
	int id;// 类别ID

	List<OrderProductItemModel> productItems;// 商品列表

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderProductItemModel> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<OrderProductItemModel> productItems) {
		this.productItems = productItems;
	}

}
