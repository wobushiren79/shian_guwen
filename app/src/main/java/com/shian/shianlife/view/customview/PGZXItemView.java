package com.shian.shianlife.view.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/25.
 */

public class PGZXItemView extends LinearLayout {
    View view;

    TextView mTVLineLeft;
    TextView mTVLineRight;
    TextView mTVContent;
    ImageView mIVIcon;

    public PGZXItemView(Context context) {
        super(context);
    }

    public PGZXItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_pgzx_item_layout, this);
        initView();
    }

    private void initView() {
        mTVLineLeft = (TextView) view.findViewById(R.id.tv_line_left);
        mTVLineRight = (TextView) view.findViewById(R.id.tv_line_right);
        mTVContent = (TextView) view.findViewById(R.id.tv_content);
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);

    }

    /**
     * 设置显示类型
     *
     * @param type 1、不能填写   2.可以填写 3.完成填写。 4状态显示
     */
    public void setType(int type) {
        switch (type) {
            case 1:
                mTVLineLeft.setBackgroundResource(R.color.zhy_line_6);
                mTVLineRight.setBackgroundResource(R.color.zhy_line_6);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_3);
                break;
            case 2:
                mTVLineLeft.setBackgroundResource(R.color.zhy_text_color_1);
                mTVLineRight.setBackgroundResource(R.color.zhy_line_6);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_1);
                break;
            case 3:
                mTVLineLeft.setBackgroundResource(R.color.zhy_text_color_1);
                mTVLineRight.setBackgroundResource(R.color.zhy_text_color_1);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_2);
                break;
            case 4:
                mTVLineLeft.setBackgroundResource(R.color.zhy_line_6);
                mTVLineRight.setBackgroundResource(R.color.zhy_line_6);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_3);
                break;
        }
    }
}
