package com.shian.shianlife.provide.model;

/**
 * 殡仪馆项目
 * 
 * @author 95
 *
 */
public class UndertakerSetmealModel {

	SetmealItemModel shuttle;// 接运
	SetmealItemModel park;// 停放
	SetmealItemModel cremation;// 火化

	public SetmealItemModel getShuttle() {
		return shuttle;
	}

	public void setShuttle(SetmealItemModel shuttle) {
		this.shuttle = shuttle;
	}

	public SetmealItemModel getPark() {
		return park;
	}

	public void setPark(SetmealItemModel park) {
		this.park = park;
	}

	public SetmealItemModel getCremation() {
		return cremation;
	}

	public void setCremation(SetmealItemModel cremation) {
		this.cremation = cremation;
	}

}
