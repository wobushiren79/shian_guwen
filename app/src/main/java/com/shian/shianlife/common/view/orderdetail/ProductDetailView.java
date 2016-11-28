package com.shian.shianlife.common.view.orderdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.OrderProductItemModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class ProductDetailView extends FrameLayout {

	private LinearLayout parentLayout;
	@InjectView(R.id.tv_name)
	TextView tv_name;
	@InjectView(R.id.tv_num)
	TextView tv_num;
	@InjectView(R.id.tv_totalprice)
	TextView tv_totalprice;

	public ProductDetailView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mParams.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_45dp);
		setLayoutParams(mParams);
		parentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_productdetail, null);
		addView(parentLayout);
		ButterKnife.inject(this);
	}

	public void setData(OrderProductItemModel mProductItemModel) {
		tv_name.append(mProductItemModel.getName());
		tv_num.append(mProductItemModel.getNumber() + mProductItemModel.getUnit());
		tv_totalprice.append(mProductItemModel.getTotalPrice() + "");
	}

}
