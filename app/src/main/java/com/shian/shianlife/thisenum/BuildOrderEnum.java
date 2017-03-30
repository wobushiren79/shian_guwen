package com.shian.shianlife.thisenum;

import android.app.Activity;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.NewOrderActivity;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;

import static com.baidu.location.b.g.T;

/**
 * Created by Administrator on 2017/3/30.
 */

public enum BuildOrderEnum {

    BY("殡仪", R.drawable.zhy_build_order_by, NewOrderActivity.class),
    GM("公墓", R.drawable.zhy_build_order_gm, BuildNewOrderActivity.class);

    private String name;
    private int iconId;
    private Class<?> activity;

    BuildOrderEnum(String name, int iconId, Class<?> activity) {
        this.name = name;
        this.iconId = iconId;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }
}
