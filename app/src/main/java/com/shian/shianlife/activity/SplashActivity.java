package com.shian.shianlife.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.BaseActivity.OnPushListener;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.push.Utils;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.SharePerfrenceUtils.ShareLogin;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.presenter.IUserLoginPresenter;
import com.shian.shianlife.mvp.login.presenter.impl.UserLoginPresenterImpl;
import com.shian.shianlife.mvp.login.view.IUserLoginView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.phpresult.PHPHrGetAdvertisement;
import com.shian.shianlife.provide.result.HrLoginResult;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Request;

public class SplashActivity extends BaseActivity implements OnPushListener, IUserLoginView {
    private int SLEEPTIME = 2500;//loading时间
    Timer timerIntent;//定时跳转

    private IUserLoginPresenter userLoginPresenter;
    private String userName;
    private String userPassWord;
    private Boolean isAuto;
    private Boolean isRe;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_splash);
        initPush();
    }

    /**
     * 休眠2秒
     */
    private void sleepActivity(final int type) {
        timerIntent = new Timer();
        timerIntent.schedule(new TimerTask() {
            @Override
            public void run() {
                jumpActivity(type);
            }
        }, SLEEPTIME);
    }


    private void initData(String channelId) {
        AppContansts.pushChannelId = channelId;
        userLoginPresenter = new UserLoginPresenterImpl(this, null);
        userLoginPresenter.getLoginConfig();
    }


    /**
     * 跳转界面
     */
    private void jumpActivity(int type) {
        Intent intent = new Intent(SplashActivity.this, LoginAdvertActivity.class);
        intent.putExtra("advert", type);
        startActivity(intent);
        finish();
    }

    private void login(String userName, String passWord, String channelId) {
        AppContansts.userCemetery = null;
        AppContansts.userLoginInfo = null;

        //登录状态为普通状态
        HpLoginParams params = new HpLoginParams();
        params.setPassword(passWord);
        params.setUsername(userName);
        params.setSystemType("2");
        params.setChannelId(channelId);
        MHttpManagerFactory.getFuneralManager().login(this, params,
                new HttpResponseHandler<HrLoginResult>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrLoginResult result) {
                        AppContansts.userLoginInfo = result;
                        if (result.getToken() != null && !result.getToken().equals("")) {
                            loginCemetery(result);
                        } else {
                            sleepActivity(LoginAdvertActivity.MAIN);
                        }
                    }

                    @Override
                    public void onError(String message) {
                        sleepActivity(LoginAdvertActivity.MAIN);
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
        MHttpManagerFactory.getCemeteryManager().loginCemetery(SplashActivity.this, paramsCemetery, new HttpResponseHandler<HrLoginResult>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrLoginResult resultCemetery) {
                AppContansts.userCemetery = resultCemetery;
                sleepActivity(LoginAdvertActivity.MAIN);
            }

            @Override
            public void onError(String message) {
                sleepActivity(LoginAdvertActivity.MAIN);
            }
        });
    }


    private void initPush() {
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(this, "api_key"));
        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(getApplicationContext());
        // Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
//                resource.getIdentifier(
//                        "notification_custom_builder", "layout", pkgName),
//                resource.getIdentifier("notification_icon", "id", pkgName),
//                resource.getIdentifier("notification_title", "id", pkgName),
//                resource.getIdentifier("notification_text", "id", pkgName));
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
//        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
//        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
//        cBuilder.setLayoutDrawable(resource.getIdentifier(
//                "simple_notification_icon", "drawable", pkgName));
//        cBuilder.setNotificationSound(Uri.withAppendedPath(
//                Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
//        // 推送高级设置，通知栏样式设置为下面的ID
//        PushManager.setNotificationBuilder(this, 1, cBuilder);

        setOnPushListener(this);
    }

    @Override
    public void onPush(String channelId) {
        // TODO Auto-generated method stub
        initData(channelId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerIntent != null)
            timerIntent.cancel();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassWord() {
        return userPassWord;
    }

    @Override
    public void setPassWord(String passWord) {
        this.userPassWord = passWord;
    }

    @Override
    public boolean getIsAutoLogin() {
        return isAuto;
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        this.isAuto = isAutoLogin;
        if (isAuto) {
            userLoginPresenter.loginSystem();
        } else {
            sleepActivity(LoginAdvertActivity.LOGIN);
        }
    }

    @Override
    public boolean getIsKeepAccount() {
        return isRe;
    }

    @Override
    public void setIsKeepAccount(boolean isKeepAccount) {
        this.isRe = isKeepAccount;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSystemSuccess(SystemLoginResultBean result) {
        login(userName, userPassWord, AppContansts.pushChannelId);
    }

    @Override
    public void loginSystemFail(String message) {
        sleepActivity(LoginAdvertActivity.LOGIN);
    }
}
