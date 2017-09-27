package com.shian.shianlife.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.presenter.IUserLoginPresenter;
import com.shian.shianlife.mvp.login.presenter.impl.UserLoginPresenterImpl;
import com.shian.shianlife.mvp.login.view.IUserLoginView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.view.customview.LoadingButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Request;

public class LoginActivity extends BaseActivity implements IUserLoginView, View.OnClickListener {
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

    @InjectView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @InjectView(R.id.tv_no_password)
    TextView tvNoPassword;


    private IUserLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initData();
        startAnim();
        //检测更新
        Utils.checkUpData(this, false);
    }

    private void initData() {
        tvNoPassword.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);

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


    private void loginFuneral() {
        AppContansts.userCemetery = null;
        AppContansts.userLoginInfo = null;

        String userName = etUserName.getText().toString();
        String passWord = etUserPassword.getText().toString();

        if (!isCanLogin(userName, passWord)) {
            return;
        }
        if (TextUtils.isEmpty(SharePerfrenceUtils.getShareChannelId(this))) {
            ToastUtils.show(this, "消息推送正在初始化，请稍后。。。");
            return;
        }

        //登录状态为普通类型
        final HpLoginParams params = new HpLoginParams();
        params.setUsername(userName);
        params.setPassword(passWord);
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
                loginCemetery();
            }


            @Override
            public void onError(String message) {
                loginCemetery();
            }
        });


    }

    /**
     * 登陆公墓
     */
    private void loginCemetery() {
        HpLoginParams params = new HpLoginParams();
        params.setPassword(etUserPassword.getText().toString());
        params.setUsername(etUserName.getText().toString());
        MHttpManagerFactory.getCemeteryManager().loginCemetery(LoginActivity.this, params, new HttpResponseHandler<HrLoginResult>() {


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
        loginFuneral();
    }

    @Override
    public void loginSystemFail(String message) {
        lbLogin.setNormal();
        ToastUtils.show(this, message);
    }

    @Override
    public void onClick(View v) {
        if (v == tvForgetPassword) {
            forgetPassWord();
        } else if (v == tvNoPassword) {
            noPassWord();
        }
    }

    /**
     * 忘记密码
     */
    private void forgetPassWord() {
        Intent intent = new Intent(this, LoginPhoneActivity.class);
        startActivity(intent);
    }

    /**
     * 没有密码
     */
    private void noPassWord() {
        Intent intent = new Intent(this, PicShowActivity.class);
        ArrayList<String> listData = new ArrayList<>();
        listData.add(AppContansts.Cooperation_Pic_1);
        listData.add(AppContansts.Cooperation_Pic_2);
        listData.add(AppContansts.Cooperation_Pic_3);
        intent.putExtra(IntentName.INTENT_LIST_DATA, listData);
        intent.putExtra(IntentName.INTENT_DATA, "招商");
        startActivity(intent);
    }
}
