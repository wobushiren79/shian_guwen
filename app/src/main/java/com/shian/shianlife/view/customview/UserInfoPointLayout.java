package com.shian.shianlife.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/5.
 */

public class UserInfoPointLayout extends LinearLayout {
    View view;
    ImageView mIVIcon;
    TextView mTVName;
    TextView mTVPoint;

    public UserInfoPointLayout(Context context) {
        super(context);
    }

    public UserInfoPointLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_userinfo_point_layout, this);
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVName = (TextView) view.findViewById(R.id.tv_name);
        mTVPoint = (TextView) view.findViewById(R.id.tv_point);
    }

    public void initLayout(int iconID, String name, String point) {
        mIVIcon.setImageResource(iconID);
        mTVName.setText(name);
        mTVPoint.setText(point);
    }

    public void setPoint(String point) {
        mTVPoint.setText(point);
    }
}
