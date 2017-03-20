package com.shian.shianlife.common.view.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/20.
 */

public class FuneralSetmealOtherView extends LinearLayout {
    View view;

    public FuneralSetmealOtherView(Context context) {
        this(context, null);
    }

    public FuneralSetmealOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_funeralsetmealother, this);
        initView();
    }

    private void initView() {

    }
}
