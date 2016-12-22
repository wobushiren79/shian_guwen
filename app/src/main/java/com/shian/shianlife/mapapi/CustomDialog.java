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
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CustomDialog extends Dialog {
    Context context;
    ImageView imageView;
    RotateAnimation rotateAnimation;

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.context = context;
        imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, 200);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.custom_dialog);
        super.setContentView(imageView);

        this.setCanceledOnTouchOutside(false);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = 200; // 宽度
        lp.height = 200; // 高度
        dialogWindow.setAttributes(lp);

    }


    @Override
    public void show() {
        super.show();
        rotateAnimation = new RotateAnimation(0, 360, 100, 100);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        imageView.setAnimation(rotateAnimation);
    }

    @Override
    public void cancel() {
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
        super.cancel();
    }
}
