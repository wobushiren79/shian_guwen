package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum CarBusiTypeEnum {
    cemetery_bespeakid(1, "cemetery_bespeak");
    private Integer code;
    private String text;

    CarBusiTypeEnum(Integer code, String text) {
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
