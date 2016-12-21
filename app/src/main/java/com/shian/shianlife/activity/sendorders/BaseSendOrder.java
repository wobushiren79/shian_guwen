package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/12/21.
 */

public class BaseSendOrder extends LinearLayout {
     View view;

    public BaseSendOrder(Context context, int layoutID) {
        super(context);
        view = LayoutInflater.from(context).inflate(layoutID, null);
        this.addView(view);
    }


}
