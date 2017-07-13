package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.CemeteryServiceActivity;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.activity.order.StoreServiceActivity;

/**
 * Created by zm.
 */

public enum OrderItemShowEnum {
    cemetery(1, "公墓服务", R.drawable.order_cemetery_icon, CemeteryServiceActivity.class),
    funeral(2, "殡仪服务", R.drawable.order_funeral_icon, FuneralServiceActivity.class),
    store(3, "单项服务", R.drawable.order_store_icon, StoreServiceActivity.class);

    private int code;
    private String name;
    private int itemPic;
    private Class<?> intentClass;

    OrderItemShowEnum(int code, String name, int itemPic, Class<?> intentClass) {
        this.code = code;
        this.name = name;
        this.itemPic = itemPic;
        this.intentClass = intentClass;
    }


    public Class<?> getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class<?> intentClass) {
        this.intentClass = intentClass;
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
