package com.shian.shianlife.activity;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.Request;

import com.baidu.android.pushservice.BasicPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.push.Utils;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;

import java.util.List;

public class SettingsActivity extends BaseActivity {
    private SharedPreferences share;
    @InjectViews({R.id.rb1, R.id.rb2})
    List<RadioButton> rbList;
    //    @InjectView(R.id.st_switch)
//    SwitchButton switchButton;
    @InjectView(R.id.tv_switch)
    TextView tvSwitch;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.activity_settings);
        setTitle("设置");
        share = getSharedPreferences("settings", -1);
//        int state = 1;
//        if (AppContansts.userInfoData != null) {
//            state = AppContansts.userInfoData.getAppStatus();
//        }
//        if (state == 1) {
//            switchButton.setChecked(true);
//            rbList.get(0).setChecked(true);
//            tvSwitch.setText("当前状态为空闲");
//        } else {
//            switchButton.setChecked(false);
//            rbList.get(1).setChecked(true);
//            tvSwitch.setText("当前状态为忙碌");
//        }
//
//        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if (isChecked) {
//                    tvSwitch.setText("当前状态为空闲");
//                    setSwitchState(isChecked);
//                } else {
//                    tvSwitch.setText("当前状态为忙碌");
//                    setSwitchState(isChecked);
//                }
//            }
//        });
    }

//    private void setSwitchState(final boolean isCheck) {
//        HpConsultIdParams params = new HpConsultIdParams();
//        if (isCheck) {
//            params.setAppStatus(1);
//        } else {
//            params.setAppStatus(2);
//        }
//        MHttpManagerFactory.getFuneralManager().changeInfo(SettingsActivity.this, params, new HttpResponseHandler<Object>() {
//
//            @Override
//            public void onStart(Request request, int id) {
//                switchButton.setEnabled(false);
//            }
//
//            @Override
//            public void onSuccess(Object result) {
//                switchButton.setEnabled(true);
//                SharedPreferences.Editor editor = share.edit();
//                editor.putBoolean("rb", isCheck);
//                editor.commit();
//            }
//
//            @Override
//            public void onError(String message) {
//                switchButton.setEnabled(true);
//            }
//        });
//    }

    @OnClick(R.id.tv_editorder)
    void logout(View v) {
        TipsDialog mDialog = new TipsDialog(this);
        mDialog.setTitle("是否退出当前账户");
        mDialog.setBottomButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                MHttpManagerFactory.getFuneralManager().loginout(getBaseContext(), new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {
                        PushManager.startWork(getApplicationContext(),
                                PushConstants.LOGIN_TYPE_API_KEY,
                                Utils.getMetaValue(SettingsActivity.this, "api_key"));
                        // TODO Auto-generated method stub

                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
                MHttpManagerFactory.getCemeteryManager().loginoutCemetery(SettingsActivity.this, new HttpResponseHandler<Object>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(Object result) {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
                SharePerfrenceUtils.setShareAutoLogin(getApplicationContext(), false);
                com.shian.shianlife.common.utils.Utils.jumpLogin(SettingsActivity.this);
            }
        });
        mDialog.setTopButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mDialog.show();
    }


    @OnClick(R.id.ll_change)
    void changeState(View v) {
        TipsDialog mDialog = new TipsDialog(this);
        mDialog.setTitle("是否切换账号状态");
        mDialog.setTopButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MHttpManagerFactory.getFuneralManager().loginout(
                        getBaseContext(), new HttpResponseHandler<Object>() {

                            @Override
                            public void onStart(Request request, int id) {

                            }

                            @Override
                            public void onSuccess(Object result) {
                                // TODO Auto-generated method stub
                                SharePerfrenceUtils.setShareAutoLogin(
                                        getBaseContext(), false);
                                PushManager.startWork(getApplicationContext(),
                                        PushConstants.LOGIN_TYPE_API_KEY,
                                        Utils.getMetaValue(SettingsActivity.this, "api_key"));
                                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                                intent.putExtra("Settings", 1);
                                setResult(1010, intent);
                                finish();
                            }

                            @Override
                            public void onError(String message) {
                                // TODO Auto-generated method stub
                            }
                        });
            }
        });
        mDialog.setBottomButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mDialog.show();
    }

    @OnClick(R.id.ll_clean)
    void clean(View v) {
        ImageLoader.getInstance().clearDiskCache();
        ToastUtils.show(this, "清除成功");
    }

    @OnCheckedChanged(R.id.rb1)
    void checkLisener(boolean isC) {
        if (!isC) return;
        HpConsultIdParams params = new HpConsultIdParams();

        params.setAppStatus(1);

        MHttpManagerFactory.getFuneralManager().changeInfo(this, params, new HttpResponseHandler<Object>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                SharedPreferences.Editor editor = share.edit();
                editor.putBoolean("rb", true);
                editor.commit();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @OnCheckedChanged(R.id.rb2)
    void checkLisener0(boolean isC) {
        if (!isC) return;
        HpConsultIdParams params = new HpConsultIdParams();

        params.setAppStatus(2);
        MHttpManagerFactory.getFuneralManager().changeInfo(this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                SharedPreferences.Editor editor = share.edit();
                editor.putBoolean("rb", false);
                editor.commit();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @OnCheckedChanged({R.id.swt1, R.id.swt2})
    void checkLisener1(boolean isC) {
        BasicPushNotificationBuilder cBuilder = new BasicPushNotificationBuilder();
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
    }

    @OnCheckedChanged(R.id.swt2)
    void checkLisener2(boolean isC) {
        BasicPushNotificationBuilder cBuilder = new BasicPushNotificationBuilder();

//            cBuilder.setNotificationSound("android.resource://" + getPackageName() + "/" +R.raw.mm);
    }

}
