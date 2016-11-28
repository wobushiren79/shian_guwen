package com.shian.shianlife.common.view.refund;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class CtgDetailRefundView extends FrameLayout {

	private LinearLayout parentLayout;

	@InjectView(R.id.tv_ctgName)
	TextView tv_ctgName;

	float totalPrice;

	public CtgDetailRefundView(Context context) {
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
		ProductDetailRefundView mDetailView = new ProductDetailRefundView(getContext());
		mDetailView.setLayoutParams(
				new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		View line = getLineView();
		parentLayout.addView(line);
		parentLayout.addView(mDetailView);
		mDetailView.setData(mProductItemModel);

	}

	private View getLineView() {
		View line = new View(getContext());
		line.setBackgroundColor(getContext().getResources().getColor(R.color.gray_common));
		line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
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
