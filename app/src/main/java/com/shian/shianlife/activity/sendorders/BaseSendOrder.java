package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public abstract class BaseSendOrder extends LinearLayout {
    View view;
    long consultId;
    Context context;
    List<String> listData = new ArrayList<>();

    public abstract void saveData();

    public abstract void getData();

    public BaseSendOrder(Context context, int layoutID, long consultId) {
        super(context);
        this.context = context;
        this.consultId = consultId;
        view = LayoutInflater.from(context).inflate(layoutID, this);
    }


}
