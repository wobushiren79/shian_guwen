package com.shian.shianlife.view.ptr;


import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zm.
 */

public class CustomPtrFramelayout extends PtrFrameLayout {
    CustomPtrHeader header;
    CustomPtrHeader footer;

    public CustomPtrFramelayout(Context context) {
        super(context);
        initViews();
    }

    public CustomPtrFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CustomPtrFramelayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        header = new CustomPtrHeader(getContext());
        setHeaderView(header);
        addPtrUIHandler(header);
        footer = new CustomPtrHeader(getContext());
        setFooterView(footer);
        addPtrUIHandler(footer);
    }
}
