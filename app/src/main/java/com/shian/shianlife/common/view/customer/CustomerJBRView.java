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
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerAgentmanParams;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

public class CustomerJBRView extends BaseCustomerView {
	private View v;
	@InjectViews({ R.id.et_jbr_0, R.id.et_jbr_1, R.id.et_jbr_2 })
	List<EditText> etList;
	@InjectView(R.id.lbs)
	LBSLocalView lbs;
	@InjectView(R.id.sp_jbr_0)
	Spinner sp0;
	@InjectView(R.id.tv_jbr_date)
	TextView tvdate;

	public CustomerJBRView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_customer_jbr, this);
		ButterKnife.inject(this, v);
	}

	public CustomerJBRView(Context context) {
		this(context, null);
	}

	public void initData() {
		super.initData();
		initSp1("其他");
		// lbs.setETVisiable(View.GONE);
		HpConsultIdParams params = new HpConsultIdParams();
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra("consultId", 0));
		MHttpManagerFactory.getAccountManager().getCustomerAgentman(getContext(), params,
				new HttpResponseHandler<HrConsultAgentman>() {

					@Override
					public void onSuccess(HrConsultAgentman result) {
						// TODO Auto-generated method stub
						if (result.getConsultAgentman() == null) {
							lbs.setLocalParams(0, 0, 0);
							return;
						}
						lbs.setLocalParams(result.getConsultAgentman().getAddressProvince(),
								result.getConsultAgentman().getAddressCity(),
								result.getConsultAgentman().getAddressArea());
						lbs.setDetailAddress(result.getConsultAgentman().getAddressSuffix());
						etList.get(0).setText(result.getConsultAgentman().getName());
						etList.get(1).setText(result.getConsultAgentman().getLinkInfo());

						tvdate.setText(TransitionDate.DateToStr(new Date(result.getConsultAgentman().getBirthday()),
								"yyyy-MM-dd"));
						initSp1(result.getConsultAgentman().getRelation());

						// etList.get(2).setText(
						// result.getConsultAgentman().getAddressSuffix());
//						etList.get(3).setText(result.getConsultAgentman().getAddressSuffix());
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

	private void initSp1(String i) {
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), R.array.gx,
				android.R.layout.simple_spinner_item);
		final String[] arrs=getResources().getStringArray( R.array.gx);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp0.setAdapter(province_adapter);
		sp0.setSelection(Utils.getArrayINdex(arrs,i));
		sp0.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				params.setRelation(arrs[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@OnClick(R.id.tv_jbr_date)
	void onDateDailog(final TextView v) {
		DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder.getInstance(getContext());
//		dialog.setShowHour(false);
		dialog.show();
		dialog.setOnSaveListener(new OnSaveListener() {

			@Override
			public void onSaveSelectedDate(String selectedDate) {
				// TODO Auto-generated method stub
				v.setText(selectedDate);
			}
		});
	}

	private HpSaveCustomerAgentmanParams params = new HpSaveCustomerAgentmanParams();

	@OnClick(R.id.tv_editorder)
	void saveData() {
		String name = etList.get(0).getText().toString();
		String phone = etList.get(1).getText().toString();
		String othergx = etList.get(2).getText().toString();
		String other = lbs.getDetailAddress();
		String date = tvdate.getText().toString();
		if (TextUtils.isEmpty(name)) {
			ToastUtils.show(getContext(), "经办人不能为空");
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			ToastUtils.show(getContext(), "经办人电话不能为空");
			return;
		}
//		if (TextUtils.isEmpty(date)) {
//			ToastUtils.show(getContext(), "出生日期不能为空");
//			return;
//		}
		if (!Utils.isPhoneNumber(phone)) {
			ToastUtils.show(getContext(), "请输入正确的号码");
			return;
		}
		if (TextUtils.isEmpty(other)) {
			ToastUtils.show(getContext(), "请输入具体地址");
			return;
		}
		lbs.getTalkAddress().setSuffix(other);
		params.setAddress(lbs.getTalkAddress());
//		params.setBirthday(TransitionDate.StrToDate(date, "yyyy-MM-dd").getTime());
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra("consultId", 0));
		params.setLinkInfo(phone);
		params.setName(name);
		MHttpManagerFactory.getAccountManager().saveCustomerAgentman(getContext(), params,
				new HttpResponseHandler<Object>() {

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
}
