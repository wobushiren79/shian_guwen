package com.shian.shianlife.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.kf5sdk.api.CallBack;
import com.kf5sdk.init.KF5SDKConfig;
import com.kf5sdk.init.UserInfo;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.BaseActivity.OnPushListener;
import com.shian.shianlife.common.push.Utils;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.SharePerfrenceUtils.ShareLogin;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.result.HrLoginResult;

public class SplashActivity extends BaseActivity implements OnPushListener {
    private static final String LOG_TAG = "SPLASH_ACTIVITY";
    RelativeLayout fl;


    @Override
    protected void onCreate(Bundle arg0) {
        if (LOGFLAG) {
            Log.v(LOG_TAG, "onCreate");
        }
        super.onCreate(arg0);
        initPush();

    }

    private void initView() {
        fl = new RelativeLayout(this);
        fl.setBackgroundResource(R.drawable.loading);
        final ImageView iv = new ImageView(this);
        LayoutParams lap = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        lap.addRule(RelativeLayout.CENTER_IN_PARENT);
        iv.setLayoutParams(lap);
//		iv.setBackgroundResource(R.drawable.ic_splash_logo1);
        fl.addView(iv);
        setContentView(fl);


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        }).start();
        iv.animate().alpha(0).setDuration(1500)
                .setListener(new AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//						iv.setBackgroundResource(R.drawable.ic_splash_logo2);
//						iv.animate().alpha(1).setDuration(1500).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }
                }).start();
    }

    private void initView1(final HrLoginResult result) {
//        RelativeLayout fl = new RelativeLayout(this);
//        fl.setBackgroundResource(R.drawable.zrjm);
//        fl.setBackgroundColor(getResources().getColor(R.color.app_bg));
//        final ImageView iv = new ImageView(this);
//        LayoutParams lap = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT);
//        lap.addRule(RelativeLayout.CENTER_IN_PARENT);
//        iv.setLayoutParams(lap);
//        iv.setBackgroundResource(R.drawable.ic_splash_logo1);

//        fl.addView(iv);
//        setContentView(fl);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Intent in = new Intent(SplashActivity.this, MainActivity.class);
                in.putExtra("loginData", JSONUtil.writeEntityToJSONString(result));
                startActivity(in);
                finish();
            }
        }).start();
//        iv.animate().alpha(0).setDuration(1000)
//                .setListener(new AnimatorListener() {
//
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
////						iv.setBackgroundResource(R.drawable.ic_splash_logo2);
////						iv.animate().alpha(1).setDuration(1500).start();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//                }).start();
    }

    private void initLogin(String channelId) {
        ShareLogin loginS = SharePerfrenceUtils.getLoginShare(this);
        if (loginS.isAutoLogin()) {
            if (LOGFLAG) {
                Log.v(LOG_TAG, "isAutoLogin");
            }

            if (loginS.getLoginType() == 0) {
                //登录状态为普通状态
                HpLoginParams params = new HpLoginParams();
                params.setPassword(loginS.getPassword());
                params.setUsername(loginS.getUsername());
                params.setSystemType("2");
                params.setChannelId(channelId);

                RelativeLayout fl = new RelativeLayout(this);
                fl.setBackgroundResource(R.drawable.loading);
                setContentView(fl);
                MHttpManagerFactory.getAccountManager().login(this, params,
                        new HttpResponseHandler<HrLoginResult>() {

                            @Override
                            public void onSuccess(HrLoginResult result) {

                                LoginActivity.cookie = result.getSessionId();
                                HttpRequestExecutor.setSession(LoginActivity.cookie, SplashActivity.this);
                                initView1(result);
                            }

                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onError(String message) {

                            }
                        });
            } else if (loginS.getLoginType() == 1) {
                //登录状态为公墓状态
                HpLoginParams params = new HpLoginParams();
                params.setPassword(loginS.getPassword());
                params.setUsername(loginS.getUsername());
                params.setSystemType("2");
                params.setChannelId(channelId);

                RelativeLayout fl = new RelativeLayout(this);
                fl.setBackgroundResource(R.drawable.loading);
                setContentView(fl);
                MHttpManagerFactory.getAccountManager().login(this, params,
                        new HttpResponseHandler<HrLoginResult>() {

                            @Override
                            public void onSuccess(HrLoginResult result) {
                                LoginActivity.cookie = result.getSessionId();
                                HttpRequestExecutor.setSession(LoginActivity.cookie, SplashActivity.this);
                                initView1(result);
                            }

                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onError(String message) {

                            }
                        });
            }


        } else {
            if (LOGFLAG) {
                Log.v(LOG_TAG, "noAutoLogin");
            }
            initView();
        }
    }

    private void initPush() {
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();
        PushManager.startWork(getApplicationContext(),

                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(this, "api_key"));
        Log.v("this", "api_key:" + Utils.getMetaValue(this, "api_key"));
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
        if (LOGFLAG) {
            Log.v(LOG_TAG, "channelId:" + channelId);
        }
        initLogin(channelId);
    }
}
