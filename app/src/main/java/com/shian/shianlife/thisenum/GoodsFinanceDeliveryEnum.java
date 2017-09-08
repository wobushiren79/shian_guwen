package com.shian.shianlife.thisenum;

/**
 * Created by zm
 */
public enum GoodsFinanceDeliveryEnum {
    notinvoicement(0, "不需要开票"), hasinvoicement(1, "需要开票");

    private int code;
    private String name;

    GoodsFinanceDeliveryEnum(int code, String name) {
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
        for (GoodsFinanceDeliveryEnum e : GoodsFinanceDeliveryEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
