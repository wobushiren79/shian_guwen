package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum GoodsPayEnum {
    no_pay(0, "未支付"),
    has_pay(1, "已支付");

    private int code;
    private String name;

    GoodsPayEnum(int code, String name) {
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
