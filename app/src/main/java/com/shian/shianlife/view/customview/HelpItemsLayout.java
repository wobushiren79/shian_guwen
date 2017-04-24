package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.HotIssueListActivity;

/**
 * Created by Administrator on 2017/3/14.
 */

public class HelpItemsLayout extends LinearLayout {
    View view;
    ImageView mIVIcon;
    TextView mTVContent;

    int code;
    String title;

    public HelpItemsLayout(Context context) {
        this(context, null);
    }

    public HelpItemsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_mainapp_layout_items, this);
        initView();
    }

    private void initView() {
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVContent = (TextView) view.findViewById(R.id.tv_content);
        view.setOnClickListener(onClickListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == view) {
                TranslateAnimation animation = new TranslateAnimation
                        (Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF,
                                Animation.RELATIVE_TO_SELF, -getResources().getDimensionPixelOffset(R.dimen.dimen_10dp));
                animation.setDuration(200);
                animation.setInterpolator(new OvershootInterpolator());
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getContext(), HotIssueListActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("code", code);
                        getContext().startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                mIVIcon.startAnimation(animation);
            }
        }
    };

    public void setData(String title, int iconID, int code) {
        mIVIcon.setImageResource(iconID);
        mTVContent.setText(title);
        this.code = code;
        this.title = title;
    }
}
