package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * 服务方式(0,及时服务 1,预约服务 2自提)
 */
public enum GoodsAduitResultEnum {
    wait_aduit(0, "未审核"),
    pass_aduit(1, "审核通过"),
    fail_aduit(2, "审核未通过");

    private int code;
    private String name;

    GoodsAduitResultEnum(int code, String name) {
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
        for (GoodsAduitResultEnum e : GoodsAduitResultEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
