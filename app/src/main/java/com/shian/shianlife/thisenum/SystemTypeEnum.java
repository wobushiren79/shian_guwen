package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum SystemTypeEnum {
    funeral(1, "殡仪"),
    cemetery(2, "公墓"),
    platform(3, "平台");

    private int code;
    private String name;

    SystemTypeEnum(int code, String name) {
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
