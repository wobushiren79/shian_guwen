package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/6.
 */

public enum APPEnum {
    ALL("全部应用", R.drawable.zhy_myapp_allapp),//其他APP
    ZSPROJECT("治丧产品", R.drawable.zhy_myapp_zsproject),//治丧产品
    CEMETERY("公墓", R.drawable.zhy_myapp_cemetery),//公墓
    BEFORECONTRACT("生前契约", R.drawable.zhy_myapp_beforecontract),//生前契约
    MICROMALL("微商城", R.drawable.zhy_myapp_zsproject),//微商城
    NAVIGATION("导航", R.drawable.zhy_myapp_navigation),//导航
    CALENDAR("万年历", R.drawable.zhy_myapp_calculator),//万年历
    CALCULATOR("计算器", R.drawable.zhy_myapp_calculator),//计算器
    COMMUNICATION("通讯宝", R.drawable.zhy_myapp_zsproject),//通讯宝
    INTEGRALMALL("积分商城", R.drawable.zhy_myapp_zsproject); //积分商城

    private String name;
    private int picId;

    private APPEnum(String name, int picId) {
        this.name = name;
        this.picId = picId;
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
}
