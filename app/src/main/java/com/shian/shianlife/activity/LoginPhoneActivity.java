package com.shian.shianlife.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.view.customview.LoadingButton;

public class LoginPhoneActivity extends BaseActivity {

    RelativeLayout mRLContent;
    EditText mETUserName;
    EditText mETPassWord;
    Button mBTPhoneCode;
    LoadingButton mLBSubmit;
    RadioButton mRBState1;
    RadioButton mRBState2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);
        mETUserName = (EditText) findViewById(R.id.et_login_username);
        mETPassWord = (EditText) findViewById(R.id.et_login_password);
        mBTPhoneCode = (Button) findViewById(R.id.bt_phonecode);
        mLBSubmit = (LoadingButton) findViewById(R.id.btn_login);

        mRBState1 = (RadioButton) findViewById(R.id.rb_state1);
        mRBState2 = (RadioButton) findViewById(R.id.rb_state2);

        mBTPhoneCode.setOnClickListener(onClickListener);
        mLBSubmit.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLBSubmit) {
                ToastUtils.show(LoginPhoneActivity.this,"该功能还未开放");
            } else if (v == mBTPhoneCode) {
                ToastUtils.show(LoginPhoneActivity.this,"该功能还未开放");
            }
        }
    };
}
