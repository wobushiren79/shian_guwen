package com.shian.shianlife.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.kf5sdk.api.CallBack;
import com.kf5sdk.init.KF5SDKConfig;
import com.kf5sdk.init.UserInfo;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.local.LocationService;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.fragment.FindFragment;
import com.shian.shianlife.fragment.HomeFragment;
import com.shian.shianlife.fragment.NewHomeFragment;
import com.shian.shianlife.fragment.NewUserCenterFragment;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.fragment.UserCenterFragment;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.service.PushService;
import com.shian.shianlife.service.UpDataService;
import com.viewpagerindicator.TabPageIndicator;

import org.support.v4.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback, OrderFragment.OrderFragmentCallBack {

    int loginType;//0殡仪 1.公墓
    @InjectView(R.id.fl_main)
    View flMain;
    @InjectView(R.id.rb_main_1)
    RadioButton rbMain1;
    @InjectView(R.id.rb_main_2)
    RadioButton rbMain2;
    @InjectView(R.id.rb_main_3)
    RadioButton rbMain3;
    @InjectView(R.id.rb_main_4)
    RadioButton rbMain4;
    @InjectView(R.id.tv_msgnum)
    TextView tvMsgNumber;


    private FragmentManager mFragmentManager;
    private FragmentTransaction transcation;
    //    private HomeFragment homeFragment;
    private NewHomeFragment homeFragment;
    private FindFragment findFragment;
    private OrderFragment orderFragment;
    //    private UserCenterFragment userFragment;
    private NewUserCenterFragment userFragment;
    private CemeteryFragment cemeteryFragment;//新增公墓服务界面

    List<RadioButton> listRB = new ArrayList<>();
    //定位初始化
//    public LocationClient mLocationClient = null;
//    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        initDate();
        initView();
        initIM();
        initPermission();
//        initLocation();
        startPushService();
        //检测更新
        Utils.checkUpData(this, false);
    }


    private void startPushService() {
        Intent intent = new Intent(MainActivity.this, PushService.class);
        startService(intent);
    }


    @TargetApi(23)
    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1002);
            } else {

            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1002) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }

    private void initIM() {
        String name = SharePerfrenceUtils.getLoginShare(this).getUsername();
        UserInfo userInfo = new UserInfo();
        userInfo.name = name;
        byte[] ips = name.getBytes();
        StringBuffer sb = new StringBuffer();
        sb.append("18030");
        for (byte b : ips) {
            int i = b;
            sb.append("" + i);
        }
        sb.append("00000000000000");//防止账号过短
        userInfo.sdkName = "来自世安工单";
        userInfo.phone = sb.toString().substring(0, 11);
        userInfo.helpAddress = "wenshikai.kf5.com";
        userInfo.email = name + "@sina.com";
        userInfo.deviceToken = name;
        userInfo.appId = "001577de0e0afa0ab1ff154110f0fc731dcee382d3b6b8b8";
        KF5SDKConfig.INSTANCE.init(this, userInfo, new CallBack() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onFailure(String result) {
            }
        });
    }

    private void initDate() {
        loginType = SharePerfrenceUtils.getLoginShare(this).getLoginType();
        mFragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        setTitle("title");
        rbMain1.setOnCheckedChangeListener(new RBCheckListener());
        rbMain2.setOnCheckedChangeListener(new RBCheckListener());
        rbMain3.setOnCheckedChangeListener(new RBCheckListener());
        rbMain4.setOnCheckedChangeListener(new RBCheckListener());
        showFragment(R.id.rb_main_1);
        MHttpManagerFactory.getAccountManager().getMessageCount(this,
                new HttpResponseHandler<HrCommentResult>() {
                    @Override
                    public void onSuccess(HrCommentResult result) {
                        // TODO Auto-generated method stub
                        AppContansts.MessageCount = result.getCount();
                        if (result.getCount() == 0) {
                            setMessageIconVisible(View.GONE);
                        } else {
                            setMessageIconVisible(View.VISIBLE);
                        }
                        if (loginType == 0) {
                            getMsgNumber();
                        }
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
        initRB();
    }

    /**
     * 设置rb的大小
     */
    private void initRB() {
        listRB.add(rbMain1);
        listRB.add(rbMain2);
        listRB.add(rbMain3);
        listRB.add(rbMain4);
        for (RadioButton rb : listRB) {
            Rect rect = new Rect();
            rect.set(0, 0, getResources().getDimensionPixelOffset(R.dimen.dimen_48dp), getResources().getDimensionPixelOffset(R.dimen.dimen_48dp)); // 这里分别是 left top right bottom  代表距离父view 的距离   长宽 是  right-left   bottom-top
            //注意 xml没有设置 drawableTop 的图片话  drawableT 为null 的情况
            Drawable drawableT = rb.getCompoundDrawables()[1]; // getCompoundDrawables()得到一个数组  0 1 2 3 对应 left top right bottom 方向的drawable
            drawableT.setBounds(rect);// 大小和位置控制
            rb.setCompoundDrawables(null, drawableT, null, null); // 设置drawable    对应 left top right bottom 方向的drawable
        }
    }

    private void showFragment(int state) {
        transcation = mFragmentManager.beginTransaction();
        switch (state) {
            case R.id.rb_main_1:
                if (homeFragment == null) {
                    homeFragment = new NewHomeFragment();
                }
                transcation.replace(R.id.fl_main, homeFragment);
                break;
            case R.id.rb_main_2:
                if (loginType == 0) {
                    //普通账号
                    if (orderFragment == null) {
                        orderFragment = new OrderFragment();
                        orderFragment.setCallBack(this);
                    }
                    transcation.replace(R.id.fl_main, orderFragment);
                } else if (loginType == 1) {
                    //公墓账号
                    if (cemeteryFragment == null) {
                        cemeteryFragment = new CemeteryFragment();
                    }
                    transcation.replace(R.id.fl_main, cemeteryFragment);
                }

                break;
            case R.id.rb_main_3:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                }
                transcation.replace(R.id.fl_main, findFragment);
                break;
            case R.id.rb_main_4:
                if (userFragment == null) {
                    userFragment = new NewUserCenterFragment();
                }
                transcation.replace(R.id.fl_main, userFragment);
                break;

            default:
                break;
        }
        transcation.commit();
    }

    @Override
    public void changeMsgNum() {
        getMsgNumber();
    }

    class RBCheckListener implements OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            if (isChecked) {
                showFragment(buttonView.getId());
            }
        }

    }

    private long currentTime;
    private int count;

    @Override
    public void onBackPressed() {
        if (count == 0) {
            currentTime = System.currentTimeMillis();
            count = 1;
            ToastUtils.show(this, "再按一次返回桌面");
        } else if (count == 1) {
            if (Math.abs(System.currentTimeMillis() - currentTime) < 2000) {
                exitApp();
                count = 0;
            } else {
                currentTime = System.currentTimeMillis();
                count = 1;
                ToastUtils.show(this, "再按一次返回桌面");
            }
        }
    }

    private void exitApp() {
        finish();
        MHttpManagerFactory.getAccountManager().loginout(this,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1010) {
            int state = data.getIntExtra("Settings", -1);
            Intent in = new Intent(this, LoginActivity.class);
            in.putExtra("loginStateChange", state);
            startActivity(in);
            finish();
        }
    }

    private LocationService locationService;

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub

        super.onStop();
    }


    @Override
    protected void onDestroy() {
        locationService.unregisterListener(mListener); // 注销掉监听
        locationService.stop(); // 停止定位服务
//        mLocationClient.stop();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        locationService = ((SaBaseApplication) getApplication()).locationService;
        // 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        // 注册监听
        int type = getIntent().getIntExtra("from", 0);
//		if (type == 0) {
        locationService.setLocationOption(locationService
                .getDefaultLocationClientOption());
//		} else if (type == 1) {
//			locationService.setLocationOption(locationService.getOption());
//		}
        locationService.start();// 定位SDK
        // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request

    }
