package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.customview.MainAPP;
import com.shian.shianlife.view.customview.MainAdvertisementLayout;
import com.shian.shianlife.view.customview.MainDynamic;
import com.shian.shianlife.view.customview.UserInfoLayout;

/**
 * Created by Administrator
 */

public class NewHomeFragment extends BaseFragment {
    View view;

    UserInfoLayout mUserInfoLayout;//用户资料布局
    MainAdvertisementLayout mMainAdvertisementLayout;//主页广告布局
    MainDynamic mMainDynamicLayout;//重要动态
    MainAPP mMainAPP;//我的APP

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newhome, null, false);
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        mUserInfoLayout = (UserInfoLayout) view.findViewById(R.id.userinfo_layout);
        mMainAdvertisementLayout = (MainAdvertisementLayout) view.findViewById(R.id.mainadvertisement_layout);
        mMainDynamicLayout = (MainDynamic) view.findViewById(R.id.maindynamic_layout);
        mMainAPP = (MainAPP) view.findViewById(R.id.mainapp_layout);

        mMainAdvertisementLayout.setCallBack(advertisermentLayoutCallBack);
        mMainDynamicLayout.setCallBack(mainDynamicCallBack);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    /**
     * 广告加载完毕与取消动画
     */
    MainAdvertisementLayout.CallBack advertisermentLayoutCallBack = new MainAdvertisementLayout.CallBack() {
        @Override
        public void loadingComplete() {
            mMainAdvertisementLayout.setVisibility(View.VISIBLE);
            TranslateAnimation animation = new TranslateAnimation(BaseActivity.metrics.widthPixels, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
            animation.setDuration(500);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation.cancel();
                    mMainAdvertisementLayout.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mMainAdvertisementLayout.startAnimation(animation);
        }

        @Override
        public void cancelPic() {
            AlphaAnimation animation = new AlphaAnimation(1, 0);
            animation.setDuration(500);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation.cancel();
                    mMainAdvertisementLayout.clearAnimation();
                    mMainAdvertisementLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mMainAdvertisementLayout.startAnimation(animation);
        }
    };


    MainDynamic.CallBack mainDynamicCallBack = new MainDynamic.CallBack() {
        @Override
        public void loadingComplete() {
            mMainDynamicLayout.setVisibility(View.VISIBLE);
        }
    };
}
