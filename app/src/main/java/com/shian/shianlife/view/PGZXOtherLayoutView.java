package com.shian.shianlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.thisenum.PGZXItemEnum;
import com.shian.shianlife.view.customview.PGZXItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class PGZXOtherLayoutView extends LinearLayout {
    View view;

    PGZXItemView mPGZXItem1;
    PGZXItemView mPGZXItem2;
    PGZXItemView mPGZXItem3;

    TextView mTVTitle;

    long orderId;
    long consultId;
    int day;


    public PGZXOtherLayoutView(Context context) {
        this(context, null);
    }

    public PGZXOtherLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_otherpgzx_view, this);
        initView();
    }

    private void initView() {
        mPGZXItem1 = (PGZXItemView) view.findViewById(R.id.pgzx_item_1);
        mPGZXItem2 = (PGZXItemView) view.findViewById(R.id.pgzx_item_2);
        mPGZXItem3 = (PGZXItemView) view.findViewById(R.id.pgzx_item_3);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    /**
     * 1、不能填写   2.可以填写 3.完成填写。 4状态显示
     *
     * @param state1
     * @param state2
     * @param state3
     */
    public void setState(int state1, int state2, int state3) {
        mPGZXItem1.setType(state1);
        mPGZXItem2.setType(state2);
        mPGZXItem3.setType(state3);
    }

    /**
     * 设置名字
     *
     * @param name
     */
    public void setTitle(String name) {
        mTVTitle.setText(name);
    }

    public void setID(long orderId, long consultId, int day) {
        this.consultId = consultId;
        this.orderId = orderId;
        this.day = day;
        if (day == 1) {
            mPGZXItem1.setName(PGZXItemEnum.DayOne1.getName());
            mPGZXItem2.setName(PGZXItemEnum.DayOne2.getName());
            mPGZXItem3.setName(PGZXItemEnum.DayOne3.getName());
            mTVTitle.setText("治丧初期");
        } else if (day == 2) {
            mPGZXItem1.setName(PGZXItemEnum.DayTwo1.getName());
            mPGZXItem2.setName(PGZXItemEnum.DayTwo2.getName());
            mPGZXItem3.setName(PGZXItemEnum.DayTwo3.getName());
            mTVTitle.setText("出殡前");
        } else if (day == 3) {
            mPGZXItem1.setName(PGZXItemEnum.DayThree1.getName());
            mPGZXItem2.setName(PGZXItemEnum.DayThree2.getName());
            mPGZXItem3.setName(PGZXItemEnum.DayThree3.getName());
            mTVTitle.setText("出殡当天");
        }
        mPGZXItem1.setID(orderId, consultId);
        mPGZXItem2.setID(orderId, consultId);
        mPGZXItem3.setID(orderId, consultId);
    }


}
