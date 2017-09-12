package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum GoodsOrderChannelEnum {
    Adviser_App(0, "顾问APP"),
    Customer_Wechat(1, "客户-小程序"),
    Adviser_Wechat(2, "顾问-小程序");
    private int code;
    private String name;

    GoodsOrderChannelEnum(int code, String name) {
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
