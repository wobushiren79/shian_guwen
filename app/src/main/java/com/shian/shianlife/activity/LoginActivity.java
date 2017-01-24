package com.shian.shianlife.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import butterknife.InjectView;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.SharePerfrenceUtils.ShareLogin;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.result.HrLoginResult;

public class LoginActivity extends BaseActivity {
    private final String LOG_TAG = "LOGIN_ACTIVITY";

    @InjectView(R.id.et_login_username)
    EditText etUserName;
    @InjectView(R.id.et_login_password)
    EditText etUserPassword;
    @InjectView(R.id.cb_login_re)
    CheckBox cbRe;
    @InjectView(R.id.cb_login_auto)
    CheckBox cbAuto;
    @InjectView(R.id.rb_state1)
    RadioButton rbState1;
    @InjectView(R.id.rb_state2)
    RadioButton rbState2;

    ShareLogin loginS;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_login);
        initView();
        changeState();
    }

    //切换账号处理
    private void changeState() {
        Log.v("this","changeState");
        int isChange= getIntent().getIntExtra("loginStateChange",-1);
        Log.v("this","ischange:"+isChange);
        if(isChange==1){
            Log.v("this","changeState0");
            int loginType=loginS.getLoginType();
            if(loginType==0){
                rbState2.setChecked(true);
            }else if(loginType==1){
                rbState1.setChecked(true);
            }
            String username = etUserName.getText().toString();
            String password = etUserPassword.getText().toString();
            login(username, password);
        }
    }

    private void initView() {
        loginS = SharePerfrenceUtils.getLoginShare(this);
        etUserName.setText(loginS.getUsername());
        if (loginS.isRemeberPassword()) {
            cbRe.setChecked(true);
            etUserPassword.setText(loginS.getPassword());
            if (loginS.getLoginType() == 0) {
                rbState1.setChecked(true);
            } else if (loginS.getLoginType() == 1) {
                rbState2.setChecked(true);
            }

        }

        if (loginS.isAutoLogin()) {
            cbAuto.setChecked(true);
            if (loginS.getLoginType() == 0) {
                rbState1.setChecked(true);
            } else if (loginS.getLoginType() == 1) {
                rbState2.setChecked(true);
            }
            login(loginS.getUsername(), loginS.getPassword());
        }

    }

    @OnClick(R.id.btn_login)
    void loginClick(View v) {
        String username = etUserName.getText().toString();
        String password = etUserPassword.getText().toString();
        login(username, password);
    }

    public static String cookie;

    private void login(final String username, final String password) {
        if (!isCanLogin(username, password)) {
            return;
        }
        if (TextUtils.isEmpty(SharePerfrenceUtils.getShareChannelId(this))) {
            ToastUtils.show(this, "消息推送正在初始化，请稍后。。。");
            return;
        }
        if (rbState1.isChecked()) {
            //登录状态为普通类型
            HpLoginParams params = new HpLoginParams();
            params.setPassword(etUserPassword.getText().toString());
            params.setUsername(etUserName.getText().toString());
            params.setSystemType("2");
            params.setChannelId(SharePerfrenceUtils.getShareChannelId(this));
            Log.v("this", SharePerfrenceUtils.getShareChannelId(this));
            MHttpManagerFactory.getAccountManager().login(this, params, new HttpResponseHandler<HrLoginResult>() {

                @Override
                public void onSuccess(HrLoginResult result) {
                    cookie = result.getSessionId();
                    HttpRequestExecutor.setSession(cookie, LoginActivity.this);
                    SharePerfrenceUtils.setLoginShare(LoginActivity.this, username, password, cbRe.isChecked(),
                            cbAuto.isChecked(), 0);
                    ToastUtils.show(getBaseContext(), "登陆成功");
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    String resultBack = JSONUtil.writeEntityToJSONString(result);
                    if (LOGFLAG) {
                        Log.v(LOG_TAG, "resultBack:" + resultBack);
                    }
                    in.putExtra("loginData", resultBack);
                    startActivity(in);
                    finish();
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onError(String message) {
                }
            });
        }
        if (rbState2.isChecked()) {
            //登录状态为公墓类型
            HpLoginParams params = new HpLoginParams();
            params.setPassword(etUserPassword.getText().toString());
            params.setUsername(etUserName.getText().toString());
            params.setSystemType("2");
            params.setChannelId(SharePerfrenceUtils.getShareChannelId(this));
            Log.v("this", SharePerfrenceUtils.getShareChannelId(this));
            MHttpManagerFactory.getAccountManager().loginCemetery(this, params, new HttpResponseHandler<HrLoginResult>() {

                @Override
                public void onSuccess(HrLoginResult result) {
                    cookie = result.getSessionId();
                    HttpRequestExecutor.setSession(cookie, LoginActivity.this);
                    SharePerfrenceUtils.setLoginShare(LoginActivity.this, username, password, cbRe.isChecked(),
                            cbAuto.isChecked(), 1);
                    ToastUtils.show(getBaseContext(), "登陆成功");
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    String resultBack = JSONUtil.writeEntityToJSONString(result);
                    if (LOGFLAG) {
                        Log.v(LOG_TAG, "resultBack:" + resultBack);
                    }
                    in.putExtra("loginData", resultBack);
                    startActivity(in);
                    finish();
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onError(String message) {
                }
            });
        }


    }

    private boolean isCanLogin(String username, String password) {

        if (username == null || username.equals("") || password == null || password.equals("")) {
            ToastUtils.show(getBaseContext(), "用户名或密码不能为空");
        } else {
            return true;
        }
        return false;
    }

    @OnClick(R.id.btn_login_web)
    void loginWeb(View v) {
        Intent in = new Intent(this, WebActivity.class);
        in.putExtra("url", "http://m.e-funeral.cn");
        startActivity(in);
    }
}
