package com.shian.shianlife.provide.params;

import java.util.List;

import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;

public class HpCreateOrderParams extends BaseHttpParams {
	// 29
	long consultId;// 咨询ID
	long setmealCemetery;// 公墓ID
	long setmealFuneral;// 殡仪馆级别ID
	long setmealMain;// 主套餐ID
	List<CreateOrderProductItemModel> items;// 订单项

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public long getSetmealCemetery() {
		return setmealCemetery;
	}

	public void setSetmealCemetery(long setmealCemetery) {
		this.setmealCemetery = setmealCemetery;
	}

	public long getSetmealFuneral() {
		return setmealFuneral;
	}

	public void setSetmealFuneral(long setmealFuneral) {
		this.setmealFuneral = setmealFuneral;
	}

	public long getSetmealMain() {
		return setmealMain;
	}

	public void setSetmealMain(long setmealMain) {
		this.setmealMain = setmealMain;
	}

	public List<CreateOrderProductItemModel> getItems() {
		return items;
	}

	public void setItems(List<CreateOrderProductItemModel> items) {
		this.items = items;
	}

}
