package com.shian.shianlife.thisenum;

/**
 * Created by zm
 * 发票抬头类型:0个人，1公司
 */
public enum GoodsInvoiceTitleTypeEnum {
    personal(0, "个人"),
    company(1, "公司");

    private int code;
    private String name;

    GoodsInvoiceTitleTypeEnum(int code, String name) {
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
        for (GoodsInvoiceTitleTypeEnum e : GoodsInvoiceTitleTypeEnum.values()) {
            if (e.getCode() == status) {
                return e.getName();
            }
        }
        return null;
    }
}