//    private void initLocation() {
//        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);    //注册监听函数
//
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
//        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 1000;
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
//        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
//
//    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub

            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                if (location.getAddrStr() == null) return;
                location(location.getAddrStr());
                AppContansts.LocalString = location.getAddrStr();
                AppContansts.LOCAL_PROVINCE = location.getAddress().province;
                AppContansts.LOCAL_CITY = location.getAddress().city;
                AppContansts.LOCAL_COUNTY = location.getAddress().district;
                AppContansts.LOCAL_STREET = location.getAddress().street;
                AppContansts.LOCAL_STREETNUM = location.getAddress().streetNumber;
                AppContansts.LOCAL_ADDRESS = location.getAddress().address;
                AppContansts.LOCAL_latitude = location.getLatitude();
                AppContansts.LOCAL_longitude = location.getLongitude();
                setTitleLocation(AppContansts.LOCAL_STREET + AppContansts.LOCAL_STREETNUM);
            }
        }

    };

    private void location(final String curAddress) {
        if (!curAddress.equals(AppContansts.LocalString)) {
            HpConsultIdParams params = new HpConsultIdParams();
            params.setCurAddress(curAddress);
            MHttpManagerFactory.getAccountManager().changeCurAddress(this, params, new HttpResponseHandler<Object>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onError(String message) {

                }

                @Override
                public void onSuccess(Object result) {
                    Log.i("tag", "changeInfo" + curAddress);
                }
            });
        }
    }


    /**
     * 获取未处理订单消息
     */
    public void getMsgNumber() {
        MHttpManagerFactory.getAccountManager().getMsgNumberForUntreated(MainActivity.this, new HttpResponseHandler<HrGetMsgNumberForUntreated>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetMsgNumberForUntreated result) {
                AppContansts.MsgNumber = result;
                AppContansts.MsgNumberTotal = result.getAssignment()
                        + result.getAuditing()
                        + result.getService()
                        + result.getTalk()
                        + result.getUnpaid();

                if (AppContansts.MsgNumberTotal > 99) {
                    tvMsgNumber.setVisibility(View.VISIBLE);
                    tvMsgNumber.setText("99");
                } else if (AppContansts.MsgNumberTotal == 0) {
                    tvMsgNumber.setVisibility(View.GONE);
                } else {
                    tvMsgNumber.setVisibility(View.VISIBLE);
                    tvMsgNumber.setText(AppContansts.MsgNumberTotal + "");
                }
                ShortcutBadger.applyCount(MainActivity.this, AppContansts.MsgNumberTotal);
                if (orderFragment != null) {
                    List<TabPageIndicator.TabView> listTabView = orderFragment.indicator.getListTabView();
                    int cornerSize = MainActivity.this.getResources().getDimensionPixelSize(R.dimen.dimen_30dp);
                    for (TabPageIndicator.TabView tabview : listTabView) {
                        String titel = tabview.getText().toString();
                        switch (titel) {
                            case "洽谈":
                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getTalk(), cornerSize);
                                break;
                            case "待服务":
                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getService(), cornerSize);
                                break;
                            case "服务派单中":
                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getAssignment(), cornerSize);
                                break;
                            case "待评审":
                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getAuditing(), cornerSize);
                                break;
                            case "待收款":
                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getUnpaid(), cornerSize);
                                break;
                            case "服务结束":
//                                tabview.setMsgCornerNumber(AppContansts.MsgNumber.getEndService());
                                break;
                        }
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
