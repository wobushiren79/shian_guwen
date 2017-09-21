package com.shian.shianlife.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignResultBean;
import com.shian.shianlife.mvp.userinfo.presenter.IUserInfoSignPresenter;
import com.shian.shianlife.mvp.userinfo.presenter.impl.UserInfoSignPresenterImpl;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoSignView;

/**
 * Created by Administrator on 2017/3/7.
 */

public class UserInfoIntegralSignView extends LinearLayout implements IUserInfoSignView {
    View view;
    ImageView mIVSign;
    TextView mTVSign;

    private IUserInfoSignPresenter userInfoSignPresenter;

    public UserInfoIntegralSignView(Context context) {
        this(context, null);
    }

    public UserInfoIntegralSignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_userinfo_integral_sign, this);

        initView();
        initData();
    }


    private void initView() {
        mIVSign = (ImageView) view.findViewById(R.id.iv_sign);
        mTVSign = (TextView) view.findViewById(R.id.tv_sign);

        view.setOnClickListener(onClickListener);
    }

    private void initData() {
        userInfoSignPresenter = new UserInfoSignPresenterImpl(this);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == view) {
                sign();
            }
        }
    };

    /**
     * 签到
     */
    private void sign() {
        userInfoSignPresenter.userInfoSign();

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void userInfoSignSuccess(UserInfoSignResultBean resultBean) {
        mIVSign.setImageResource(R.drawable.zhy_userinfo_integral_unsign);
        ScaleAnimation animation = new ScaleAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(1);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                mTVSign.setText("已签到");
            }
        });
        mTVSign.startAnimation(animation);
    }

    @Override
    public void userInfoSignFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }
}
