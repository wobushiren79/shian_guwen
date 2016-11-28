package com.shian.shianlife.provide.model;

import java.util.List;

/**
 * 套餐
 * 
 * @author w9433
 *
 */
public class SetmealModel {

	String name;// 套餐名字
	int id;// 套餐ID

	List<CtgItemModel> ctgItems;// 套餐明细

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

	public List<CtgItemModel> getCtgItems() {
		return ctgItems;
	}

	public void setCtgItems(List<CtgItemModel> ctgItems) {
		this.ctgItems = ctgItems;
	}

	@Override
	public String toString() {
		return name;
	}

}
