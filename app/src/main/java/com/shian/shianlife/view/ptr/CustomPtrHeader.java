package com.shian.shianlife.view.ptr;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.shian.shianlife.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by zm.
 */

public class CustomPtrHeader extends FrameLayout implements PtrUIHandler {
    private ImageView mIVLoadIcon;
    private ImageView mIVLoadRotateIcon;

    protected RotateAnimation mFlipAnimation;
    private int mRotateAniTime = 500;

    public CustomPtrHeader(@NonNull Context context) {
        super(context);
        initView();
    }

    public CustomPtrHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomPtrHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_ptr_header, this);
        mIVLoadIcon = (ImageView) header.findViewById(R.id.iv_load);
        mIVLoadRotateIcon = (ImageView) header.findViewById(R.id.iv_rotate);
        buildAnimation();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
//        Log.v("this", "onUIReset");

        mIVLoadRotateIcon.clearAnimation();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        Log.v("this", "onUIRefreshPrepare");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
//        Log.v("this", "onUIRefreshBegin");
        mIVLoadRotateIcon.startAnimation(mFlipAnimation);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
//        Log.v("this", "onUIRefreshComplete" + " isHeader:" + isHeader);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
//        Log.v("this", "onUIPositionChange" + " isUnderTouch:" + isUnderTouch + " byte:" + status + " mOffsetToRefresh:" + mOffsetToRefresh + " currentPos:" + currentPos + " lastPos:" + lastPos);

        if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            mIVLoadRotateIcon.clearAnimation();
            mIVLoadRotateIcon.setRotation(currentPos);
        }

    }

    protected void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, -360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setRepeatCount(Animation.INFINITE);
        mFlipAnimation.setFillAfter(true);
    }

}
