package com.shian.shianlife.common.view.customer;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerTalkParams;
import com.shian.shianlife.provide.result.HrConsultFuneral;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import okhttp3.Request;

public class CustomerQtView extends BaseCustomerView {
	private View v;
	@InjectView(R.id.lbs)
	LBSLocalView lbsView;
	@InjectViews({ R.id.et_cqt_0, R.id.et_cqt_1, R.id.et_cqt_2, R.id.et_cqt_4 })
	List<EditText> etList;
//	@InjectView(R.id.et_otheradd)
//	EditText etOthers;
	@InjectViews({ R.id.sp_cqt_0, R.id.sp_cqt_1 })
	List<Spinner> spList;

	public CustomerQtView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_customer_qt,
				this);
		ButterKnife.inject(this, v);
	}

	public CustomerQtView(Context context) {
		this(context, null);
	}

	public void initData() {
		super.initData();
//		lbsView.setETVisiable(View.GONE);
		etList.get(0).setText(AppContansts.LocalString);
		HpConsultIdParams params = new HpConsultIdParams();
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra(
				"consultId", 0));
		initSp0("佛教");
		initSp1("简办");
		MHttpManagerFactory.getFuneralManager().getCustomerTalk(getContext(),
				params, new HttpResponseHandler<HrConsultFuneral>() {

					@Override
					public void onStart(Request request, int id) {

					}

					@Override
					public void onSuccess(HrConsultFuneral result) {
						// TODO Auto-generated method stub
						if (result.getConsultFuneral() == null) {
							lbsView.setLocalParams(0, 0, 0);
							return;
						}
						if (!TextUtils.isEmpty(result.getConsultFuneral()
								.getTalkGpsAddress())) {
							etList.get(0).setText(
									result.getConsultFuneral()
											.getTalkGpsAddress());
						}
						etList.get(1).setText(
								result.getConsultFuneral().getFrBeliefDesc());
						etList.get(2)
								.setText(
										result.getConsultFuneral()
												.getFrDirectionDesc());
						// etList.get(3).setText(result.getFrAddress());
						etList.get(3).setText(
								result.getConsultFuneral().getOther());
						initSp0(result.getConsultFuneral().getFrBelief());
						initSp1(result.getConsultFuneral().getFrDirection());
						lbsView.setLocalParams(result.getConsultFuneral()
								.getTalkAddressProvince(), result
								.getConsultFuneral().getTalkAddressCity(),
								result.getConsultFuneral().getTalkAddressArea());
						lbsView.setDetailAddress(result.getConsultFuneral()
								.getTalkAddressSuffix());
					}



					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void initSp0(String i) {
		final String[] arrs=getResources().getStringArray( R.array.zsxy);
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
				.createFromResource(getContext(), R.array.zsxy,
						android.R.layout.simple_spinner_item);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spList.get(0).setAdapter(province_adapter);
		spList.get(0).setSelection(Utils.getArrayINdex(arrs,i));
		spList.get(0).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				params.setFrBelief(arrs[position ]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	private void initSp1(String i) {
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
				.createFromResource(getContext(), R.array.zsfx,
						android.R.layout.simple_spinner_item);
		final String[] arrs=getResources().getStringArray( R.array.zsfx);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spList.get(1).setAdapter(province_adapter);
		spList.get(1).setSelection(Utils.getArrayINdex(arrs,i));
		spList.get(1).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				params.setFrDirection(arrs[position] );
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	HpSaveCustomerTalkParams params = new HpSaveCustomerTalkParams();

	@OnClick(R.id.tv_editorder)
	void saveData(View v) {
		String qtdz = etList.get(0).getText().toString();
//		if (qtdz == null || qtdz.equals("")) {
//			ToastUtils.show(getContext(), "洽谈地址不能为空");
//			return;
//		}
		if(TextUtils.isEmpty(lbsView.getDetailAddress())){
			ToastUtils.show(getContext(), "请输入具体地址");
			return;
		}
		lbsView.getTalkAddress().setSuffix(lbsView.getDetailAddress());
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra(
				"consultId", 0));
		params.setFrBeliefDesc(etList.get(1).getText().toString());
		params.setFrDirectionDesc(etList.get(2).getText().toString());
		params.setOther(etList.get(3).getText().toString());
		params.setTalkGpsAddress(etList.get(0).getText().toString());
		params.setTalkAddress(lbsView.getTalkAddress());
		MHttpManagerFactory.getFuneralManager().saveCustomerTalk(getContext(),
				params, new HttpResponseHandler<Object>() {

					@Override
					public void onStart(Request request, int id) {

					}

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						ToastUtils.show(getContext(), "保存成功");
					}

					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}
}
