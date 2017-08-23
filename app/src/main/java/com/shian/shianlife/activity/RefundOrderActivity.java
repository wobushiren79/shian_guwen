package com.shian.shianlife.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.refund.AddedDetailRefundView;
import com.shian.shianlife.common.view.refund.FuneralDetailRefundView;
import com.shian.shianlife.common.view.refund.MainDetailRefundView;
import com.shian.shianlife.common.view.refund.PayInfoRefundView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.model.PayInfoModel;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpRefundParams;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import okhttp3.Request;

public class RefundOrderActivity extends BaseActivity {

	@InjectView(R.id.ll_parent)
	LinearLayout ll_parent;

	MainDetailRefundView mMainDetailView;
	FuneralDetailRefundView mFuneralDetailView;
//	CemeteryDetailRefundView mCemeteryDetailView;
	AddedDetailRefundView mAddedDetailView;
	PayInfoRefundView mPayInfoView;

	BordModel mBordModel;
	PayInfoModel mPayInfoModel;

	private long orderId;
	private TextView refundMoney;
	private boolean isDetailFlag;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_detail);

		orderId = getIntent().getLongExtra("orderId", 0);
		isDetailFlag=getIntent().getBooleanExtra("isDetailFlag",false);
		if(isDetailFlag){
			setTitle("退款详情");
		}else{
			setTitle("申请退款");
		}
		mMainDetailView = new MainDetailRefundView(this);
		mFuneralDetailView = new FuneralDetailRefundView(this);
//		mCemeteryDetailView = new CemeteryDetailRefundView(this);
		mAddedDetailView = new AddedDetailRefundView(this);
		mPayInfoView = new PayInfoRefundView(this);
		refundMoney=mPayInfoView.getreFundMoney();

		TextView textView=new TextView(RefundOrderActivity.this);
		textView.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams textLayouparam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.dimen_20dp));
		textView.setLayoutParams(textLayouparam);
		ll_parent.addView(textView);
		ll_parent.addView(mMainDetailView);
		ll_parent.addView(mFuneralDetailView);
//		ll_parent.addView(mCemeteryDetailView);
		ll_parent.addView(mAddedDetailView);
		ll_parent.addView(mPayInfoView);

		getData();
	}

	private void getData() {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderId(orderId);
		params.setReturnOnlyRefund(isDetailFlag);
		MHttpManagerFactory.getFuneralManager().getRefundOrder(this, params, new HttpResponseHandler<HrGetOrderDetailResult>() {

			@Override
			public void onStart(Request request, int id) {

			}

			@Override
			public void onSuccess(HrGetOrderDetailResult result) {
				mMainDetailView.setData(result);
				mFuneralDetailView.setData(result);
//				mCemeteryDetailView.setData(result);
				mAddedDetailView.setData(result);
				mPayInfoView.setData(result);
			}


			@Override
			public void onError(String message) {

			}
		});
	}

	public void setRefundMoney(float money){
		float m=Float.valueOf(refundMoney.getText().toString());
		float nM=m+money;
		if(nM>=0){
			refundMoney.setText(nM+"");
		}
	}

	private List<HpRefundParams.RefundItem>  refundItems=new ArrayList<HpRefundParams.RefundItem>();
	public void setRefundItems(long orderItemId,int num,String message){
		HpRefundParams.RefundItem mItem=null;
		for(HpRefundParams.RefundItem item:refundItems){
			if(item.getOrderItemId()==orderItemId){
				mItem=item;
			}
		}
		if(mItem==null){
			mItem=new HpRefundParams.RefundItem();
			refundItems.add(mItem);
		}
		mItem.setOrderItemId(orderItemId);
		mItem.setReason(message);
		mItem.setRefundNumber(num);
	}

	public List<HpRefundParams.RefundItem> getRefundItems() {
		return refundItems;
	}

	public void setRefundItems(List<HpRefundParams.RefundItem> refundItems) {
		this.refundItems = refundItems;
	}

	public boolean isDetailFlag() {
		return isDetailFlag;
	}

	public void setDetailFlag(boolean detailFlag) {
		isDetailFlag = detailFlag;
	}
}
