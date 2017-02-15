package com.shian.shianlife.common.view.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MainSetmealOtherView extends LinearLayout {
    private View layout;

    public MainSetmealOtherView(Context context) {
        this(context, null);
    }

    public MainSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        layout= LayoutInflater.from(getContext()).inflate(R.layout.view_mainsetmealother,this);
    }
}
