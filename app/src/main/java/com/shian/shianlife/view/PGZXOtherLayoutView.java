package com.shian.shianlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/25.
 */

public class PGZXOtherLayoutView extends LinearLayout {
    View view;

    public PGZXOtherLayoutView(Context context) {
        super(context);
    }

    public PGZXOtherLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_otherpgzx_view, this);
        initView();
    }

    private void initView() {

    }
}
