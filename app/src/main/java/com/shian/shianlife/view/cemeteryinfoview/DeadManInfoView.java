package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DeadManInfoView extends LinearLayout {
    private View view;
    public DeadManInfoView(Context context) {
        this(context,null);
    }

    public DeadManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_deadman_info, this);
    }
}
