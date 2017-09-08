package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * 服务方式(0,及时服务 1,预约服务 2自提)
 */
public enum GoodsServiceWayEnum {
    now_service(0, "及时服务"),
    plan_service(1, "预约服务"),
    self_service(2, "自提");


    private int code;
    private String name;

    GoodsServiceWayEnum(int code, String name) {
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
        for (GoodsServiceWayEnum e : GoodsServiceWayEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
