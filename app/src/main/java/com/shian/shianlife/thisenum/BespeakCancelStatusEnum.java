package com.shian.shianlife.thisenum;

/**
 * @author zhangfb
 */
public enum BespeakCancelStatusEnum {

    UN_PROCESS(0, "未受理"),
    WAIT_SERVICE(1, "受理中"),
    CANCEL_SUCCESS(2, "取消成功"),
    CANCEL_FAIL(3, "取消失败");

    private int code;    // 代码
    private String name; // 名称

    BespeakCancelStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}