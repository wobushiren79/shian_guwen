package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum ShowModeEnum {
    edit(1, "编辑"),
    show(2, "展示");


    private int code;
    private String name;

    ShowModeEnum(int code, String name) {
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
