package com.shian.shianlife.thisenum;

/**
 * Created by Administrator on 2017/3/31.
 */

public enum TalkEditInfoEnum {
    noTalk(0, "不可编辑"),
    canTalk(1, "可以编辑");
    private Integer code;
    private String text;

    TalkEditInfoEnum(Integer code, String text) {
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
