package com.shian.shianlife.thisenum;


/**
 * Created by zm
 * 订单状态 0, 代付款或者待支付 值：1, 待服务  2,执行中  3,服务完成 4,成功服务  10,取消订单
 */
public enum GoodsOrderStatusEnum {
    waitpay(0, "待付款"),
    waitservice(1, "待服务"),
    execute(2, "执行中"),
    servicecomplete(3, "服务完成"),
    servicesuccess(4, "成功服务"),
    servicefail(10, "交易关闭");

    private int code;
    private String name;

    GoodsOrderStatusEnum(int code, String name) {
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
        for (GoodsOrderStatusEnum e : GoodsOrderStatusEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
