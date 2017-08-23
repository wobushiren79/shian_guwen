package com.shian.shianlife.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import okhttp3.Request;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.result.HrOrderItenNote;

public class ShenheActivity extends BaseActivity {
	@InjectViews({ R.id.tv_shenhe_0, R.id.tv_shenhe_1, R.id.tv_shenhe_2,
			R.id.tv_shenhe_3, R.id.tv_shenhe_4, R.id.tv_shenhe_5,
			R.id.tv_shenhe_6, R.id.tv_shenhe_7, R.id.tv_shenhe_8 })
	List<TextView> tvList;
	@InjectView(R.id.et_shenhe)
	EditText etShenhe;
	private long orderItemId;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_shenhe);
		orderItemId = getIntent().getLongExtra("orderItemId", 0);
		initData();
	}

	private void initData() {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderItemId(orderItemId);
		MHttpManagerFactory.getFuneralManager().getOrderItem(this, params,
				new HttpResponseHandler<HrOrderItenNote>() {

					@Override
					public void onStart(Request request, int id) {

					}

					@Override
					public void onSuccess(HrOrderItenNote mData) {
						// TODO Auto-generated method stub
						tvList.get(0).setText(
								"订单编号：" + mData.getOrderNum() + "");
						tvList.get(1).setText(
								"经办人：" + mData.getAgentmanName() + "");
						tvList.get(2)
								.setText("执行名称：" + mData.getSkuName() + "");
						tvList.get(3).setText(
								"执行人员：" + mData.getExecutorName() + "");
						tvList.get(4).setText(
								"治丧地址：" + mData.getFuneralAddress() + "");
						tvList.get(5).setText(
								"接单时间："
										+mData.getAcceptTime());
						tvList.get(6).setText(
								"开始执行时间："
										+mData.getStartTime());
						tvList.get(7)
								.setText(
										"执行完成时间："
												+ mData.getEndTime());
						tvList.get(8).setText(
								"白事顾问评价：" + mData.getExecutorNote() + "");
					}


					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	@OnClick({ R.id.tv_pass, R.id.tv_unpass })
	void passClick(View v) {

	}
}
