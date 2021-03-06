package com.shian.shianlife.common.view.orderdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class CtgDetailView extends FrameLayout {

	private LinearLayout parentLayout;

	@InjectView(R.id.tv_ctgName)
	TextView tv_ctgName;

	float totalPrice;

	public CtgDetailView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mParams.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_45dp);
		setLayoutParams(mParams);
		parentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_ctgdetail, null);
		addView(parentLayout);
		ButterKnife.inject(this);
	}

	public void addProductItem(OrderProductItemModel mProductItemModel) {
		ProductDetailView mDetailView = new ProductDetailView(getContext());
		mDetailView.setLayoutParams(
				new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//		View line = getLineView();
//		parentLayout.addView(line);
		parentLayout.addView(mDetailView);
		mDetailView.setData(mProductItemModel);

	}

	private View getLineView() {
		View line = new View(getContext());
		line.setBackgroundColor(getContext().getResources().getColor(R.color.zhy_line_3));
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams
				(LinearLayout.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_2dp));
		layoutParams.leftMargin=getResources().getDimensionPixelOffset(R.dimen.dimen_32dp);
		line.setLayoutParams(layoutParams);
		return line;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setData(OrderCtgItemModel ctgItemModel) {
		tv_ctgName.setText(ctgItemModel.getName());
		totalPrice = 0;
		for (OrderProductItemModel mProductItemModel : ctgItemModel.getProductItems()) {
			addProductItem(mProductItemModel);
			totalPrice += mProductItemModel.getTotalPrice();
		}
	}

}
