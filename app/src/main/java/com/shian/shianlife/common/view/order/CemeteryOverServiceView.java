package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/1/9.
 */

public class CemeteryOverServiceView extends BaseOrderView {
    private View view;
    public CemeteryOverServiceView(Context context) {
        this(context, null);
    }

    public CemeteryOverServiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view= LayoutInflater.from(context).inflate(R.layout.view_cemetery_overservice,null);
        addView(view);
    }

    @Override
    public void refresh() {

    }
}
