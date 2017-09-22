package com.shian.shianlife.activity.userinfo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.AnimUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralResultBean;
import com.shian.shianlife.mvp.userinfo.presenter.IUserInfoIntegralPresenter;
import com.shian.shianlife.mvp.userinfo.presenter.impl.UserInfoIntegralPresenterImpl;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoIntegralView;
import com.shian.shianlife.view.customview.UserInfoIntegralSignView;
import com.shian.shianlife.view.ptr.CustomPtrFramelayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class UserInfoIntegralActivity extends BaseActivity implements IUserInfoIntegralView, UserInfoIntegralSignView.CallBack {

    @InjectView(R.id.iv_integral_back)
    ImageView ivIntegralBack;
    @InjectView(R.id.tv_mypoint)
    TextView tvMypoint;
    @InjectView(R.id.tv_pointtitle)
    TextView tvPointtitle;
    @InjectView(R.id.signview)
    UserInfoIntegralSignView signView;
    @InjectView(R.id.rl_point)
    RelativeLayout rlPoint;
    @InjectView(R.id.ll_details)
    LinearLayout llDetails;
    @InjectView(R.id.ptr_layout)
    CustomPtrFramelayout ptrLayout;

    private IUserInfoIntegralPresenter userInfoIntegralPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_integral);

        initView();
        initData();

    }

    private void initView() {
        setTitle("积分");
        llDetails.setOnClickListener(onClickListener);
        ptrLayout.setPtrHandler(ptrDefaultHandler2);
        signView.setCallBack(this);
    }

    private void initData() {
        userInfoIntegralPresenter = new UserInfoIntegralPresenterImpl(this);
        userInfoIntegralPresenter.getUserInfoIntegral();
    }

    /**
     * 动画效果
     */
    private void startAnim(int startPoint, int endPoint) {
        AnimUtils.integralBackAnim(ivIntegralBack);
        AnimUtils.intagralTextAnim(tvPointtitle, null);
        AnimUtils.intagralTextAnim(tvMypoint, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signView.setVisibility(View.VISIBLE);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(1000);
                alphaAnimation.setInterpolator(new OvershootInterpolator());
                signView.startAnimation(alphaAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AnimUtils.intagralPointAnim(tvMypoint, startPoint, endPoint);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == llDetails) {
                Intent intent = new Intent(UserInfoIntegralActivity.this, UserInfoIntegralDetailsActivity.class);
                startActivity(intent);
            }
        }
    };

    PtrDefaultHandler2 ptrDefaultHandler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            ptrLayout.refreshComplete();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            setViewStatus(View.GONE);
            signView.setVisibility(View.GONE);
            userInfoIntegralPresenter.getUserInfoIntegral();
        }
    };

    /**
     * 设置控件状态
     *
     * @param gone
     */
    private void setViewStatus(int gone) {
        ivIntegralBack.setVisibility(gone);
        tvMypoint.setVisibility(gone);
        tvPointtitle.setVisibility(gone);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void getUserInfoIntegralSuccess(UserInfoIntegralResultBean resultBean) {
        setViewStatus(View.VISIBLE);
        ptrLayout.refreshComplete();
        if (resultBean.getCanCheckin() != null) {
            if (resultBean.getCanCheckin())
                signView.setSignStatus(false);
            else
                signView.setSignStatus(true);
        }


    }

    @Override
    public void getUserInfoIntegralFail(String msg) {
        ptrLayout.refreshComplete();
        ToastUtils.show(this, msg);
    }

    @Override
    public void setUserInfoIntegral(Integer integral) {
        startAnim(0, integral);
    }

    @Override
    public void signSuccess(View view) {
        ptrLayout.autoRefresh();
    }
}
