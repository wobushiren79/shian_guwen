package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;

/**
 * Created by zm.
 */

public enum OrderItemShowEnum {
    cemetery(1, "公墓服务", R.drawable.order_cemetery_icon),
    funeral(2, "殡仪服务", R.drawable.order_funeral_icon),
    store(3, "单项服务", R.drawable.order_store_icon);

    private int code;
    private String name;
    private int itemPic;

    OrderItemShowEnum(int code, String name, int itemPic) {
        this.code = code;
        this.name = name;
        this.itemPic = itemPic;
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

    public int getItemPic() {
        return itemPic;
    }

    public void setItemPic(int itemPic) {
        this.itemPic = itemPic;
    }
}
