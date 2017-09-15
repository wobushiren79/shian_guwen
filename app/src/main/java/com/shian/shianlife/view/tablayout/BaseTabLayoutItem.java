package com.shian.shianlife.view.tablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;

/**
 * Created by zm.
 */

public abstract class BaseTabLayoutItem extends LinearLayout {
    protected View view;

    public BaseTabLayoutItem(Context context, int layoutId) {
        this(context, layoutId, null);
    }

    public BaseTabLayoutItem(Context context, int layoutId, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, layoutId, this);
    }

    public abstract void setSelect(boolean isSelect);
}
