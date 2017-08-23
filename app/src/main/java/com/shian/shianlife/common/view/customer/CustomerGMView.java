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
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerCemeteryParams;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder.OnSaveListener;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import okhttp3.Request;

public class CustomerGMView extends BaseCustomerView {
    private View v;

    @InjectViews({R.id.sp_gm_0, R.id.sp_gm_3})
    List<Spinner> spList;
    @InjectViews({R.id.et_gm_0, R.id.et_gm_1, R.id.et_gm_2, R.id.et_gm_yx,
            R.id.et_gm_dq, R.id.et_gm_mc})
    List<EditText> etList;
    @InjectView(R.id.tv_gm_date)
    TextView tvDate;
    @InjectView(R.id.ll_gm_main)
    View llMain;
    @InjectView(R.id.ll_gm_yx)
    View llYx;
    @InjectView(R.id.et_gm_dq)
    EditText etGm;
    private long consultId;

    public CustomerGMView(Context context, AttributeSet attrs) {
        super(context, attrs);
        v = LayoutInflater.from(context).inflate(R.layout.view_customer_gm,
                this);
        ButterKnife.inject(this, v);
    }

    public CustomerGMView(Context context) {
        this(context, null);
    }

    public void initData() {
        super.initData();
        initSp0(0);
        initSp1(0);
        consultId = ((Activity) getContext()).getIntent().getLongExtra(
                "consultId", 0);
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getCemeteryManager().getCustomerCemetery(
                getContext(), params,
                new HttpResponseHandler<HrConsultCemetery>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrConsultCemetery result) {
                        // TODO Auto-generated method stub
                        cemeteryId = result.getConsultCemetery()
                                .getCemeteryId();
                        initSp0(result.getConsultCemetery().getBuyFlag());
                        etList.get(0).setText(
                                result.getConsultCemetery().getGraveAddress());
                        etList.get(1)
                                .setText(
                                        result.getConsultCemetery()
                                                .getBuyAmount() + "");
                        etList.get(2).setText(
                                result.getConsultCemetery().getNote());
                        etList.get(3).setText(
                                result.getConsultCemetery().getBuyIntention());
                        etList.get(4).setText(
                                result.getConsultCemetery().getAddress());
                        etList.get(5).setText(
                                result.getConsultCemetery().getCemeteryName());
                        tvDate.setText(TransitionDate.DateToStr(new Date(result
                                        .getConsultCemetery().getBuryTime()),
                                "yyyy-MM-dd"));
                        initSp1(result.getConsultCemetery()
                                .getCemeteryUsageStatus());
                        etGm.setText(result.getConsultCemetery().getAddress());
                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void initSp0(int i) {
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
                .createFromResource(getContext(), R.array.isgm,
                        android.R.layout.simple_spinner_item);
        province_adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spList.get(0).setAdapter(province_adapter);
        spList.get(0).setSelection(i - 1);
        spList.get(0).setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0) {
                    llMain.setVisibility(View.GONE);
                    llYx.setVisibility(View.VISIBLE);
                } else {
                    llMain.setVisibility(View.VISIBLE);
                    llYx.setVisibility(View.GONE);
                }
                params.setBuyFlag(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private long cemeteryId;

    private void initSp1(int i) {
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gm_qk,
                        android.R.layout.simple_spinner_item);
        province_adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spList.get(1).setAdapter(province_adapter);
        spList.get(1).setSelection(i - 1);
        spList.get(1).setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                params.setCemeteryUsageStatus(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.tv_gm_date)
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

    private HpSaveCustomerCemeteryParams params = new HpSaveCustomerCemeteryParams();

    @OnClick(R.id.tv_editorder)
    void saveData() {
        String local = etList.get(0).getText().toString();
        String amount = etList.get(1).getText().toString();
        String note = etList.get(2).getText().toString();
        String yx = etList.get(3).getText().toString();
        String dq = etList.get(4).getText().toString();
        String mc = etList.get(5).getText().toString();
        String date = tvDate.getText().toString();


        if (params.getBuyFlag() <= 1) {
            if (TextUtils.isEmpty(yx)) {
                ToastUtils.show(getContext(), "购墓意向不能为空");
                return;
            }
            params.setAddress(etGm.getText().toString());
        } else {
//			if (TextUtils.isEmpty(etGm.getText().toString())) {
//				ToastUtils.show(getContext(), "公墓地区不能为空");
//				return;
//			}
            if (TextUtils.isEmpty(mc)) {
                ToastUtils.show(getContext(), "公墓名称不能为空");
                return;
            }
        }

        if (!TextUtils.isEmpty(date))
            params.setBuryTime(TransitionDate.StrToDate(date, "yyyy-MM-dd")
                    .getTime());
        params.setBuyAmount(amount);
        params.setBuyIntention(yx);
        params.setCemeteryId(cemeteryId);
        params.setCemeteryName(mc);
        params.setConsultId(consultId);
        params.setGraveAddress(local);
        params.setNote(note);
        MHttpManagerFactory.getCemeteryManager().saveCustomerCemetery(
                getContext(), params, new HttpResponseHandler<Object>() {

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
