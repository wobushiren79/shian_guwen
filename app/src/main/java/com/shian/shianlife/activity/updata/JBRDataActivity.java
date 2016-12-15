package com.shian.shianlife.activity.updata;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerAgentmanParams;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.Date;

import butterknife.OnClick;

public class JBRDataActivity extends BaseActivity {

    EditText mETJBRName;
    EditText mETJBRPhone;
    EditText mETJBRRelation;
    EditText mETJBRCardId;
    EditText mETJBREmail;
    EditText mETRemark;

    Spinner mSPRelation;

    TextView mTVMapText;
    TextView mTVBack;
    TextView mTVNext;

    ImageView mIVMapSelect;
    ImageView mIVMapCheck;

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

        mTVMapText = (TextView) findViewById(R.id.tv_map_text);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVNext = (TextView) findViewById(R.id.tv_next);

        mIVMapCheck = (ImageView) findViewById(R.id.iv_map);
        mIVMapSelect = (ImageView) findViewById(R.id.iv_data);

        mSPRelation = (Spinner) findViewById(R.id.sp_jbr_0);

        setTitle("经办人信息");
        mTVBack.setOnClickListener(onClickListener);
        mTVNext.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVBack) {
                //返回往生者信息
            } else if (view == mTVNext) {
                //生成合同信息
                String name = mETJBRName.getText().toString();
                String phone = mETJBRPhone.getText().toString();
                String cardid = mETJBRCardId.getText().toString();
                String email = mETJBREmail.getText().toString();
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
                params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
                params.setLinkInfo(phone);
                params.setName(name);
                MHttpManagerFactory.getAccountManager().saveCustomerAgentman(JBRDataActivity.this, params,
                        new HttpResponseHandler<Object>() {

                            @Override
                            public void onSuccess(Object result) {
                                // TODO Auto-generated method stub
                                ToastUtils.show(JBRDataActivity.this, "保存成功");
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
    };

    public void initData() {
        initSp1("其他");
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
        MHttpManagerFactory.getAccountManager().getCustomerAgentman(JBRDataActivity.this, params,
                new HttpResponseHandler<HrConsultAgentman>() {

                    @Override
                    public void onSuccess(HrConsultAgentman result) {
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
