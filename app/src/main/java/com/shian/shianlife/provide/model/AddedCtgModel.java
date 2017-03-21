package com.shian.shianlife.provide.model;

public class AddedCtgModel {

	int id;

	String name;

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

	@Override
	public String toString() {

		return name;
	}

	GoodsModel mGoodsModel;

	int num;

	public GoodsModel getmGoodsModel() {
		return mGoodsModel;
	}

	public void setmGoodsModel(GoodsModel mGoodsModel) {
		this.mGoodsModel = mGoodsModel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	boolean isCheck=false;//新增 是否选择了该项

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}
}
