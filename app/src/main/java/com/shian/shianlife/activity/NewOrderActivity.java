package com.shian.shianlife.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import butterknife.InjectView;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpAddConsultParams;
import com.shian.shianlife.provide.result.HrAddConsultResult;

public class NewOrderActivity extends BaseActivity {
	@InjectView(R.id.lbs)
	LBSLocalView lbsView;
	@InjectView(R.id.sp_order_ywlx)
	Spinner spYW;
	@InjectView(R.id.et_neworder_name)
	EditText etName;
	@InjectView(R.id.et_neworder_phone)
	EditText etPhone;
	@InjectView(R.id.et_neworder_bz)
	EditText etBz;
	@InjectView(R.id.et_otheradd)
	EditText etOther;
	private HpAddConsultParams params = new HpAddConsultParams();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_neworder);
		setTitle("新建工单");
		initView();
	}

	private void initView() {
		// lbsView.setETVisiable(View.GONE);
		lbsView.setLocalParams(0, 0, 0);
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(this, R.array.ywlx,
				android.R.layout.simple_spinner_item);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spYW.setAdapter(province_adapter);
		spYW.setSelection(0);
		spYW.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				params.setBusinessType(position + 1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	@OnClick(R.id.tv_editorder)
	void editOrderClick(View v) {
		editOrder(false);
	}

	@OnClick(R.id.tv_qiatan)
	void qiatan() {
		editOrder(true);
	}

	private void editOrder(final boolean isF) {

		final String name = etName.getText().toString();
		final String phone = etPhone.getText().toString();
		final String bz = etBz.getText().toString();
		final String address = lbsView.getDetailAddress();
		final HpAddConsultParams.TalkAddress ad = lbsView.getTalkAddress();

		if (name == null || name.equals("")) {
			ToastUtils.show(this, "客户姓名不能为空");
			return;
		}
		if (phone == null || phone.equals("")) {
			ToastUtils.show(this, "客户电话不能为空");
			return;
		}
		if (address == null || address.equals("")) {
			ToastUtils.show(this, "详细地址不能为空");
			return;
		}
		if (params.getBusinessType() == 0) {
			ToastUtils.show(this, "请选择业务类型");
			return;
		}
		if (!Utils.isPhoneNumber(phone)) {
			ToastUtils.show(this, "请输入正确的号码");
			return;
		}
		TipsDialog mDialog = new TipsDialog(this);
		mDialog.setTitle("点击确认后，信息将无法修改\n请仔细核实信息");
		mDialog.setTopButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		mDialog.setBottomButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
//				HpAddConsultParams.TalkAddress ad = lbsView.getTalkAddress();
				params.setCustomerName(name);
				params.setCustomerMobile(phone);
				params.setCustomerAddress(ad);
				params.setDescription(bz);
				MHttpManagerFactory.getAccountManager().addConsult(NewOrderActivity.this, params,
						new HttpResponseHandler<HrAddConsultResult>() {

							@Override
							public void onSuccess(HrAddConsultResult result) {
								if (result != null) {
									ToastUtils.show(getBaseContext(), "创建成功");
									if(!isF){
									Intent in = new Intent(NewOrderActivity.this, EditOrderActivity.class);
									in.putExtra("consultId", result.getConsultId());
									startActivity(in);
									}
									OrderFragment.C_bOrder_isRefresh=true;
									finish();
								}
							}

							@Override
							public void onStart() {

							}

							@Override
							public void onError(String message) {

							}
						});

			}
		});
		mDialog.show();

	}
}
