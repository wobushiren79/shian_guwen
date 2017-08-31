package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.CemeteryServiceActivity;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.activity.order.StoreServiceActivity;
import com.shian.shianlife.common.contanst.AppContansts;

/**
 * Created by zm.
 */

public enum OrderItemShowEnum {
    cemetery(1, "圆满-公墓", R.drawable.order_cemetery_icon, CemeteryServiceActivity.class, AppContansts.Temp_Cemetery_BaseUrl),
    funeral(2, "圆满-白事", R.drawable.order_funeral_icon, FuneralServiceActivity.class, AppContansts.Temp_Funeral_BaseUrl),
    store(3, "圆满-商城", R.drawable.order_store_icon, StoreServiceActivity.class, "");

    private int code;
    private String name;
    private int itemPic;
    private Class<?> intentClass;
    private String url;

    OrderItemShowEnum(int code, String name, int itemPic, Class<?> intentClass, String url) {
        this.code = code;
        this.name = name;
        this.itemPic = itemPic;
        this.intentClass = intentClass;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
