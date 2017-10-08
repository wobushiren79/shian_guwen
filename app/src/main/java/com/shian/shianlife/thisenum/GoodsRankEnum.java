package com.shian.shianlife.thisenum;

/**
 * Created by zm.
 */

public enum GoodsRankEnum {
    Total_Desc(1, "total desc", "价钱降序"),
    Total_Asc(2, "total asc", "价钱升序"),
    Sale_Amount_Desc(3, "sale_amount desc", "数量倒序"),
    Sale_Amount_Asc(4, "sale_amount asc", "数量升序"),
    Sort_Desc(5, "sort desc", "默认倒序"),
    Sort_Asc(6, "sort asc", "默认升序");

    private int num;
    private String code;
    private String name;

    GoodsRankEnum(int num, String code, String name) {
        this.num = num;
        this.code = code;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
