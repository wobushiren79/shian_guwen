package com.shian.shianlife.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/3.
 */

public class LoadingButton extends LinearLayout {
    View view;
    ImageView mIVLoading;
    ImageView mIVComplete;
    TextView mTVContent;
    RotateAnimation rotateAnimation;

    public LoadingButton(Context context) {
        this(context, null);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_loading_button, this);
        initView();
        setNormal();
    }

    private void initView() {
        mTVContent = (TextView) view.findViewById(R.id.tv_content);
        mIVComplete = (ImageView) view.findViewById(R.id.iv_complete);
        mIVLoading = (ImageView) view.findViewById(R.id.iv_loading);

    }

    public void setComplete() {
        endAnim(mIVLoading);
        mIVLoading.setVisibility(GONE);
        mTVContent.setVisibility(GONE);
        mIVComplete.setVisibility(VISIBLE);

    }

    public void setLoading() {
        mIVLoading.setVisibility(VISIBLE);
        mIVComplete.setVisibility(GONE);
        mTVContent.setVisibility(GONE);
        startAnim(mIVLoading);
    }

    public void setNormal() {
        endAnim(mIVLoading);
        mIVLoading.setVisibility(GONE);
        mIVComplete.setVisibility(GONE);
        mTVContent.setVisibility(VISIBLE);

    }

    public void startAnim(View view) {
        rotateAnimation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(rotateAnimation);
    }

    public void endAnim(View view) {
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
            view.clearAnimation();
        }
    }
}
