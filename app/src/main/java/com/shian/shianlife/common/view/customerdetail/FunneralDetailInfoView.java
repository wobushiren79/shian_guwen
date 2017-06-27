package com.shian.shianlife.common.view.customerdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.result.HrGetCustomerFuneral;
import com.shian.shianlife.provide.result.HrGetCustomerFuneral.OrderFuneral;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class FunneralDetailInfoView extends FrameLayout {

	@InjectView(R.id.tv_dieTime)
	TextView tv_dieTime;

	@InjectView(R.id.tv_funeralTime)
	TextView tv_funeralTime;

	@InjectView(R.id.tv_cremationTime)
	TextView tv_cremationTime;

	@InjectView(R.id.tv_dieAddress)
	TextView tv_dieAddress;

	@InjectView(R.id.tv_funeralParlorAddress)
	TextView tv_funeralParlorAddress;

	@InjectView(R.id.tv_funeralAddress)
	TextView tv_funeralAddress;

	@InjectView(R.id.tv_note)
	TextView tv_note;

	public FunneralDetailInfoView(Context context) {
		super(context);
		init();
	}

	public FunneralDetailInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FunneralDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_funeraldetailinfo, null);
		addView(view);
		ButterKnife.inject(this);
	}

	public void setData(HrGetCustomerFuneral result) {
		OrderFuneral funeral = result.getOrderFuneral();
		tv_cremationTime.setText(funeral.getCremationTime());
		tv_dieAddress.setText(funeral.getDieAddress());
		tv_dieTime.setText(funeral.getDieTime());
		tv_funeralAddress.setText(funeral.getFuneralAddress());
		tv_funeralParlorAddress.setText(funeral.getFuneralParlorAddress());
		tv_funeralTime.setText(funeral.getFuneralTime());
		tv_note.setText(funeral.getNote());
	}

}
