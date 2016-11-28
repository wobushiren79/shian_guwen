package com.shian.shianlife.common.view.customerdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.shian.shianlife.provide.result.HrConsultCemetery.HrConsultCemeterys;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CemeteryDetailInfoView extends FrameLayout {

	@InjectView(R.id.tv_cemeteryName)
	TextView tv_cemeteryName;

	@InjectView(R.id.tv_buyIntention)
	TextView tv_buyIntention;

	@InjectView(R.id.tv_graveAddress)
	TextView tv_graveAddress;

	@InjectView(R.id.tv_address)
	TextView tv_address;

	@InjectView(R.id.tv_buyAmount)
	TextView tv_buyAmount;

	@InjectView(R.id.tv_buryTime)
	TextView tv_buryTime;

	@InjectView(R.id.tv_cemeteryUsageStatus)
	TextView tv_cemeteryUsageStatus;

	@InjectView(R.id.tv_note)
	TextView tv_note;
	@InjectView(R.id.ll_main)
	View llMain;
	@InjectView(R.id.ll_buyYx)
	LinearLayout llYx;
	@InjectView((R.id.tv_buyYx))
	TextView tvYx;


	public CemeteryDetailInfoView(Context context) {
		super(context);
		init();
	}

	public CemeteryDetailInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CemeteryDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_cemeterydetailinfo, null);
		addView(view);
		ButterKnife.inject(this);
	}

	public void setData(HrConsultCemetery result) {
		HrConsultCemeterys cemetery = result.getConsultCemetery();
		tv_address.setText(cemetery.getAddress());
		tv_buryTime.setText(TimeUtils.formatTime(cemetery.getBuryTime()));
		tv_buyAmount.setText("￥" + cemetery.getBuyAmount());
		int flag=cemetery.getBuyFlag();
		if(flag==1){
			llMain.setVisibility(View.GONE);
			tv_buyIntention.setText("否");
			llYx.setVisibility(View.VISIBLE);
		}else{
			tv_buyIntention.setText("是");
		}
		tvYx.setText(cemetery.getBuyIntention());
		tv_cemeteryName.setText(cemetery.getCemeteryName());
		switch (cemetery.getCemeteryUsageStatus()) {
		case 1:
			tv_cemeteryUsageStatus.setText("单穴");
			break;
		case 2:
			tv_cemeteryUsageStatus.setText("双穴");
			break;
		case 3:
			tv_cemeteryUsageStatus.setText("双穴已葬一位");
			break;
		}
		tv_graveAddress.setText(cemetery.getGraveAddress());
		tv_note.setText(cemetery.getNote());

	}

}
