package com.shian.shianlife.common.view.customer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerFuneral;
import com.shian.shianlife.provide.result.HrGetCustomerFuneral;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;

public class CustomerZSView extends BaseCustomerView {
	private View v;
	private long orderId;
	@InjectViews({ R.id.lbs0, R.id.lbs1, R.id.lbs2 })
	List<LBSLocalView> lbsList;
	@InjectViews({ R.id.et_zs_0, R.id.et_zs_1, R.id.et_zs_2 })
	List<TextView> tvList;
	@InjectViews({  R.id.et_zs_6 })
	List<EditText> etList;

	public CustomerZSView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_customer_zs,
				this);
		ButterKnife.inject(this, v);
		orderId = ((Activity) getContext()).getIntent().getLongExtra("orderId",
				0);
//		for (LBSLocalView lbs : lbsList) {
//			lbs.setETVisiable(View.GONE);
//		}
	}

	public CustomerZSView(Context context) {
		this(context, null);
	}

	public void initData() {
		super.initData();
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderId(orderId);
		MHttpManagerFactory.getAccountManager().getCustomerFuneral(
				getContext(), params,
				new HttpResponseHandler<HrGetCustomerFuneral>() {

					@Override
					public void onSuccess(HrGetCustomerFuneral result) {
						// TODO Auto-generated method stub
						if (result.getOrderFuneral() == null) {
							lbsList.get(0).setLocalParams(0, 0, 0);
							lbsList.get(1).setLocalParams(0, 0, 0);
							lbsList.get(2).setLocalParams(0, 0, 0);
						}
						tvList.get(0).setText(
								Utils.getDateUtils(result.getOrderFuneral()
										.getDieTime()));
						tvList.get(1).setText(
								Utils.getDateUtils(result.getOrderFuneral()
										.getCremationTime()));
						tvList.get(2).setText(
								Utils.getDateUtils(result.getOrderFuneral()
										.getFuneralTime()));
						lbsList.get(0).setLocalParams(
								result.getOrderFuneral()
										.getDieAddressProvince(),
								result.getOrderFuneral().getDieAddressCity(),
								result.getOrderFuneral().getDieAddressArea());
						lbsList.get(1)
								.setLocalParams(
										result.getOrderFuneral()
												.getParlorAddressProvince(),
										result.getOrderFuneral()
												.getParlorAddressCity(),
										result.getOrderFuneral()
												.getParlorAddressArea());
						lbsList.get(2).setLocalParams(
								result.getOrderFuneral()
										.getFuneralAddressProvince(),
								result.getOrderFuneral()
										.getFuneralAddressCity(),
								result.getOrderFuneral()
										.getFuneralAddressArea());
						lbsList.get(0).setDetailAddress(
								result.getOrderFuneral().getDieAddressSuffix());
						lbsList.get(1).setDetailAddress(
								result.getOrderFuneral()
										.getParlorAddressSuffix());
						lbsList.get(2).setDetailAddress(
								result.getOrderFuneral()
										.getFuneralAddressSuffix());
						etList.get(0).setText(
								result.getOrderFuneral().getNote());
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	@OnClick({ R.id.et_zs_0, R.id.et_zs_1, R.id.et_zs_2 })
	void onDateDailog(final TextView v) {
		DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
				.getInstance(getContext());
		dialog.show();
		dialog.setOnSaveListener(new OnSaveListener() {

			@Override
			public void onSaveSelectedDate(String selectedDate) {
				// TODO Auto-generated method stub
				v.setText(selectedDate);
			}
		});
	}

	@OnClick(R.id.tv_editorder)
	void saveData() {
		String time0 = tvList.get(0).getText().toString();
		String time1 = tvList.get(1).getText().toString();
		String time2 = tvList.get(2).getText().toString();
		if (TextUtils.isEmpty(time0)) {
			ToastUtils.show(getContext(), "去世时间不能为空");
			return;
		}
		if (TextUtils.isEmpty(time1)) {
			ToastUtils.show(getContext(), "出殡时间不能为空");
			return;
		}
		if (TextUtils.isEmpty(time2)) {
			ToastUtils.show(getContext(), "预计火化时间不能为空");
			return;
		}
		if(TextUtils.isEmpty(lbsList.get(0).getDetailAddress())){
			ToastUtils.show(getContext(), "请输入去世具体地址");
			return;
		}
		if(TextUtils.isEmpty(lbsList.get(1).getDetailAddress())){
			ToastUtils.show(getContext(), "请输出殡世具体地址");
			return;
		}
		if(TextUtils.isEmpty(lbsList.get(2).getDetailAddress())){
			ToastUtils.show(getContext(), "请输入火化世具体地址");
			return;
		}
//		lbsList.get(0).getTalkAddress()
//				.setSuffix(etList.get(0).getText().toString());
//		lbsList.get(1).getTalkAddress()
//				.setSuffix(etList.get(1).getText().toString());
//		lbsList.get(2).getTalkAddress()
//				.setSuffix(etList.get(2).getText().toString());

		HpSaveCustomerFuneral params = new HpSaveCustomerFuneral();
		params.setOrderId(orderId);
		params.setDieTime(getlDate(time0));
		params.setFuneralTime(getlDate(time1));
		params.setCremationTime(getlDate(time2));
		params.setNote(etList.get(0).getText().toString());
		params.setDieAddress(lbsList.get(0).getTalkAddress());
		params.setFuneralAddress(lbsList.get(2).getTalkAddress());
		params.setParlorAddress(lbsList.get(1).getTalkAddress());
		MHttpManagerFactory.getAccountManager().saveCustomerFuneral(
				getContext(), params, new HttpResponseHandler<Object>() {

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						ToastUtils.show(getContext(), "保存成功");
					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	private long getlDate(String d) {
		return TransitionDate.StrToDate(d, "yyyy-MM-dd HH:ss").getTime();
	}
}
