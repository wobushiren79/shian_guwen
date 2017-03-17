package com.shian.shianlife.activity;

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
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.phpresult.PHPHrGetAdvertisement;
import com.shian.shianlife.provide.result.HrLoginResult;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity implements OnPushListener {
    private int sleepTime = 2500;//loading时间
    private int advertisementTime = 5000;//广告时间
    HrLoginResult result = null;

    Timer timerIntent;//定时跳转
    Button mBTJump;
    ImageView mIVAdvertising;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                advertisementSet();
            }
        }
    };


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_splash);
        init();
        initPush();
        initAdvertisement();
    }

    /**
     * 获取广告图
     */
    private void initAdvertisement() {
        RequestParams params = new RequestParams();
        params.put("type", 1);
        params.put("number", 1);
        params.put("pagerNumber", 0);
        MHttpManagerFactory.getPHPManager().getAdvertisement(SplashActivity.this, params, new HttpResponseHandler<PHPHrGetAdvertisement>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(final PHPHrGetAdvertisement result) {
                String picUrl = AppContansts.PhpURL + result.getItems().get(0).getBanner();
                ImageLoader.getInstance().displayImage(picUrl, mIVAdvertising);
                mIVAdvertising.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBTJump.setText("跳转");
                        jumpWeb(result);
                    }
                });
            }


            @Override
            public void onError(String message) {

            }
        });
    }


    private void init() {
        mBTJump = (Button) findViewById(R.id.bt_jump);
        mIVAdvertising = (ImageView) findViewById(R.id.iv_advertising);

        mBTJump.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTJump) {
                jumpActivity();
            }
        }
    };

    private void jumpActivity() {
        ShareLogin loginS = SharePerfrenceUtils.getLoginShare(this);
        if (loginS.isAutoLogin()) {
            jumpMain(result);
        } else {
            jumpLogin();
        }
    }

    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                    isOpenAdvertisement(true);
                    timerIntent = new Timer();
                    timerIntent.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            jumpLogin();
                        }
                    }, advertisementTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initView1(final HrLoginResult result) {
        this.result = result;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                    isOpenAdvertisement(true);
                    timerIntent = new Timer();
                    timerIntent.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            jumpMain(result);
                        }
                    }, advertisementTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 跳转主界面
     *
     * @param result
     */
    private void jumpMain(HrLoginResult result) {
        cancelTimer();
        Intent in = new Intent(SplashActivity.this, MainActivity.class);
        in.putExtra("loginData", JSONUtil.writeEntityToJSONString(result));
        startActivity(in);
        finish();
    }

    /**
     * 跳转登录界面
     */
    private void jumpLogin() {
        cancelTimer();
        Intent in = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(in);
        finish();
    }

    /**
     * 跳转网页界面
     *
     * @param result
     */
    private void jumpWeb(PHPHrGetAdvertisement result) {
        cancelTimer();
        Intent intent = new Intent(SplashActivity.this, WebActivity.class);
        intent.putExtra("url", result.getItems().get(0).getUrl());
        startActivity(intent);
    }

    /**
     * 关闭定时器
     */
    private void cancelTimer() {
        if (timerIntent != null) {
            timerIntent.cancel();
        }

    }

    private void initLogin(String channelId) {
        ShareLogin loginS = SharePerfrenceUtils.getLoginShare(this);
        if (loginS.isAutoLogin()) {
            if (loginS.getLoginType() == 0) {
                //登录状态为普通状态
                HpLoginParams params = new HpLoginParams();
                params.setPassword(loginS.getPassword());
                params.setUsername(loginS.getUsername());
                params.setSystemType("2");
                params.setChannelId(channelId);
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


                MHttpManagerFactory.getAccountManager().loginCemetery(this, params,
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
            initView();
        }
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
        initLogin(channelId);
    }


    /**
     * 是否开启广告
     *
     * @param isOpen
     */
    public void isOpenAdvertisement(boolean isOpen) throws InterruptedException {
        if (isOpen == false) {
            return;
        } else {
            handler.obtainMessage(0).sendToTarget();
        }
    }

    private void advertisementSet() {
        mBTJump.setVisibility(View.VISIBLE);
        mIVAdvertising.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
