package com.shian.shianlife.common.view.customer;

import com.shian.shianlife.provide.model.ConsultPrereadyModel;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.AddAddition;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.RemoveAddition;
import com.shian.shianlife.provide.result.HrGetCustomerContract.OrderContractAddition;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PicImageView extends ImageView {
	private OrderContractAddition addItem;
	private AddAddition newItem;
	private RemoveAddition delItem;
	private ConsultPrereadyModel add1Item;

	public ConsultPrereadyModel getAdd1Item() {
		return add1Item;
	}

	public void setAdd1Item(ConsultPrereadyModel add1Item) {
		this.add1Item = add1Item;
	}

	public AddAddition getNewItem() {
		return newItem;
	}

	public void setNewItem(AddAddition newItem) {
		this.newItem = newItem;
	}

	public RemoveAddition getDelItem() {
		return delItem;
	}

	public void setDelItem(RemoveAddition delItem) {
		this.delItem = delItem;
	}

	public OrderContractAddition getAddItem() {
		return addItem;
	}

	public void setAddItem(OrderContractAddition addItem) {
		this.addItem = addItem;
	}

	public PicImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

}
