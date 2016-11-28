package com.shian.shianlife.provide.model;

import java.util.List;

public class ProjectItemModel {

	String name;// 项目名字
	int id;// 项目ID

	List<OrderCtgItemModel> ctgItems;// 类别列表

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

	public List<OrderCtgItemModel> getCtgItems() {
		return ctgItems;
	}

	public void setCtgItems(List<OrderCtgItemModel> ctgItems) {
		this.ctgItems = ctgItems;
	}

}
