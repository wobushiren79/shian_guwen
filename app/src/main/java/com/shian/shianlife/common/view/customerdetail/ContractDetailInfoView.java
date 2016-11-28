package com.shian.shianlife.common.view.customerdetail;

import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.view.DownLoadView;
import com.shian.shianlife.provide.result.HrGetCustomerContract;
import com.shian.shianlife.provide.result.HrGetCustomerContract.OrderContract;
import com.shian.shianlife.provide.result.HrGetCustomerContract.OrderContractAddition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ContractDetailInfoView extends FrameLayout {

	@InjectView(R.id.tv_contractNo)
	TextView tv_contractNo;

	@InjectView(R.id.tv_contractAmount)
	TextView tv_contractAmount;

	@InjectView(R.id.ll_fujian)
	LinearLayout ll_fujian;

	@InjectView(R.id.tv_description)
	TextView tv_description;

	public ContractDetailInfoView(Context context) {
		super(context);
		init();
	}

	public ContractDetailInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ContractDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_contractdetailinfo, null);
		addView(view);
		ButterKnife.inject(this);
	}

	public void setData(HrGetCustomerContract result) {
		OrderContract orderContract = result.getOrderContract();
		tv_contractNo.setText(orderContract.getContractNo());
		tv_contractAmount.setText(orderContract.getContractAmount());
		List<OrderContractAddition> contractAdditions = result.getOrderContractAdditions();
		LinearLayout.LayoutParams mLinearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mLinearParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		LinearLayout.LayoutParams mBtnParams = new LinearLayout.LayoutParams(0,
				getResources().getDimensionPixelOffset(R.dimen.dimen_80dp));
		mBtnParams.weight = 1;
		mBtnParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mBtnParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mBtnParams.gravity = Gravity.CENTER_VERTICAL;
		int position = 0;
		int tmp = contractAdditions.size() % 3;
		int line = tmp == 0 ? tmp : tmp + 1;
		for (int i = 0; i < line; i++) {
			LinearLayout mLinearLayout = new LinearLayout(getContext());
			mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
			mLinearLayout.setLayoutParams(mLinearParams);
			ll_fujian.addView(mLinearLayout);
			for (int j = 0; j < 3; j++) {
				if (position < contractAdditions.size()) {
					OrderContractAddition addition = contractAdditions.get(position);
					DownLoadView dv = new DownLoadView(getContext());
					dv.setLayoutParams(mBtnParams);
					dv.setFileUrl(AppContansts.OSSURL + addition.getFileUrl());
					mLinearLayout.addView(dv);
					position++;
				}
			}
		}

	}

}
