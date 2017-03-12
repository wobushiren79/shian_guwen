package com.shian.shianlife.thisenum;


import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/12.
 */

public enum UserCenterEnum {
    HELP("客服帮助", R.drawable.zhy_user_center_help),
    COLLECTION("我的收藏", R.drawable.zhy_user_center_collection),
    IDEA("意见反馈", R.drawable.zhy_user_center_idea),
    SETTING("设置", R.drawable.zhy_user_center_setting),
    PLATFORM("入驻平台", R.drawable.zhy_user_center_platform),
    VERSION("版本号", R.drawable.zhy_user_center_version);

    String name;
    int picId;

    UserCenterEnum(String name, int picId) {
        this.name = name;
        this.picId = picId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
