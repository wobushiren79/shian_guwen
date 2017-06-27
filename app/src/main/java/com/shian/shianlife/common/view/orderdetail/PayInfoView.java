package com.shian.shianlife.common.view.orderdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.RefundOrderActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.model.PayInfoModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PayInfoView extends FrameLayout {

	LinearLayout parentLayout;

	@InjectView(R.id.tv_totalAmount)
	TextView tv_totalAmount;

	@InjectView(R.id.tv_prepayAmount)
	TextView tv_prepayAmount;

	@InjectView(R.id.tv_prepayEndTime)
	TextView tv_prepayEndTime;

	@InjectView(R.id.tv_restPayAmount)
	TextView tv_restPayAmount;

	@InjectView(R.id.tv)
	TextView tv;

	@InjectView(R.id.tv_restPayEndTime)
	TextView tv_restPayEndTime;

	@InjectView(R.id.tv_restPayeeName)
	TextView tv_restPayeeName;

	@InjectView(R.id.tv_restPayeePhone)
	TextView tv_restPayeePhone;

	@InjectView(R.id.iv_signUrl)
	ImageView iv_signUrl;

	@InjectView(R.id.tv_refundMoney)
	TextView tvRefundMoney;

	public PayInfoView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mParams.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_1dp);
		setLayoutParams(mParams);
		parentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.view_moneydetail, null);
		addView(parentLayout);
		ButterKnife.inject(this);
	}

	public void setData(HrGetOrderDetailResult result) {
		PayInfoModel payInfo = result.getPayInfo();
		tv_totalAmount.setText("￥"+payInfo.getTotalAmount() );
		tv_prepayAmount.append(payInfo.getPrepayAmount() + "");
		if(payInfo.getPrepayEndTime()!=null)
		tv_prepayEndTime.append(payInfo.getPrepayEndTime());
		tv_restPayAmount.append(payInfo.getReceivableAmount()+ "");
		tv.append(payInfo.getRestPayAmount()+"");
		if(payInfo.getRestPayEndTime()!=null)
		tv_restPayEndTime.append(payInfo.getRestPayEndTime());
		tv_restPayeeName.append(payInfo.getRestPayeeName());
		tv_restPayeePhone.append(payInfo.getRestPayeePhone());
		tvRefundMoney.append(payInfo.getRefundAmount()+"");
		PicassoUD.loadByImageLoader(getContext(), AppContansts.OSSURL+payInfo.getSignUrl(), iv_signUrl, 0);

	}

	@OnClick(R.id.tv_refund)
	void showRefundActivity(){
		Intent in = new Intent(getContext(), RefundOrderActivity.class);
		in.putExtra("orderId",((Activity)getContext()).getIntent().getLongExtra("orderId", 0));
		in.putExtra("consultId", ((Activity)getContext()).getIntent().getLongExtra("consultId", 0));
		in.putExtra("isDetailFlag",true);
		getContext().startActivity(in);
	}

}
