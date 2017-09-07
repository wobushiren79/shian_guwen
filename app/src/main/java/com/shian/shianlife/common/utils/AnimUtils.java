package com.shian.shianlife.common.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
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

    public static void addShoppingCartAnim(View view, int duration) {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation xAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 3f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
        xAnimation.setDuration(duration);

        TranslateAnimation yAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -3f);
        yAnimation.setDuration(duration/2);
        yAnimation.setInterpolator(new LinearInterpolator());
        yAnimation.setRepeatMode(Animation.REVERSE);
        yAnimation.setRepeatCount(1);

        animationSet.addAnimation(xAnimation);
        animationSet.addAnimation(yAnimation);
        view.startAnimation(animationSet);
    }
}
