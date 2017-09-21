package com.shian.shianlife.common.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by zm.
 */

public class AnimUtils {
    public static void startScaleToSelf(View view, int duration, float fromX, float toX, float fromY, float toY, Animation.AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation
                (fromX, toX, fromY, toY,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setAnimationListener(animationListener);
        view.startAnimation(scaleAnimation);
    }

    public static void startRotateToSelf(View view, int duration, int fromDegrees, int toDegrees, Animation.AnimationListener animationListener) {
        RotateAnimation rotateAnimation = new RotateAnimation
                (fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setAnimationListener(animationListener);
        view.startAnimation(rotateAnimation);
    }

    public static void setShoppingCartAnim(View view, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation
                (1, 1.5f, 1, 1.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(1);
        view.startAnimation(scaleAnimation);
    }

    public static void addShoppingCartAnim(View view, int duration, Animation.AnimationListener animationListener) {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation xAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 3f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
        xAnimation.setDuration(duration);

        TranslateAnimation yAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -3f);
        yAnimation.setDuration(duration / 2);
        yAnimation.setInterpolator(new LinearInterpolator());
        yAnimation.setRepeatMode(Animation.REVERSE);
        yAnimation.setRepeatCount(1);

        animationSet.addAnimation(xAnimation);
        animationSet.addAnimation(yAnimation);
        animationSet.setAnimationListener(animationListener);
        view.startAnimation(animationSet);
    }

    public static void showPriceAnim(View view, int duration, Animation.AnimationListener animationListener) {
//        ScaleAnimation scaleAnimation = new ScaleAnimation
//                (3f, 1f, 3f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        scaleAnimation.setDuration(duration);
//        scaleAnimation.setInterpolator(new BounceInterpolator());
//        if (animationListener != null)
//            scaleAnimation.setAnimationListener(animationListener);
//        view.startAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(duration);
        if (animationListener != null)
            alphaAnimation.setAnimationListener(animationListener);
        view.startAnimation(alphaAnimation);
    }


    public static void showPriceAnimShake(View view, int duration, Animation.AnimationListener animationListener) {
        AnimationSet animationSet = new AnimationSet(true);

        int rand = (int) (Math.random() * 2);
        int degrees;
        if (rand == 1) {
            degrees = 15;
        } else {
            degrees = -15;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0, degrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
        rotateAnimation.setDuration(duration);
        if (animationListener != null)
            rotateAnimation.setAnimationListener(animationListener);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());

        animationSet.addAnimation(rotateAnimation);
        view.startAnimation(animationSet);
    }

    public static void payPriceAnim(View view, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation
                (0, 1f, 0, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        view.setAnimation(scaleAnimation);
    }

    public static void payPriceAnimIcon(View view, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation
                (0f, 1f, 0f, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
//        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        view.setAnimation(scaleAnimation);
    }

    public static void payPriceAnimOther(View view, int duration, float dre) {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, dre);
        translateAnimation.setDuration(duration);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setRepeatCount(Animation.INFINITE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(duration);

        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        view.setAnimation(animationSet);
    }

    /**
     * 积分背景动画
     *
     * @param view
     */
    public static void integralBackAnim(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 100, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(6000);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setInterpolator(new OvershootInterpolator());

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }

    /**
     * 积分字体动画
     *
     * @param view
     * @param animationListener
     */
    public static void intagralTextAnim(TextView view, Animation.AnimationListener animationListener) {
        ScaleAnimation textScaleAnimation = new ScaleAnimation(0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        textScaleAnimation.setDuration(2000);
        textScaleAnimation.setInterpolator(new OvershootInterpolator());
        textScaleAnimation.setAnimationListener(animationListener);
        view.startAnimation(textScaleAnimation);

    }

    /**
     * 积分数字动画
     *
     * @param view
     * @param startPoint
     * @param endPoint
     */
    public static void intagralPointAnim(final TextView view, int startPoint, int endPoint) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startPoint, endPoint);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setText(animation.getAnimatedValue() + "");
            }
        });
        valueAnimator.setTarget(view);
        valueAnimator.start();
    }
}
