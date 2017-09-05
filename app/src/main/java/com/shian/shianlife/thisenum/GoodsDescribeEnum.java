package com.shian.shianlife.thisenum;

import com.shian.shianlife.view.goods.GoodsDescribeApplyLayout;
import com.shian.shianlife.view.goods.GoodsDescribeDetailsLayout;
import com.shian.shianlife.view.goods.GoodsDescribeSecurityLayout;

/**
 * Created by zm.
 */

public enum GoodsDescribeEnum {
    goods_details(1, "产品详情"),
    goods_apply(2, "适用信息"),
    goods_security(3, "售后保障");

    private int code;
    private String name;


    GoodsDescribeEnum(int code, String name) {
        this.code = code;
        this.name = name;

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
}
