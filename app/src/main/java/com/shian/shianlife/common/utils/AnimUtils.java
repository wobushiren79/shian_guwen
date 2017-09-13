package com.shian.shianlife.common.utils;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


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
}
