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

/**
 * Created by Administrator on 2017/3/7.
 */

public class UserInfoIntegralSignView extends LinearLayout {
    View view;
    ImageView mIVSign;
    TextView mTVSign;

    public UserInfoIntegralSignView(Context context) {
        this(context, null);
    }

    public UserInfoIntegralSignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_userinfo_integral_sign, this);

        initView();
    }

    private void initView() {
        mIVSign = (ImageView) view.findViewById(R.id.iv_sign);
        mTVSign = (TextView) view.findViewById(R.id.tv_sign);

        view.setOnClickListener(onClickListener);
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
}
