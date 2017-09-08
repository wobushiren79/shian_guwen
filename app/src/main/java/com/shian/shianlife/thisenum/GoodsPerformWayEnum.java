package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * 执行方式:0同城送达 1上门服务 2快递物流
 */
public enum GoodsPerformWayEnum {
    local_send(0, "同城送达"),
    home_send(1, "上门服务"),
    express_send(2, "快递物流");


    private int code;
    private String name;

    GoodsPerformWayEnum(int code, String name) {
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
        for (GoodsPerformWayEnum e : GoodsPerformWayEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
