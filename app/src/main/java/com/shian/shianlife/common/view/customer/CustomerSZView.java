package com.shian.shianlife.common.view.customer;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import okhttp3.Request;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerUsageParams;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;

public class CustomerSZView extends BaseCustomerView {
	private View v;
	@InjectView(R.id.et_sz_name)
	EditText etName;
	@InjectView(R.id.et_sz_age)
	EditText etAge;
	@InjectView(R.id.et_sz_bz)
	EditText etBz;
	@InjectView(R.id.et_sz_height)
	EditText etHeight;
//	@InjectView(R.id.et_sz_otheradd)
//	EditText etOtherAdd;
	@InjectView(R.id.et_sz_size)
	EditText etSize;
	@InjectView(R.id.et_sz_wp)
	EditText etWp;
	@InjectView(R.id.lbs)
	LBSLocalView lbs;
	@InjectViews({ R.id.rb_sz_wz, R.id.rb_sz_man, R.id.rb_sz_felman,
			R.id.rb_sz_bm })
	List<RadioButton> rbList;
	@InjectView(R.id.sp_sz_szxz)
	Spinner spSzxz;
	@InjectView(R.id.tv_sz_birthd)
	TextView tvBirthd;

	public CustomerSZView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.view_customer_sz,
				this);
		ButterKnife.inject(this, v);
	}

	public CustomerSZView(Context context) {
		this(context, null);
	}

	public void initData() {
		super.initData();
//		lbs.setETVisiable(View.GONE);
		initSp1("健康");setSexListener();
		HpConsultIdParams params = new HpConsultIdParams();
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra(
				"consultId", 0));
		MHttpManagerFactory.getFuneralManager().getCustomerUsage(getContext(),
				params, new HttpResponseHandler<HrConsultUsageResult>() {

					@Override
					public void onStart(Request request, int id) {

					}

					@Override
					public void onSuccess(HrConsultUsageResult result) {
						// TODO Auto-generated method stub
						if (result.getConsultUsage() == null) {
							lbs.setLocalParams(0, 0, 0);
							return;
						}
						lbs.setLocalParams(result.getConsultUsage()
								.getCurAddressProvince(), result
								.getConsultUsage().getCurAddressCity(), result
								.getConsultUsage().getCurAddressArea());
						lbs.setDetailAddress(result.getConsultUsage().getCurAddressSuffix());
						etAge.setText(result.getConsultUsage().getAge());
						etBz.setText(result.getConsultUsage().getNote());
						etHeight.setText(result.getConsultUsage().getHeight());
						etName.setText(result.getConsultUsage().getName());
						etSize.setText(result.getConsultUsage().getShoeSize());
						etWp.setText(result.getConsultUsage().getIntimeReady());
						rbList.get(result.getConsultUsage().getSex() - 1)
								.setChecked(true);
						tvBirthd.setText(TransitionDate.DateToStr(new Date(
								result.getConsultUsage().getBirthday()),
								"yyyy-MM-dd"));
						initSp1(result.getConsultUsage().getState());

					}


					@Override
					public void onError(String message) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void initSp1(String i) {
		ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
				.createFromResource(getContext(), R.array.szxz,
						android.R.layout.simple_spinner_item);
		final String[] arrs=getResources().getStringArray(R.array.szxz);
		province_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSzxz.setAdapter(province_adapter);
		spSzxz.setSelection(Utils.getArrayINdex(arrs,i));
		spSzxz.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				params.setState(arrs[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@OnClick(R.id.tv_sz_birthd)
	void onDateDailog(final TextView v) {
		DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
				.getInstance(getContext());
		dialog.setShowHour(false);
		dialog.show();
		dialog.setOnSaveListener(new OnSaveListener() {

			@Override
			public void onSaveSelectedDate(String selectedDate) {
				// TODO Auto-generated method stub
				v.setText(selectedDate);
			}
		});
	}
	
	private void setSexListener(){
		for(int i=0;i<rbList.size();i++){
			setCheckListener(rbList.get(i),i);
		}
	}
	private void setCheckListener(RadioButton rb,final int index){
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				params.setSex(index+1);
			}
		});
	}

	private HpSaveCustomerUsageParams params = new HpSaveCustomerUsageParams();

	@OnClick(R.id.tv_editorder)
	void saveData(View v) {
		String age = etAge.getText().toString();
		String bz = etBz.getText().toString();
		String height = etHeight.getText().toString();
		String name = etName.getText().toString();
		String other = lbs.getDetailAddress();
		String size = etSize.getText().toString();
		String wp = etWp.getText().toString();
		String birthd = tvBirthd.getText().toString();
		if (TextUtils.isEmpty(age)) {
			ToastUtils.show(getContext(), "逝者年龄不能为空");
			return;
		}
		if (TextUtils.isEmpty(wp)) {
			ToastUtils.show(getContext(), "寿衣准备情况不能为空");
			return;
		}
		if (TextUtils.isEmpty(name)) {
			ToastUtils.show(getContext(), "逝者姓名不能为空");
			return;
		}
		if (TextUtils.isEmpty(size)) {
			ToastUtils.show(getContext(), "逝者鞋码不能为空");
			return;
		}
		if (TextUtils.isEmpty(birthd)) {
			ToastUtils.show(getContext(), "逝者生日不能为空");
			return;
		}
		if(TextUtils.isEmpty(other)){
			ToastUtils.show(getContext(), "请输入具体地址");
			return;
		}
		lbs.getTalkAddress().setSuffix(other);
		params.setAge(age);
		params.setBirthday(TransitionDate.StrToDate(birthd, "yyyy-MM-dd")
				.getTime());
		params.setConsultId(((Activity) getContext()).getIntent().getLongExtra(
				"consultId", 0));
		params.setCurAddress(lbs.getTalkAddress());
		params.setHeight(height);
		params.setIntimeReady(wp);
		params.setName(name);
		params.setNote(bz);
		params.setShoeSize(size);

		MHttpManagerFactory.getFuneralManager().saveCustomerUsage(getContext(),
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
