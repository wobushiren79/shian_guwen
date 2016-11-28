package com.shian.shianlife.activity;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.orderdetail.AddedDetailView;
import com.shian.shianlife.common.view.orderdetail.CemeteryDetailView;
import com.shian.shianlife.common.view.orderdetail.CustomDetailView;
import com.shian.shianlife.common.view.orderdetail.CustomDetailView.OnCallBack;
import com.shian.shianlife.common.view.orderdetail.FuneralDetailView;
import com.shian.shianlife.common.view.orderdetail.MainDetailView;
import com.shian.shianlife.common.view.orderdetail.PayInfoView;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.model.PayInfoModel;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.InjectView;

public class OrderDetailActivity extends BaseActivity {

	@InjectView(R.id.ll_parent)
	LinearLayout ll_parent;

	CustomDetailView mCustomDetailView;
	MainDetailView mMainDetailView;
	FuneralDetailView mFuneralDetailView;
	CemeteryDetailView mCemeteryDetailView;
	AddedDetailView mAddedDetailView;
	PayInfoView mPayInfoView;

	BordModel mBordModel;
	PayInfoModel mPayInfoModel;

	private long orderId;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_detail);
		setTitle("订单详情");
		mCustomDetailView = new CustomDetailView(this);
		mMainDetailView = new MainDetailView(this);
		mFuneralDetailView = new FuneralDetailView(this);
		mCemeteryDetailView = new CemeteryDetailView(this);
		mAddedDetailView = new AddedDetailView(this);
		mPayInfoView = new PayInfoView(this);
		ll_parent.addView(mCustomDetailView);
		ll_parent.addView(mMainDetailView);
		ll_parent.addView(mFuneralDetailView);
		ll_parent.addView(mCemeteryDetailView);
		ll_parent.addView(mAddedDetailView);
		ll_parent.addView(mPayInfoView);
		orderId = getIntent().getLongExtra("orderId", 0);
		getData();
		

	}

	private void getData() {
		HpGetOrderDetailParams params = new HpGetOrderDetailParams();
		params.setOrderId(orderId);
		OrderManagerImpl.getInstance().getOrderDetail(this, params, new HttpResponseHandler<HrGetOrderDetailResult>() {

			@Override
			public void onSuccess(HrGetOrderDetailResult result) {
				mCustomDetailView.setData(result);
				mCustomDetailView.setOnCallBack(new OnCallBack() {

					@Override
					public void watchCutomerDetail() {
						ToastUtils.show(mContext, "查看客户详情");
					}
				});
				mMainDetailView.setData(result);
				mFuneralDetailView.setData(result);
				mCemeteryDetailView.setData(result);
				mAddedDetailView.setData(result);
				mPayInfoView.setData(result);
			}

			@Override
			public void onStart() {

			}

			@Override
			public void onError(String message) {

			}
		});
	}

}
