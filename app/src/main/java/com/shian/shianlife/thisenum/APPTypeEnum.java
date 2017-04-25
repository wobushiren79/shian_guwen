package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum APPTypeEnum {
    ADVISER(1, "殡仪"),
    EXECUTOR(2, "顾问"),
    CEMETERY(3, "公墓");

    private int code;
    private String name;

    APPTypeEnum(int code, String name) {
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
