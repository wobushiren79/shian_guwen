package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/1/9.
 */

public class CemeterySHView extends BaseOrderView  {
    private View view;
    public CemeterySHView(Context context) {
        this(context,null);
    }

    public CemeterySHView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view= LayoutInflater.from(context).inflate(R.layout.view_cemetery_sh,null);
        addView(view);
    }

    @Override
    public void refresh() {

    }
}
