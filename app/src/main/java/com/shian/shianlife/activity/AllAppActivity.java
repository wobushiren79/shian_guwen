package com.shian.shianlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.AllAppAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.thisenum.APPEnum;
import com.shian.shianlife.view.ScrollGridView;
import com.shian.shianlife.view.customview.AppAdvertisementLayout;
import com.shian.shianlife.view.customview.MainAdvertisementLayout;

public class AllAppActivity extends BaseActivity {

    ScrollGridView mPlatformGridView;
    ScrollGridView mToolsGridView;
    ScrollGridView mOtherGridView;

    AppAdvertisementLayout appAdvertisementLayout;

    APPEnum[] platformData = {
            APPEnum.ZSPROJECT,
            APPEnum.CEMETERY,
            APPEnum.VRCEMETERY,
            APPEnum.BEFORECONTRACT
    };
    APPEnum[] toolsData = {
            APPEnum.NAVIGATION,
            APPEnum.CALENDAR,
            APPEnum.CALCULATOR,
            APPEnum.DIDI,
            APPEnum.COMMUNICATION
    };
    APPEnum[] otherData = {
            APPEnum.INTEGRALMALL
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_app);

        setTitle("全部应用");
        initView();
    }

    private void initView() {
        mPlatformGridView = (ScrollGridView) findViewById(R.id.platform_gridview);
        mToolsGridView = (ScrollGridView) findViewById(R.id.tools_gridview);
        mOtherGridView = (ScrollGridView) findViewById(R.id.other_gridview);

        appAdvertisementLayout = (AppAdvertisementLayout) findViewById(R.id.appadvertisementlayout);

        mPlatformGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, platformData));
        mToolsGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, toolsData));
        mOtherGridView.setAdapter(new AllAppAdapter(AllAppActivity.this, otherData));

        appAdvertisementLayout.setCallBack(new MainAdvertisementLayout.CallBack() {
            @Override
            public void loadingComplete() {
                appAdvertisementLayout.setVisibility(View.VISIBLE);
                TranslateAnimation animation = new TranslateAnimation
                        (BaseActivity.metrics.widthPixels, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
                animation.setDuration(500);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animation.cancel();
                        appAdvertisementLayout.clearAnimation();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                appAdvertisementLayout.startAnimation(animation);
            }

            @Override
            public void cancelPic() {

            }
        });
    }

}
