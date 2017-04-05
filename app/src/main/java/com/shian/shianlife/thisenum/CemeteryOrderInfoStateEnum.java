package com.shian.shianlife.thisenum;

/**
 * Created by Administrator on 2017/4/5.
 */

public enum CemeteryOrderInfoStateEnum {
    notFillInfo(0, "未填写信息"),
    fillCemeteryOrdered(1, "填写了购墓订单"),
    fillCemeteryUserInfo(2, "填写了墓位使用者信息"),
    fillAgentInfo(3, "填写了经办人信息");
    private Integer code;
    private String text;

    CemeteryOrderInfoStateEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
