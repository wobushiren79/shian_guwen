package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum UpDataImportantEnum {
    NOTIMPORTANT(0, "非必需"),
    IMPORTANT(1, "必须");

    private int code;
    private String name;

    UpDataImportantEnum(int code, String name) {
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
