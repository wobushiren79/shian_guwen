package com.shian.shianlife.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpOrderInvoic;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrOrderInvoic;

public class PayActivity extends BaseActivity {
	@InjectView(R.id.ll_pay_fp)
	LinearLayout llFP;
	@InjectViews({ R.id.et_content, R.id.et_tt, R.id.et_dz, R.id.et_sjr,
			R.id.et_phone })
	List<EditText> etList;
	@InjectView(R.id.lbs)
	LBSLocalView lbs;
	boolean isPayDing;
	private long orderId;
	@InjectView(R.id.et_otheradd)
	EditText etOther;
	@InjectView(R.id.tv_parNum)
	TextView tv_prepayNum;
	@InjectView(R.id.tv_pay_title)
	TextView tvT;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_pay_ding);
		setTitle("支付定金");
		isPayDing = getIntent().getBooleanExtra("ding", true);
		orderId = getIntent().getLongExtra("orderId", -1);
		if (!isPayDing) {
			llFP.setVisibility(View.VISIBLE);
		}
		if (isPayDing) {
			frePay();
		} else {
			setTitle("支付余款");
			tvT.setText("余款金额：");
			restPay();
			initFP();
		}
	}

	/**
	 * 发票
	 */
	private void initFP() {
		lbs.setETVisiable(View.GONE);
		initSp0(1);
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderId(orderId);
		MHttpManagerFactory.getAccountManager().getOrderInvoic(this, params,
				new HttpResponseHandler<HrOrderInvoic>() {

					@Override
					public void onSuccess(HrOrderInvoic result) {
						// TODO Auto-generated method stub
						if (result == null || result.getInvoice() == null) {
							lbs.setLocalParams(0, 0, 0);
							etList.get(2).setText(AppContansts.LocalString);
						}
						initSp0(result.getInvoice().getType());
						etList.get(0).setText(result.getInvoice().getContent());
						etList.get(1).setText(result.getInvoice().getHeader());
						etList.get(2).setText(
								result.getInvoice().getRecAddress());
						etList.get(3).setText(result.getInvoice().getRecName());
						etList.get(4)
								.setText(result.getInvoice().getRecPhone());
						lbs.setLocalParams(result.getInvoice()
								.getRecAddressProvince(), result.getInvoice()
								.getRecAddressCity(), result.getInvoice()
								.getRecAddressArea());
						etOther.setText(result.getInvoice()
								.getRecAddressSuffix());
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

	@InjectView(R.id.sp_pay_20)
	Spinner sp;

	private void initSp0(int i) {
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
				.createFromResource(this, R.array.pay_fp,
						android.R.layout.simple_spinner_item);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(province_adapter);
		sp.setSelection(i - 1);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				params.setType(position + 1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	HpOrderInvoic params = new HpOrderInvoic();

	private void sendFP() {
		lbs.getTalkAddress().setSuffix(etOther.getText().toString());
		params.setOrderId(orderId);
		params.setContent(etList.get(0).getText().toString());
		params.setHeader(etList.get(1).getText().toString());
		params.setPayAddress(etList.get(2).getText().toString());
		params.setRecName(etList.get(3).getText().toString());
		params.setRecPhone(etList.get(4).getText().toString());
		params.setRecAddress(lbs.getTalkAddress());
		MHttpManagerFactory.getAccountManager().saveOrderInvoic(this, params,
				new HttpResponseHandler<HrCommentResult>() {

					@Override
					public void onSuccess(HrCommentResult result) {
						// TODO Auto-generated method stub

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

	private String payAmount;

	private int payType;// 0:pos|1:weixin
	@InjectView(R.id.et_xj)
	EditText etXj;

	@OnClick({ R.id.cb_pos, R.id.cb_weixin, R.id.cb_zfb })
	void paySiwtch(CheckBox v) {
		if (!v.isChecked()) {
			v.setChecked(true);
		}
		if (v.getId() == R.id.cb_pos && v.isChecked()) {
			((CheckBox) findViewById(R.id.cb_zfb)).setChecked(false);
			((CheckBox) findViewById(R.id.cb_weixin)).setChecked(false);
			etXj.setVisibility(View.GONE);
			payType = 0;
		} else if (v.getId() == R.id.cb_weixin && v.isChecked()) {
			((CheckBox) findViewById(R.id.cb_zfb)).setChecked(false);
			((CheckBox) findViewById(R.id.cb_pos)).setChecked(false);
			etXj.setVisibility(View.GONE);
			payType = 1;
		} else if (v.getId() == R.id.cb_zfb && v.isChecked()) {
			payType = 2;
			((CheckBox) findViewById(R.id.cb_pos)).setChecked(false);
			((CheckBox) findViewById(R.id.cb_weixin)).setChecked(false);
			etXj.setVisibility(View.VISIBLE);
		}
	}

	@OnClick(R.id.tv_edit_commit)
	void payClick(View v) {
		if (!isPayDing) {
			sendFP();
		}
		creatEwm(payID);
	}

	private long payID;

	private void frePay() {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderId(orderId);
		MHttpManagerFactory.getAccountManager().creatFrePay(this, params,
				new HttpResponseHandler<HrCommentResult>() {

					@Override
					public void onSuccess(HrCommentResult result) {
						// TODO Auto-generated method stub
						tv_prepayNum.append(result.getActualAmount() + "");
						payAmount = result.getActualAmount() + "";
						payID = result.getPayId();
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

	private void restPay() {
		tv_prepayNum.append(getIntent().getStringExtra("payAmount") + "");
		payAmount = getIntent().getStringExtra("payAmount") + "";
		payID = getIntent().getLongExtra("payId", 0);
	}

	private void creatEwm(long payId) {
		if (payType == 0) {
			posEwm(payId);
		} else if (payType == 1) {
			weixinEwm(payId);
		} else if (payType == 2) {
			payCash(payId);
		}
	}

	private void posEwm(long payId) {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setPayId(payId);
		params.setPayAmount(payAmount);
		MHttpManagerFactory.getAccountManager().creatPosEwm(this, params,
				new HttpResponseHandler<HrCommentResult>() {

					@Override
					public void onSuccess(HrCommentResult result) {
						// TODO Auto-generated method stub
						Intent in = new Intent(PayActivity.this,
								PayEwmActivity.class);
						in.putExtra("ewm", result.getVerifyUrl());
						in.putExtra("payAmount", payAmount);
						in.putExtra("orderCode",
								getIntent().getStringExtra("orderCode"));
						startActivity(in);
						finish();
					}

					@Override
					public void onStart() {

					}

					@Override
					public void onError(String message) {

					}
				});
	}

	private void weixinEwm(long payId) {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setPayId(payId);
		MHttpManagerFactory.getAccountManager().creatWeinxinEwm(this, params,
				new HttpResponseHandler<HrCommentResult>() {

					@Override
					public void onSuccess(HrCommentResult result) {
						Intent in = new Intent(PayActivity.this,
								PayEwmActivity.class);
						in.putExtra("ewm", result.getCodeUrl());
						in.putExtra("payAmount", payAmount);
						in.putExtra("orderCode",
								getIntent().getStringExtra("orderCode"));
						startActivity(in);
						finish();
					}

					@Override
					public void onStart() {

					}

					@Override
					public void onError(String message) {

					}
				});
	}

	private void payCash(long payId) {
		String cash = etXj.getText().toString();
		if (TextUtils.isEmpty(cash)) {
			ToastUtils.show(this, "请输入票据单号");
			return;
		}
		HpOrderIdParams params = new HpOrderIdParams();
		params.setPayId(payId);
		params.setInvoiceNo(cash);
		MHttpManagerFactory.getAccountManager().payCrash(this, params,
				new HttpResponseHandler<Object>() {

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						ToastUtils.show(getBaseContext(), "提交成功");
						OrderFragment.C_bOrder_isRefresh=true;
						finish();
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
}
