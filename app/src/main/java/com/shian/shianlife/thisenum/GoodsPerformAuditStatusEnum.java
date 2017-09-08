package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * 0未审核  1审核通过 2审核未通过
 */
public enum GoodsPerformAuditStatusEnum {
    aduit_no(0, "未审核"),
    aduit_success(1, "审核通过"),
    aduit_fail(2, "审核未通过");
    private int code;
    private String name;

    GoodsPerformAuditStatusEnum(int code, String name) {
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

    public static String getValueText(Integer status) {
        if (status == null)
            return null;
        for (GoodsPerformAuditStatusEnum e : GoodsPerformAuditStatusEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
