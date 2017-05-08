package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum MapLineStateEnum {
    WALK(1, "步行"),
    BUS(2, "步行"),
    DRIVE(3, "步行");

    private int code;
    private String name;

    MapLineStateEnum(int code, String name) {
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

