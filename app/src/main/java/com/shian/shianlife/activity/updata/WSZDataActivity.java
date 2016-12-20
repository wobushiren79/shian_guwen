package com.shian.shianlife.activity.updata;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.activity.SaveTalkFailActivity;
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
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WSZDataActivity extends BaseActivity {
    EditText mETName;
    EditText mETCardId;
    EditText mETAge;
    EditText mETShoesSize;
    EditText mETRemark;

    TextView mTVBirthdayTime;
    TextView mTVNext;

    Spinner mSPHealth;
    Spinner mSPClothes;
    Spinner mSPOtherHealth;

    MapSelectLayoutView mSelectLayoutView;

    RadioButton mRBUn, mRBMan, mRBWoman, mRBSelect;
    List<RadioButton> rbList = new ArrayList<>();

    private HpSaveCustomerUsageParams params = new HpSaveCustomerUsageParams();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_wszdata);

        init();
        initView();
        initData();
    }

    private void init() {
        setTitle("往生者信息");
    }

    private void initView() {
        mETName = (EditText) findViewById(R.id.et_sz_name);
        mETCardId = (EditText) findViewById(R.id.et_sz_idcard);
        mETAge = (EditText) findViewById(R.id.et_sz_age);
        mETShoesSize = (EditText) findViewById(R.id.et_sz_size);
        mETRemark = (EditText) findViewById(R.id.et_sz_bz);

        mSPClothes = (Spinner) findViewById(R.id.sp_clothes);
        mSPHealth = (Spinner) findViewById(R.id.sp_sz_szxz);
        mSPOtherHealth = (Spinner) findViewById(R.id.sp_other_health);

        mTVBirthdayTime = (TextView) findViewById(R.id.tv_sz_birthd);
        mTVNext = (TextView) findViewById(R.id.tv_editorder);

        mRBUn = (RadioButton) findViewById(R.id.rb_sz_wz);
        mRBMan = (RadioButton) findViewById(R.id.rb_sz_man);
        mRBWoman = (RadioButton) findViewById(R.id.rb_sz_felman);
        mRBSelect = (RadioButton) findViewById(R.id.rb_sz_bm);

        mSelectLayoutView = (MapSelectLayoutView) findViewById(R.id.mapselect);
        mSelectLayoutView.setData(1);

        rbList.add(mRBUn);
        rbList.add(mRBMan);
        rbList.add(mRBWoman);
        rbList.add(mRBSelect);

        mTVBirthdayTime.setOnClickListener(onClickListener);
        mTVNext.setOnClickListener(onClickListener);

    }


    private void initData() {
        initSp1(0, "健康", mSPHealth);
        initSp1(1, "家属自己准备寿衣", mSPClothes);
        initSp1(2, "健康", mSPOtherHealth);

        setSexListener();
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((WSZDataActivity.this).getIntent().getLongExtra(
                "consultId", 0));
        MHttpManagerFactory.getAccountManager().getCustomerUsage(WSZDataActivity.this,
                params, new HttpResponseHandler<HrConsultUsageResult>() {

                    @Override
                    public void onSuccess(HrConsultUsageResult result) {
                        // TODO Auto-generated method stub

                        Log.v("this", "Name:" + result.getConsultUsage().getName());
                        Log.v("this", "CardId:" + result.getConsultUsage().getCardId());
                        Log.v("this", "Age:" + result.getConsultUsage().getAge());
                        Log.v("this", "Sex:" + result.getConsultUsage().getSex());
                        Log.v("this", "ShoeSize:" + result.getConsultUsage().getShoeSize());
                        Log.v("this", "State:" + result.getConsultUsage().getState());
                        Log.v("this", "Birthday:" + result.getConsultUsage().getBirthday());
                        Log.v("this", "Location:" + result.getConsultUsage().getLocation());
                        Log.v("this", "ClothesData:" + result.getConsultUsage().getClothesData());
                        Log.v("this", "OtherHealth:" + result.getConsultUsage().getOtherHealth());
                        Log.v("this", "Note:" + result.getConsultUsage().getNote());

                        mETName.setText(result.getConsultUsage().getName());
                        mETCardId.setText(result.getConsultUsage().getCardId());
                        mETAge.setText(result.getConsultUsage().getAge());
                        mETShoesSize.setText(result.getConsultUsage().getShoeSize());
                        mTVBirthdayTime.setText(TransitionDate.DateToStr(new Date(
                                        result.getConsultUsage().getBirthday()),
                                "yyyy-MM-dd"));
                        mSelectLayoutView.setLocation(result.getConsultUsage().getLocation());
                        mETRemark.setText(result.getConsultUsage().getNote());
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

    private void setSexListener() {
        for (int i = 0; i < rbList.size(); i++) {
            setCheckListener(rbList.get(i), i);
        }
    }

    private void setCheckListener(RadioButton rb, final int index) {
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked)
                    params.setSex(index + 1);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVBirthdayTime) {
                setBirthdayTime();
            } else if (view == mTVNext) {
                upData();
            }
        }
    };

    private void upData() {
        String name = mETName.getText().toString();
        String carid = mETCardId.getText().toString();
        String age = mETAge.getText().toString();
        String size = mETShoesSize.getText().toString();
        String birthd = mTVBirthdayTime.getText().toString();
        String location = mSelectLayoutView.getLocation();
        String bz = mETRemark.getText().toString();

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
        params.setConsultId(getIntent().getLongExtra("consultId", 0));
        params.setName(name);
        params.setCardId(carid);
        params.setAge(age);
        params.setShoeSize(size);
        params.setBirthday(TransitionDate.StrToDate(birthd, "yyyy-MM-dd")
                .getTime());
        params.setLocation(location);

        params.setNote(bz);

        Log.v("this", "Name:" + params.getName());
        Log.v("this", "CardId:" + params.getCardId());
        Log.v("this", "Age:" + params.getAge());
        Log.v("this", "Sex:" + params.getSex());
        Log.v("this", "ShoeSize:" + params.getShoeSize());
        Log.v("this", "State:" + params.getState());
        Log.v("this", "Birthday:" + params.getBirthday());
        Log.v("this", "Location:" + params.getLocation());
        Log.v("this", "ClothesData:" + params.getClothesData());
        Log.v("this", "OtherHealth:" + params.getOtherHealth());
        Log.v("this", "Note:" + params.getNote());

        MHttpManagerFactory.getAccountManager().saveCustomerUsage(WSZDataActivity.this,
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(WSZDataActivity.this, "保存成功");
                        Intent intent = new Intent(WSZDataActivity.this, JBRDataActivity.class);
                        intent.putExtra("consultId", params.getConsultId());
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

    private void setBirthdayTime() {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(WSZDataActivity.this);
        dialog.setShowHour(false);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {

            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                mTVBirthdayTime.setText(selectedDate);
            }
        });
    }
}
