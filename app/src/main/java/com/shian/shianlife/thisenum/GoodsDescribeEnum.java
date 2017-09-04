package com.shian.shianlife.thisenum;

import android.view.View;

import com.shian.shianlife.base.BaseLayout;

/**
 * Created by zm.
 */

public enum GoodsDescribeEnum {

    goods_details(1, "产品详情", BaseLayout.class),
    goods_apply(2, "适用信息", BaseLayout.class),
    goods_security(3, "售后保障", BaseLayout.class);


    private int code;
    private String name;
    private Class view;

    GoodsDescribeEnum(int code, String name, Class view) {
        this.code = code;
        this.name = name;
        this.view = view;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getView() {
        return view;
    }

    public void setView(Class view) {
        this.view = view;
    }
}
