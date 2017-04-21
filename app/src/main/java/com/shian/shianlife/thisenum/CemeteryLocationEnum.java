package com.shian.shianlife.thisenum;

/**
 * Created by Administrator on 2017/4/13.
 */

public enum CemeteryLocationEnum {
    CEMETERYNAME(0, "公墓名称"),
    LOCATIONGARDEN(1, "苑"),
    LOCATIONAREA(2, "区"),
    LOCATIONROW(3, "排"),
    LOCATIONNUM(4, "号");

    private int code;
    private String name;

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

    CemeteryLocationEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
