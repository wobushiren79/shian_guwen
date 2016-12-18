package com.shian.shianlife.activity.updata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerAgentmanParams;
import com.shian.shianlife.provide.result.HrConsultAgentman;


public class JBRDataActivity extends BaseActivity {

    EditText mETJBRName;
    EditText mETJBRPhone;
    EditText mETJBRRelation;
    EditText mETJBRCardId;
    EditText mETJBREmail;
    EditText mETRemark;

    Spinner mSPRelation;

    EditText mTVMapText;
    EditText mTVMapZSText;
    TextView mTVBack;
    TextView mTVNext;

    ImageView mIVMapSelect;
    ImageView mIVMapCheck;
    ImageView mIVMapZSSelect;
    ImageView mIVMapZSCheck;

    LinearLayout ZSLL;
    LinearLayout JBRLL;

    long consultId;
    private HpSaveCustomerAgentmanParams params = new HpSaveCustomerAgentmanParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbrdata);

        initView();
        initData();
    }

    private void initView() {
        mETJBRName = (EditText) findViewById(R.id.et_jbr_0);
        mETJBRPhone = (EditText) findViewById(R.id.et_jbr_1);
        mETJBRRelation = (EditText) findViewById(R.id.et_jbr_2);
        mETJBRCardId = (EditText) findViewById(R.id.et_jbr_cardid);
        mETJBREmail = (EditText) findViewById(R.id.et_jbr_email);
        mETRemark = (EditText) findViewById(R.id.et_remark);


        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVNext = (TextView) findViewById(R.id.tv_next);
        JBRLL = (LinearLayout) findViewById(R.id.jbr_ll);
        ZSLL = (LinearLayout) findViewById(R.id.zs_ll);

        mTVMapText = (EditText) JBRLL.findViewById(R.id.tv_map_text);
        mIVMapCheck = (ImageView) JBRLL.findViewById(R.id.iv_map);
        mIVMapSelect = (ImageView) JBRLL.findViewById(R.id.iv_data);

        mTVMapZSText = (EditText) ZSLL.findViewById(R.id.tv_map_text);
        mIVMapZSCheck = (ImageView) ZSLL.findViewById(R.id.iv_map);
        mIVMapZSSelect = (ImageView) ZSLL.findViewById(R.id.iv_data);

        mSPRelation = (Spinner) findViewById(R.id.sp_jbr_0);


        mTVBack.setOnClickListener(onClickListener);
        mTVNext.setOnClickListener(onClickListener);

        mIVMapCheck.setOnClickListener(onClickListener);
        mIVMapSelect.setOnClickListener(onClickListener);
        mIVMapZSCheck.setOnClickListener(onClickListener);
        mIVMapZSSelect.setOnClickListener(onClickListener);
    }

    int mapCheckNum = 0;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVBack) {
                //返回往生者信息
                toBack();
            } else if (view == mTVNext) {
                upData();
            } else if (view == mIVMapCheck || view == mIVMapZSCheck) {
                if (view == mIVMapCheck) {
                    mapCheckNum = 0;
                } else if (view == mIVMapZSCheck) {
                    mapCheckNum = 1;
                }
                setMapLocation();
            }
        }
    };


    private void setMapLocation() {
        //点击地图定位
        Intent intent = new Intent(JBRDataActivity.this, MapLocation.class);
        startActivityForResult(intent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1111:
                if (mapCheckNum == 0) {
                    String location = data.getStringExtra("location");
                    mTVMapText.setText(location);
                } else if (mapCheckNum == 1) {
                    String location = data.getStringExtra("location");
                    mTVMapZSText.setText(location);
                }


                break;
            default:
                break;
        }
    }


    private void toBack() {
        Intent intent = new Intent(JBRDataActivity.this, WSZDataActivity.class);
        intent.putExtra("consultId", consultId);
        startActivity(intent);
        finish();
    }

    private void upData() {
        if (mSPRelation.getSelectedItemPosition() == 0) {
            if (mETJBRRelation.getText().toString().equals("")) {
                params.setRelation("其他");
            } else {
                params.setRelation(mETJBRRelation.getText().toString());
            }
        }

        //生成合同信息
        String name = mETJBRName.getText().toString();
        String phone = mETJBRPhone.getText().toString();
        String location = mTVMapText.getText().toString();
        String zsLocation = mTVMapZSText.getText().toString();
        String cardid = mETJBRCardId.getText().toString();
        String email = mETJBREmail.getText().toString();
        String remark = mETRemark.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtils.show(JBRDataActivity.this, "经办人不能为空");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show(JBRDataActivity.this, "经办人电话不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(phone)) {
            ToastUtils.show(JBRDataActivity.this, "请输入正确的号码");
            return;
        }
        if (TextUtils.isEmpty(location)) {
            ToastUtils.show(JBRDataActivity.this, "经办人地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(zsLocation)) {
            ToastUtils.show(JBRDataActivity.this, "治丧地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(cardid)) {
            ToastUtils.show(JBRDataActivity.this, "经办人身份证号不能为空");
            return;
        }
        if (cardid.getBytes().length != 18) {
            ToastUtils.show(JBRDataActivity.this, "往生者身份证号码不足18位");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtils.show(JBRDataActivity.this, "经办人邮箱不能为空");
            return;
        }
        params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
        params.setName(name);
        params.setLinkInfo(phone);
        params.setLocation(location);
        params.setCardId(cardid);
        params.setEmail(email);
        params.setRemark(remark);
        params.setZsLocation(zsLocation);
        params.setBirthday(TransitionDate.StrToDate("2016-12-13", "yyyy-MM-dd").getTime());

        Log.v("this", "ConsultId:" + params.getConsultId());
        Log.v("this", "Name:" + params.getName());
        Log.v("this", "LinkInfo:" + params.getLinkInfo());
        Log.v("this", "Location:" + params.getLocation());
        Log.v("this", "zsLocation:" + params.getZsLocation());
        Log.v("this", "CardId:" + params.getCardId());
        Log.v("this", "Email:" + params.getEmail());
        Log.v("this", "Remark:" + params.getRemark());
        Log.v("this", "Relation:" + params.getRelation());


        MHttpManagerFactory.getAccountManager().saveCustomerAgentman(JBRDataActivity.this, params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(JBRDataActivity.this, "保存成功");
                        Intent intent = new Intent(JBRDataActivity.this, ContractDataActivity.class);
                        intent.putExtra("consultId", consultId);
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

    public void initData() {
        setTitle("经办人信息");
        consultId = getIntent().getLongExtra("consultId", 0);
        initSp1("其他");
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
        MHttpManagerFactory.getAccountManager().getCustomerAgentman(JBRDataActivity.this, params,
                new HttpResponseHandler<HrConsultAgentman>() {

                    @Override
                    public void onSuccess(HrConsultAgentman result) {
                        // TODO Auto-generated method stub
                        Log.v("this", "Name:" + result.getConsultAgentman().getName());
                        Log.v("this", "LinkInfo:" + result.getConsultAgentman().getLinkInfo());
                        Log.v("this", "Location:" + result.getConsultAgentman().getLocation());
                        Log.v("this", "CardId:" + result.getConsultAgentman().getCardId());
                        Log.v("this", "Email:" + result.getConsultAgentman().getEmail());
                        Log.v("this", "Remark:" + result.getConsultAgentman().getRemark());
                        Log.v("this", "Relation:" + result.getConsultAgentman().getRelation());
                        Log.v("this", "zsLocation" + result.getConsultAgentman().getZsLocation());

                        mETJBRName.setText(result.getConsultAgentman().getName());
                        mETJBRPhone.setText(result.getConsultAgentman().getLinkInfo());
                        mETJBRRelation.setText(result.getConsultAgentman().getRelation());
                        mETJBRCardId.setText(result.getConsultAgentman().getCardId());
                        mETJBREmail.setText(result.getConsultAgentman().getEmail());
                        mETRemark.setText(result.getConsultAgentman().getRemark());
                        mTVMapText.setText(result.getConsultAgentman().getLocation());
                        mTVMapZSText.setText(result.getConsultAgentman().getZsLocation());
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
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(JBRDataActivity.this, R.array.gx,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(R.array.gx);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPRelation.setAdapter(province_adapter);
        mSPRelation.setSelection(Utils.getArrayINdex(arrs, i));
        mSPRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                params.setRelation(arrs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
