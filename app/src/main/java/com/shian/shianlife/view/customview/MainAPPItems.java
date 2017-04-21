package com.shian.shianlife.view.customview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.RouteNode;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.AllAppActivity;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.activity.map.NewMapLineActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;

import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MainAPPItems extends LinearLayout {
    View view;
    ImageView mIVIcon;
    TextView mTVContent;

    String url;

    public MainAPPItems(Context context) {
        this(context, null);
    }

    public MainAPPItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_mainapp_layout_items, this);
        initView();
    }


    private void initView() {
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVContent = (TextView) view.findViewById(R.id.tv_content);
        view.setOnClickListener(onClickListener);
    }

    public void setData(String content, int iconID, String url) {
        mIVIcon.setImageResource(iconID);
        mTVContent.setText(content);
        this.url = url;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == view) {
                TranslateAnimation animation = new TranslateAnimation
                        (Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF,
                                Animation.RELATIVE_TO_SELF, -getResources().getDimensionPixelOffset(R.dimen.dimen_10dp));
                animation.setDuration(200);
                animation.setInterpolator(new OvershootInterpolator());
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (url.equals("")) {

                        } else if (url.contains("all")) {
                            Intent intent = new Intent(getContext(), AllAppActivity.class);
                            getContext().startActivity(intent);
                        } else if (url.contains("http")) {
                            if (url.contains("diditaxi")) {
                                openWeb(1);
                            } else {
                                openWeb(0);
                            }
                        } else if (url.contains("navigation")) {
                            navigationFunction();
                        } else if (url.contains("calendar")) {
                            openCalendar();
                        } else if (url.contains("calculator")) {
                            openJS();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                mIVIcon.startAnimation(animation);
            }
        }
    };


    /**
     * 打开计算机
     */
    public void openJS() {
        PackageInfo pak = getAllApps(getContext(), "Calculator", "calculator"); //大小写
        if (pak != null) {
            Intent intent = new Intent();
            intent = getContext().getPackageManager().getLaunchIntentForPackage(pak.packageName);
            getContext().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "未找到计算器", Toast.LENGTH_SHORT).show();
        }
    }

    public PackageInfo getAllApps(Context context, String app_flag_1, String app_flag_2) {
        PackageManager pManager = context.getPackageManager();
        // 获取手机内所有应用
        List<PackageInfo> packlist = pManager.getInstalledPackages(0);
        for (int i = 0; i < packlist.size(); i++) {
            PackageInfo pak = (PackageInfo) packlist.get(i);
            if (pak.packageName.contains(app_flag_1) || pak.packageName.contains(app_flag_2)) {
                return pak;
            }
        }
        return null;
    }


    /**
     * 打开日历
     */
    private void openCalendar() {
//        Intent i = new Intent();..
//        ComponentName cn = null;
//        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
//            cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
//        } else {
//            cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
//        }
//        i.setComponent(cn);
//        getContext().startActivity(i);
        Intent intent=new Intent(getContext(),WebActivity.class);
        intent.putExtra("url","http://m.wannianli.tianqi.com");
        getContext().startActivity(intent);
    }


    /**
     * 网页连接功能
     *
     * @param type 0为默认连接，1为滴滴
     */
    private void openWeb(int type) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        if (type == 0) {
            intent.putExtra("url", url);
        } else if (type == 1) {
            intent.putExtra("url", url
                    + "/?channel=" + AppContansts.DiDichannel
                    + "&maptype=soso" +//wgs baidu soso
                    "&lat=" + AppContansts.LOCAL_latitude
                    + "&lng=" + AppContansts.LOCAL_longitude
            );
        }
        getContext().startActivity(intent);
    }


    /**
     * 导航功能
     */
    private void navigationFunction() {

//        Intent intent = new Intent();
//        if (Utils.isInstalled(getContext(), "com.baidu.BaiduMap")) {
//            try {
//                intent = Intent.parseUri("intent://map/direction?" +
////                            "origin=latlng:" + startLatLng.getLocation().latitude + "," + startLatLng.getLocation().longitude +
////                            "|name:" + AppContansts.LOCAL_ADDRESS +
////                            "&destination=latlng:" + endLatLng.getLocation().latitude + "," + endLatLng.getLocation().longitude +
////                            "|name:" + endPointStr +
//                        "mode=driving" +
//                        "&src=Name|AppName" +
//                        "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end", 0);
//            } catch (URISyntaxException e) {
//                Utils.LogVPrint("URISyntaxException : " + e.getMessage());
//                e.printStackTrace();
//            }
//            getContext().startActivity(intent);
//        } else if (Utils.isInstalled(getContext(), "com.autonavi.minimap")) {
//            intent.setData(Uri
//                    .parse("androidamap://route?" +
//                            "sourceApplication=softname" +
////                                "&slat=" + startLatLng.getLocation().latitude +
////                                "&slon=" + startLatLng.getLocation().longitude +
////                                "&dlat=" + endLatLng.getLocation().latitude +
////                                "&dlon=" +endLatLng.getLocation().longitude+
////                                "&dname=" + endPointStr +
//                            "dev=0" +
//                            "&m=0" +
//                            "&t=2"));
//            getContext().startActivity(intent);
//        } else {
//            ToastUtils.showLongTime(getContext(), "请先下载百度地图或高德地图");
//        }
        Intent intent=new Intent(getContext(),WebActivity.class);
        intent.putExtra("url","http://m.amap.com/");
        getContext().startActivity(intent);

    }
}
