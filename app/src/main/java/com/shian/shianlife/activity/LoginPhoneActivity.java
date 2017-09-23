package com.shian.shianlife.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;
import com.shian.shianlife.mvp.userinfo.presenter.IChangePassWordSMSPresenter;
import com.shian.shianlife.mvp.userinfo.presenter.impl.ChangePassWordSMSPresenterImpl;
import com.shian.shianlife.mvp.userinfo.view.IChangePassWordSMSView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

public class LoginPhoneActivity extends BaseActivity implements IChangePassWordSMSView, View.OnClickListener {

    @InjectView(R.id.et_login_phone)
    EditText etLoginPhone;
    @InjectView(R.id.et_login_smscode)
    EditText etLoginSmsCode;
    @InjectView(R.id.tv_phone_code)
    TextView tvPhoneCode;
    @InjectView(R.id.et_login_password)
    EditText etLoginPassWord;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;
    @InjectView(R.id.rl_content)
    RelativeLayout rlContent;
    @InjectView(R.id.ll_submit)
    LinearLayout llSubmit;

    private Timer timerIntent;//定时

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvPhoneCode.setText("再次获取（" + msg.obj + "）");
            } else if (msg.what == 1) {
                setLayoutStatus(false);
            }
            super.handleMessage(msg);
        }
    };
    private IChangePassWordSMSPresenter changePassWordSMSPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        initView();
        initData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        llSubmit.setVisibility(View.GONE);
        tvPhoneCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    private void initData() {
        changePassWordSMSPresenter = new ChangePassWordSMSPresenterImpl(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void getSMSCodeSuccess(ChangePassWordSMSResultBean result) {
        llSubmit.setVisibility(View.VISIBLE);
        setLayoutStatus(true);
        startThread();
    }

    @Override
    public void getSMSCodeFail(String msg) {
        ToastUtils.show(this, msg);
        setLayoutStatus(false);
    }

    @Override
    public void changePassWordSMSSuccess(ChangePassWordSMSResultBean result) {
        ToastUtils.show(this, "修改密码成功");
        finish();
    }

    @Override
    public void changePassWordSMSFail(String msg) {
        setLayoutStatus(false);
        ToastUtils.show(this, msg);
    }

    @Override
    public String getUserPhone() {
        return etLoginPhone.getText().toString();
    }

    @Override
    public String getNewPassWord() {
        return etLoginPassWord.getText().toString();
    }

    @Override
    public String getMsgCode() {
        return etLoginSmsCode.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            subSMSCode();
        } else if (v == tvPhoneCode) {
            getSMSCode();
        }
    }

    /**
     * 提交数据
     */
    private void subSMSCode() {
        if (timerIntent != null)
            timerIntent.cancel();
        changePassWordSMSPresenter.changePassWordSMS();
    }

    /**
     * 获取数据
     */
    private void getSMSCode() {
        tvPhoneCode.setOnClickListener(null);
        changePassWordSMSPresenter.getSMSCode();
    }

    private void startThread() {
        if (timerIntent != null)
            timerIntent.cancel();
        timerIntent = new Timer();
        timerIntent.schedule(new TimerTask() {
            int time = 180;

            @Override
            public void run() {
                if (time < 0) {
                    timerIntent.cancel();
                    handler.obtainMessage(1).sendToTarget();
                } else {
                    handler.obtainMessage(0, time).sendToTarget();
                }
                time--;
            }
        }, 0, 1000);
    }


    /**
     * 設置倒計時字體
     *
     * @param isTiming
     */
    public void setLayoutStatus(boolean isTiming) {
        if (isTiming) {
            tvPhoneCode.setTextColor(getResources().getColor(R.color.zhy_text_color_2));
            tvPhoneCode.setBackgroundResource(R.drawable.zhy_button_bg_uncheck_7);
            tvPhoneCode.setOnClickListener(null);
            llSubmit.setVisibility(View.VISIBLE);
        } else {
            tvPhoneCode.setText("获取验证码");
            tvPhoneCode.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            tvPhoneCode.setBackgroundResource(R.drawable.zhy_button_state_2);
            tvPhoneCode.setOnClickListener(this);
            llSubmit.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (timerIntent != null) {
            timerIntent.cancel();
        }
        super.onDestroy();
    }
}
