package com.shian.shianlife.view.tablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by zm.
 */

public class NormalTabLayoutItem extends BaseTabLayoutItem {
    private TextView tvTitle;
    private TextView tvIndicator;

    public NormalTabLayoutItem(Context context) {
        this(context, null);
    }

    public NormalTabLayoutItem(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_tablayout_item_normal, attrs);

        initView();
        initData();
    }


    private void initView() {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvIndicator = (TextView) view.findViewById(R.id.tv_indicator);
    }


    private void initData() {

    }

    public void setTitle(String name) {
        tvTitle.setText(name);
    }

    @Override
    public void setSelect(boolean isSelect) {
        if (isSelect) {
            tvTitle.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            tvIndicator.setVisibility(VISIBLE);
        } else {
            tvTitle.setTextColor(getResources().getColor(R.color.zhy_text_color_16));
            tvIndicator.setVisibility(GONE);
        }
    }
}
