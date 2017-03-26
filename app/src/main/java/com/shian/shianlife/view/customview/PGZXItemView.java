package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.sendorders.SendOrderActivity;
import com.shian.shianlife.thisenum.PGZXItemEnum;

/**
 * Created by Administrator on 2017/3/25.
 */

public class PGZXItemView extends LinearLayout {
    View view;

    TextView mTVLineLeft;
    TextView mTVLineRight;
    TextView mTVContent;
    ImageView mIVIcon;

    long orderId;
    long consultId;

    String name;

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
        RelativeLayout.LayoutParams iconSmallLayout = new RelativeLayout.LayoutParams
                (getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_10dp), getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_10dp));
        RelativeLayout.LayoutParams iconBigLayout = new RelativeLayout.LayoutParams
                (getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_26dp), getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_26dp));
        iconSmallLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
        iconBigLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
        switch (type) {
            case 1:
                mTVLineLeft.setBackgroundResource(R.color.zhy_line_6);
                mTVLineRight.setBackgroundResource(R.color.zhy_line_6);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_3);
                mTVContent.setBackgroundResource(R.drawable.zhy_pgzx_state_3);
                mTVContent.setTextColor(getResources().getColor(R.color.zhy_line_6));
                mIVIcon.setLayoutParams(iconSmallLayout);
                mTVContent.setOnClickListener(null);
                break;
            case 2:
                mTVLineLeft.setBackgroundResource(R.color.zhy_text_color_1);
                mTVLineRight.setBackgroundResource(R.color.zhy_line_6);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_1);
                mTVContent.setBackgroundResource(R.drawable.zhy_pgzx_state_1);
                mTVContent.setTextColor(getResources().getColor(R.color.white));
                mIVIcon.setLayoutParams(iconBigLayout);
                mTVContent.setOnClickListener(onClickListener);
                break;
            case 3:
                mTVLineLeft.setBackgroundResource(R.color.zhy_text_color_1);
                mTVLineRight.setBackgroundResource(R.color.zhy_text_color_1);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_2);
                mTVContent.setBackgroundResource(R.drawable.zhy_pgzx_state_4);
                mTVContent.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
                mIVIcon.setLayoutParams(iconSmallLayout);
                mTVContent.setOnClickListener(onClickListener);
                break;
            case 4:
                mTVLineLeft.setBackgroundResource(R.color.zhy_text_color_1);
                mTVLineRight.setBackgroundResource(R.color.zhy_text_color_1);
                mIVIcon.setImageResource(R.drawable.zhy_pgzx_icon_2);
                mTVContent.setBackgroundResource(R.drawable.zhy_pgzx_state_2);
                mTVContent.setTextColor(getResources().getColor(R.color.black_item_shua_textcolor));
                mIVIcon.setLayoutParams(iconSmallLayout);
                mTVContent.setOnClickListener(null);
                break;
        }
    }

    public void setName(String name) {
        this.name = name;
        mTVContent.setText(name);
    }

    public void setID(long orderId, long consultId) {
        this.consultId = consultId;
        this.orderId = orderId;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVContent) {
                Intent intent = new Intent(getContext(), SendOrderActivity.class);
                intent.putExtra("consultId", consultId);
                intent.putExtra("orderId", orderId);
                intent.putExtra("TitleName",name);
                int step = 0;
                if (name.equals(PGZXItemEnum.DayOne1.getName())) {
                    step = 0;
                } else if (name.equals(PGZXItemEnum.DayOne2.getName())) {
                    step = 1;
                } else if (name.equals(PGZXItemEnum.DayOne3.getName())) {
                    step = 2;
                } else if (name.equals(PGZXItemEnum.DayTwo1.getName())) {
                    step = 2;
                } else if (name.equals(PGZXItemEnum.DayTwo2.getName())) {
                    step = 3;
                } else if (name.equals(PGZXItemEnum.DayTwo3.getName())) {
                    step = 4;
                } else if (name.equals(PGZXItemEnum.DayThree1.getName())) {
                    step = 4;
                } else if (name.equals(PGZXItemEnum.DayThree2.getName())) {
                    step = 5;
                } else if (name.equals(PGZXItemEnum.DayThree3.getName())) {
                    step = 6;
                }
                intent.putExtra("step", step);
                getContext().startActivity(intent);
            }
        }
    };
}
