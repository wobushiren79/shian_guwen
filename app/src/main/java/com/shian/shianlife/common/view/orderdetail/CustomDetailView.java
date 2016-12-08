package com.shian.shianlife.common.view.orderdetail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.CustomerDetailActivity;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.common.view.ColorsTextView;
import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

@SuppressLint("InflateParams")
public class CustomDetailView extends FrameLayout {

	/**
	 * 客户编号
	 */
	@InjectView(R.id.tv_visitNum)
	ColorsTextView tv_visitNum;

	@InjectView(R.id.tv_customerdetail)
	TextView tv_customerdetail;

	/**
	 * 订单编号
	 */
	@InjectView(R.id.tv_orderNum)
	TextView tv_orderNum;

	/**
	 * 订单日期
	 */
	@InjectView(R.id.tv_orderTime)
	TextView tv_orderTime;

	/**
	 * 经办人
	 */
	@InjectView(R.id.tv_agentmanName)
	TextView tv_agentmanName;

	/**
	 * 经办人电话
	 */
	@InjectView(R.id.tv_agentmanlinkInfo)
	TextView tv_agentmanlinkInfo;

	/**
	 * 合同编号
	 */
	@InjectView(R.id.tv_contractNo)
	TextView tv_contractNo;

	/**
	 * 合同金额
	 */
	@InjectView(R.id.tv_contractAmount)
	TextView tv_contractAmount;

	/**
	 * 逝者姓名
	 */
	@InjectView(R.id.tv_usageName)
	TextView tv_usageName;

	/**
	 * 出殡时间
	 */
	@InjectView(R.id.tv_funeralTime)
	TextView tv_funeralTime;

	/**
	 * 服务开始时间
	 */
	@InjectView(R.id.tv_startTime)
	TextView tv_startTime;

	/**
	 * 服务结束时间
	 */
	@InjectView(R.id.tv_endTime)
	TextView tv_endTime;

	/**
	 * 白事顾问
	 */
	@InjectView(R.id.tv_talkerName)
	TextView tv_talkerName;

	/**
	 * 治丧指导
	 */
	@InjectView(R.id.tv_performerName)
	TextView tv_performerName;

	/**
	 * 经办人地址
	 */
	@InjectView(R.id.tv_agentmanAddress)
	TextView tv_agentmanAddress;

	/**
	 * 治丧地址
	 */
	@InjectView(R.id.tv_funeralAddress)
	TextView tv_funeralAddress;

	/**
	 * 去世地址
	 */
	@InjectView(R.id.tv_dieAddress)
	TextView tv_dieAddress;

	/**
	 * 殡仪馆地址
	 */
	@InjectView(R.id.tv_funeralParlorAddress)
	TextView tv_funeralParlorAddress;

	@InjectView(R.id.tv_khName)
	TextView tv_khName;
	@InjectView(R.id.tv_khTel)
	TextView tv_khTel;
	@InjectView(R.id.tv_talkerTel)
	TextView tv_talkerTel;
	@InjectView(R.id.tv_performerTel)
	TextView tv_performerTel;
	LinearLayout parentLayout;

	BordModel mBordModel;

	public CustomDetailView(Context context) {
		super(context);
		init();

	}

	private void init() {
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		setLayoutParams(mParams);
		parentLayout = (LinearLayout) LayoutInflater.from(getContext())
				.inflate(R.layout.view_customdetail, null);
		addView(parentLayout);
		ButterKnife.inject(this);
	}

	@OnClick(R.id.tv_customerdetail)
	void watchCutomerDetail() {
		Intent in = new Intent(getContext(), CustomerDetailActivity.class);
		in.putExtra("orderId", ((Activity) getContext()).getIntent()
				.getLongExtra("orderId", 0));
		in.putExtra("consultId", ((Activity) getContext()).getIntent()
				.getLongExtra("consultId", 0));
		getContext().startActivity(in);
		if (onCallBack != null) {
			onCallBack.watchCutomerDetail();
		}
	}

	public void setData(HrGetOrderDetailResult result) {
		mBordModel = result.getBoard();
		tv_visitNum.appendContent(mBordModel.getVisitNum(),Color.parseColor("#467837"));
		tv_orderNum.append(mBordModel.getOrderNum());
		if(mBordModel.getOrderTime()!=0)
		tv_orderTime.append(TimeUtils.formatTime(mBordModel.getOrderTime()));
		tv_agentmanName.append(mBordModel.getAgentmanName());
		tv_agentmanlinkInfo.append(mBordModel.getAgentmanlinkInfo());
		tv_contractNo.append(mBordModel.getContractNo());
		tv_contractAmount.append(mBordModel.getContractAmount());
		tv_usageName.append(mBordModel.getUsageName());
		if(mBordModel.getFuneralTime()!=0) {
			tv_funeralTime
					.append(TimeUtils.formatTime(mBordModel.getFuneralTime()));
		}
		if(mBordModel.getStartTime()!=0)
		tv_startTime.append(TimeUtils.formatTime(mBordModel.getStartTime()));
		if(mBordModel.getEndTime()!=0)
		tv_endTime.append(TimeUtils.formatTime(mBordModel.getEndTime()));
		tv_talkerName.append(mBordModel.getTalkerName());
		tv_performerName.append(mBordModel.getPerformerName());
		tv_agentmanAddress.append(mBordModel.getAgentmanAddress());
		tv_funeralAddress.append(mBordModel.getFuneralAddress());
		tv_dieAddress.append(mBordModel.getDieAddress());
		tv_funeralParlorAddress.append(mBordModel.getFuneralParlorAddress());
		tv_khTel.append(mBordModel.getCustomerMobile());
		tv_khName.append(mBordModel.getCustomerName());
		tv_talkerTel.append(mBordModel.getTalkerMobile());
		tv_performerTel.append(mBordModel.getPerformerMobile());
	}

	OnCallBack onCallBack;

	public void setOnCallBack(OnCallBack onCallBack) {
		this.onCallBack = onCallBack;
	}

	public interface OnCallBack {
		public void watchCutomerDetail();
	}

}
