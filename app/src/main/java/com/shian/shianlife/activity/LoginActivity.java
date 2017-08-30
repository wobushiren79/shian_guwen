package com.shian.shianlife.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Request;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.SharePerfrenceUtils.ShareLogin;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.presenter.IUserLoginPresenter;
import com.shian.shianlife.mvp.login.presenter.impl.UserLoginPresenterImpl;
import com.shian.shianlife.mvp.login.view.IUserLoginView;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.view.customview.LoadingButton;

public class LoginActivity extends BaseActivity implements IUserLoginView {
    @InjectView(R.id.et_login_username)
    EditText etUserName;
    @InjectView(R.id.et_login_password)
    EditText etUserPassword;
    @InjectView(R.id.cb_login_re)
    CheckBox cbRe;
    @InjectView(R.id.cb_login_auto)
    CheckBox cbAuto;

    @InjectView(R.id.btn_login)
    LoadingButton lbLogin;
    @InjectView(R.id.rl_content)
    RelativeLayout rlContent;


    private IUserLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_login);
        initData();
        startAnim();

    }

    private void initData() {
        userLoginPresenter = new UserLoginPresenterImpl(this, null);
        userLoginPresenter.getLoginConfig();
    }

    /**
     * 动画
     */
    private void startAnim() {
        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(1000);
        rlContent.setAnimation(translateAnimation);
        translateAnimation.start();
    }


    @OnClick(R.id.btn_login)
    void loginClick(View v) {
        lbLogin.setLoading();
        userLoginPresenter.loginSystem();
    }


    private void login(final String username, final String password) {
        AppContansts.userCemetery = null;
        AppContansts.userLoginInfo = null;
        if (!isCanLogin(username, password)) {
            return;
        }
        if (TextUtils.isEmpty(SharePerfrenceUtils.getShareChannelId(this))) {
            ToastUtils.show(this, "消息推送正在初始化，请稍后。。。");
            return;
        }

        //登录状态为普通类型
        final HpLoginParams params = new HpLoginParams();
        params.setPassword(etUserPassword.getText().toString());
        params.setUsername(etUserName.getText().toString());
        params.setSystemType("2");
        params.setChannelId(SharePerfrenceUtils.getShareChannelId(this));
        MHttpManagerFactory.getFuneralManager().login(this, params, new HttpResponseHandler<HrLoginResult>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(final HrLoginResult result) {
                AppContansts.userLoginInfo = result;
                //判断是否要进行公墓登陆
                if (result.getToken() != null && !result.getToken().equals("")) {
                    loginCemetery(result);
                } else {
                    loginSuccess();
                }
            }


            @Override
            public void onError(String message) {
                loginSuccess();
            }
        });


    }

    /**
     * 登陆公墓
     *
     * @param result
     */
    private void loginCemetery(final HrLoginResult result) {
        HpLoginParams paramsCemetery = new HpLoginParams();
        paramsCemetery.setToken(result.getToken());
        MHttpManagerFactory.getCemeteryManager().loginCemetery(LoginActivity.this, paramsCemetery, new HttpResponseHandler<HrLoginResult>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrLoginResult resultCemetery) {
                AppContansts.userCemetery = resultCemetery;
                loginSuccess();

            }

            @Override
            public void onError(String message) {
                loginSuccess();
            }
        });
    }

    /**
     * 登陆成功跳转
     *
     */
    private void loginSuccess() {
        lbLogin.setComplete();
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
        finish();
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
//        Intent in = new Intent(this, WebActivity.class);
//        in.putExtra("url", "http://m.e-funeral.cn");
//        startActivity(in);
        Intent in = new Intent(this, LoginPhoneActivity.class);
        startActivity(in);
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public void setUserName(String userName) {
        etUserName.setText(userName);
    }

    @Override
    public String getPassWord() {
        return etUserPassword.getText().toString();
    }

    @Override
    public void setPassWord(String passWord) {
        etUserPassword.setText(passWord);
    }

    @Override
    public boolean getIsAutoLogin() {
        return cbAuto.isChecked();
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        cbAuto.setChecked(isAutoLogin);
    }

    @Override
    public boolean getIsKeepAccount() {
        return cbRe.isChecked();
    }

    @Override
    public void setIsKeepAccount(boolean isKeepAccount) {
        cbRe.setChecked(isKeepAccount);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSystemSuccess(SystemLoginResultBean result) {
        userLoginPresenter.saveLoginConfig();
        login(etUserName.getText().toString(), etUserPassword.getText().toString());
    }

    @Override
    public void loginSystemFail(String message) {
        ToastUtils.show(this, message);
    }
}
