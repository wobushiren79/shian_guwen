package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * :0待派单 1已派单(待接单) 2待执行(已接单) 3执行中  4审核中  5成功服务 6审核失败 7交易关闭
 */
public enum GoodsPerformStatusEnum {
    waitassign(0, "待派单"),
    hasassign(1, "待接单"),
    waitexecute(2, "待执行"),
    executeing(3, "执行中"),
    auditing(4, "审核中"),
    success(5, "成功服务"),
    auditfail(6, "审核失败"),
    cancel(7, "交易关闭");


    private int code;
    private String name;

    GoodsPerformStatusEnum(int code, String name) {
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
        for (GoodsPerformStatusEnum e : GoodsPerformStatusEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
