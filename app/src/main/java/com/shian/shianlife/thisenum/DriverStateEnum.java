package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum DriverStateEnum {
    waitCar(0, "待派车"),
    isAttribute(1, "待接单"),
    waitGetCar(2, "待取车"),
    setOff(3, "待出发"),
    callFor(4, "去接人"),
    waitGoOnCar(5, "等上车"),
    alreadyGoOnCar(6, "已上车"),
    deliverd(7, "已送达"),
    successService(8, "成功服务"),
    cancel(9, "已取消");
    private Integer code;
    private String name;

    DriverStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
