package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum OrderByEnum {
    DESC(0, "降序"),
    ASC(1, "升序");

    private int code;
    private String name;

    OrderByEnum(int code, String name) {
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
