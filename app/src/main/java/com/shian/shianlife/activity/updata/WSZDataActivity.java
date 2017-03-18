package com.shian.shianlife.activity.updata;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;

import com.shian.shianlife.provide.params.HpSaveCustomerUsageParams;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WSZDataActivity extends BaseActivity {
    TextView mTVNext;

    EditTextViewNormal mWriteDeadName;
    EditTextViewNormal mWriteCardId;
    EditTextViewNormal mWriteDeadAge;
    EditTextViewNormal mWriteShoesSize;
    EditTextViewNormal mWriteRemark;

    SpinnerViewNormal mWriteDeadSex;
    SpinnerViewNormal mWriteDeadState;
    SpinnerViewNormal mWriteDeadClothes;
    SpinnerViewNormal mWriteOtherState;

    TimeSelectViewNormal mWriteTimeSelect;

    MapSelectViewNormal mWriteMapSelectNow;
    long consultId;
    long orderId;

    private HpSaveCustomerUsageParams params = new HpSaveCustomerUsageParams();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_wszdata);

        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);

        init();
        initView();
        initData();
    }

    private void init() {
        setTitle("往生者信息");
    }

    private void initView() {
        mWriteDeadName = (EditTextViewNormal) findViewById(R.id.write_deadname);
        mWriteCardId = (EditTextViewNormal) findViewById(R.id.write_deadcardid);
        mWriteDeadAge = (EditTextViewNormal) findViewById(R.id.write_deadage);
        mWriteDeadSex = (SpinnerViewNormal) findViewById(R.id.write_deadsex);
        mWriteShoesSize = (EditTextViewNormal) findViewById(R.id.write_deadshoe);
        mWriteDeadState = (SpinnerViewNormal) findViewById(R.id.write_deadstate);
        mWriteTimeSelect = (TimeSelectViewNormal) findViewById(R.id.write_timeselect);
        mWriteMapSelectNow = (MapSelectViewNormal) findViewById(R.id.write_mapselect_now);
        mWriteDeadClothes = (SpinnerViewNormal) findViewById(R.id.write_deadclothes);
        mWriteRemark = (EditTextViewNormal) findViewById(R.id.write_remark);
        mWriteOtherState = (SpinnerViewNormal) findViewById(R.id.write_otherstate);

        mTVNext = (TextView) findViewById(R.id.tv_editorder);
        mTVNext.setOnClickListener(onClickListener);

        mWriteDeadSex.initSpinner(R.array.sex);
        mWriteDeadState.initSpinner(R.array.szxz);
        mWriteOtherState.initSpinner(R.array.szxz);
        mWriteDeadClothes.initSpinner(R.array.syxx);
        mWriteMapSelectNow.setNumView(0);
    }


    private void initData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerUsage(WSZDataActivity.this,
                params, new HttpResponseHandler<HrConsultUsageResult>() {

                    @Override
                    public void onSuccess(HrConsultUsageResult result) {
                        // TODO Auto-generated method stub

                        Utils.LogVPrint("Name:" + result.getConsultUsage().getName());
                        Utils.LogVPrint("CardId:" + result.getConsultUsage().getCardId());
                        Utils.LogVPrint("Age:" + result.getConsultUsage().getAge());
                        Utils.LogVPrint("Sex:" + result.getConsultUsage().getSex());
                        Utils.LogVPrint("ShoeSize:" + result.getConsultUsage().getShoeSize());
                        Utils.LogVPrint("State:" + result.getConsultUsage().getState());
                        Utils.LogVPrint("Birthday:" + result.getConsultUsage().getBirthday());
                        Utils.LogVPrint("Location:" + result.getConsultUsage().getLocation());
                        Utils.LogVPrint("ClothesData:" + result.getConsultUsage().getClothesData());
                        Utils.LogVPrint("OtherHealth:" + result.getConsultUsage().getOtherHealth());
                        Utils.LogVPrint("Note:" + result.getConsultUsage().getNote());
                        Utils.LogVPrint("AgentmanLocation:" + result.getConsultUsage().getAgentmanLocation());
                        Utils.LogVPrint("ZsLocation:" + result.getConsultUsage().getZsLocation());
                        Utils.LogVPrint("DeadLocation:" + result.getConsultUsage().getDeadLocation());

                        List<String> listLocationData = new ArrayList<String>();
                        if (result.getConsultUsage().getAgentmanLocation() != null) {
                            listLocationData.add(result.getConsultUsage().getAgentmanLocation());
                        }
                        if (result.getConsultUsage().getZsLocation() != null) {
                            listLocationData.add(result.getConsultUsage().getZsLocation());
                        }
                        if (result.getConsultUsage().getDeadLocation() != null) {
                            listLocationData.add(result.getConsultUsage().getDeadLocation());
                        }
                        if (result.getConsultUsage().getLocation() != null) {
                            listLocationData.add(result.getConsultUsage().getLocation());
                        }
                        mWriteDeadSex.setData(result.getConsultUsage().getSex()-1);
                        mWriteDeadState.setData(result.getConsultUsage().getState());
                        mWriteDeadClothes.setData(result.getConsultUsage().getClothesData());
                        mWriteOtherState.setData(result.getConsultUsage().getOtherHealth());


                        mWriteMapSelectNow.initAutoTextView(listLocationData);
                        mWriteDeadName.setData(result.getConsultUsage().getName());
                        mWriteCardId.setData(result.getConsultUsage().getCardId());
                        mWriteDeadAge.setData(result.getConsultUsage().getAge());
                        mWriteShoesSize.setData(result.getConsultUsage().getShoeSize());

                        mWriteTimeSelect.setData(TransitionDate.DateToStr(new Date(
                                        result.getConsultUsage().getBirthday()),
                                "yyyy-MM-dd"));
                        mWriteMapSelectNow.setData(result.getConsultUsage().getLocation());
                        mWriteRemark.setData(result.getConsultUsage().getNote());
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


    private void initSp1(final int type, String name, Spinner sp) {
        int data = 0;
        if (type == 0) {
            data = R.array.szxz;
        } else if (type == 1) {
            data = R.array.syxx;
        } else if (type == 2) {
            data = R.array.szxz;
        }
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter
                .createFromResource(WSZDataActivity.this, data,
                        android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(data);
        province_adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(province_adapter);
        sp.setSelection(Utils.getArrayINdex(arrs, name));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (type == 0) {
                    params.setState(arrs[position]);
                } else if (type == 1) {
                    params.setClothesData(arrs[position]);
                } else if (type == 2) {
                    params.setOtherHealth(arrs[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVNext) {
                upData();
            }
        }
    };

    private void upData() {
        String name = mWriteDeadName.getData();
        String carid = mWriteCardId.getData();
        String age = mWriteDeadAge.getData();
        String size = mWriteShoesSize.getData();
        String birthd = mWriteTimeSelect.getData();
        String location = mWriteMapSelectNow.getData();
        String bz = mWriteRemark.getData();

        if (TextUtils.isEmpty(name)) {
            ToastUtils.show(WSZDataActivity.this, "往生者姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(carid)) {
            ToastUtils.show(WSZDataActivity.this, "往生者身份证号码不能为空");
            return;
        }
        if (carid.getBytes().length != 18) {
            ToastUtils.show(WSZDataActivity.this, "往生者身份证号码不足18位");
            return;
        }
        if (TextUtils.isEmpty(age)) {
            ToastUtils.show(WSZDataActivity.this, "往生者年龄不能为空");
            return;
        }
        if (TextUtils.isEmpty(size)) {
            ToastUtils.show(WSZDataActivity.this, "往生者鞋码不能为空");
            return;
        }

        if (TextUtils.isEmpty(birthd)) {
            ToastUtils.show(WSZDataActivity.this, "往生者生日不能为空");
            return;
        }
        if (TextUtils.isEmpty(location)) {
            ToastUtils.show(WSZDataActivity.this, "地址不能为空");
            return;
        }
        params.setConsultId(consultId);
        params.setName(name);
        params.setCardId(carid);
        params.setAge(age);
        params.setShoeSize(size);
        params.setBirthday(TransitionDate.StrToDate(birthd, "yyyy-MM-dd")
                .getTime());
        params.setLocation(location);
        params.setNote(bz);
        params.setSex(mWriteDeadSex.getSelectPosition() + 1);
        params.setState(mWriteDeadState.getData());
        params.setClothesData(mWriteDeadClothes.getData());
        params.setOtherHealth(mWriteOtherState.getData());

        Utils.LogVPrint("Name:" + params.getName());
        Utils.LogVPrint("CardId:" + params.getCardId());
        Utils.LogVPrint("Age:" + params.getAge());
        Utils.LogVPrint("Sex:" + params.getSex());
        Utils.LogVPrint("ShoeSize:" + params.getShoeSize());
        Utils.LogVPrint("State:" + params.getState());
        Utils.LogVPrint("Birthday:" + params.getBirthday());
        Utils.LogVPrint("Location:" + params.getLocation());
        Utils.LogVPrint("ClothesData:" + params.getClothesData());
        Utils.LogVPrint("OtherHealth:" + params.getOtherHealth());
        Utils.LogVPrint("Note:" + params.getNote());

        MHttpManagerFactory.getAccountManager().saveCustomerUsage(WSZDataActivity.this,
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(WSZDataActivity.this, "保存成功");
                        Intent intent = new Intent(WSZDataActivity.this, JBRDataActivity.class);
                        intent.putExtra("consultId", consultId);
                        intent.putExtra("orderId", orderId);
                        startActivity(intent);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OrderFragment.C_bOrder_isRefresh = true;
    }


}
