package com.shian.shianlife.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MainAPPItems extends LinearLayout {
    View view;
    ImageView mIVIcon;
    TextView mTVContent;

    public MainAPPItems(Context context) {
        this(context, null);
    }

    public MainAPPItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_mainapp_layout_items, this);
        initView();
    }


    private void initView() {
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVContent = (TextView) view.findViewById(R.id.tv_content);

        view.setOnClickListener(onClickListener);
    }

    public void setData(String content, int iconID) {
        mIVIcon.setImageResource(iconID);
        mTVContent.setText(content);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == view) {
                TranslateAnimation animation = new TranslateAnimation
                        (Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF,
                                Animation.RELATIVE_TO_SELF,-getResources().getDimensionPixelOffset(R.dimen.dimen_10dp));
                animation.setDuration(200);
                animation.setInterpolator(new OvershootInterpolator());
                mIVIcon.startAnimation(animation);
            }
        }
    };
}
