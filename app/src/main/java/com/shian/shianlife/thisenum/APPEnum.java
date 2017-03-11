package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/6.
 */

public enum APPEnum {
    ALL("全部应用", R.drawable.zhy_myapp_allapp, "all"),//其他APP
    ZSPROJECT("治丧产品", R.drawable.zhy_myapp_zsproject, "http://m.e-funeral.cn/Home/Plan/lst.html"),//治丧产品
    CEMETERY("公墓", R.drawable.zhy_myapp_cemetery, "http://m.e-funeral.cn/Home/Goods/index.html"),//公墓
    BEFORECONTRACT("生前契约", R.drawable.zhy_myapp_beforecontract, ""),//生前契约
    MICROMALL("微商城", R.drawable.zhy_myapp_micromall, "https://kdt.im/AHSojr"),//微商城
    NAVIGATION("导航", R.drawable.zhy_myapp_navigation, "navigation"),//导航
    CALENDAR("万年历", R.drawable.zhy_myapp_calendar, "calendar"),//万年历
    CALCULATOR("计算器", R.drawable.zhy_myapp_calculator, "calculator"),//计算器
    COMMUNICATION("通讯宝", R.drawable.zhy_myapp_zsproject, "communication"),//通讯宝
    DIDI("滴滴打车", R.drawable.zhy_myapp_didi, "http://webapp.diditaxi.com.cn"),//
    INTEGRALMALL("积分商城", R.drawable.zhy_myapp_integralmall, ""); //积分商城

    private String name;
    private int picId;
    private String url;

    private APPEnum(String name, int picId, String url) {
        this.name = name;
        this.picId = picId;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
