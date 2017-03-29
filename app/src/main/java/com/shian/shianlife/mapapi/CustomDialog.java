package com.shian.shianlife.mapapi;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 */

public class CustomDialog extends Dialog {
    Context context;
    LinearLayout dialogLayout;

    private int pointNum;//圆球数量
    private int pointSize;//圆球大小
    private int pointSpace;//圆球间距
    List<ImageView> pointList = new ArrayList<>();

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.context = context;
//        imageView = new ImageView(context);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, 200);
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        imageView.setLayoutParams(layoutParams);
//        imageView.setImageResource(R.drawable.custom_dialog);
        initData();
        super.setContentView(initView());

//        this.setCanceledOnTouchOutside(false);
//        Window dialogWindow = this.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        dialogWindow.setGravity(Gravity.CENTER);
//        lp.width = 200; // 宽度
//        lp.height = 200; // 高度
//        dialogWindow.setAttributes(lp);

    }

    private void initData() {
        pointNum = 4;
        pointSize = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_40dp);
        pointSpace = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_8dp);
    }

    private LinearLayout initView() {
        //整体布局设置
        dialogLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams dialogParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogLayout.setLayoutParams(dialogParams);

        //圆球设置
        LinearLayout.LayoutParams pointLayout = new LinearLayout.LayoutParams(pointSize, pointSize);
        pointLayout.rightMargin = pointSpace;
        pointLayout.leftMargin = pointSpace;
        for (int i = 0; i < pointNum; i++) {
            ImageView point = new ImageView(getContext());
            point.setLayoutParams(pointLayout);
            if (i % 2 == 0) {
                point.setImageResource(R.drawable.zhy_dialog_point_1);
            } else {
                point.setImageResource(R.drawable.zhy_dialog_point_2);
            }
            pointList.add(point);
            dialogLayout.addView(point);
        }
        return dialogLayout;
    }


    @Override
    public void show() {
        super.show();
        for (int i = 0; i < pointList.size(); i++) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1f, 0.0f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(600);
            scaleAnimation.setRepeatCount(1);
            scaleAnimation.setInterpolator(new LinearInterpolator());
            scaleAnimation.setStartOffset((i + 1) * 150);
            final int finalI = i;
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.0f, 1f, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(600);
                    scaleAnimation.setRepeatCount(Animation.INFINITE);
                    scaleAnimation.setInterpolator(new LinearInterpolator());
                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                    pointList.get(finalI).startAnimation(scaleAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            pointList.get(i).startAnimation(scaleAnimation);

        }
    }

    @Override
    public void cancel() {
//        if (scaleAnimation != null) {
//            scaleAnimation.cancel();
//        }
        super.cancel();
    }
}
