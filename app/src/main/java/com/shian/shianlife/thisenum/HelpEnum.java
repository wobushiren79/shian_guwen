package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public enum HelpEnum {
    MONEY("财务结算", R.drawable.zhy_help_money, ""),
    SYSTEMHELP("系统操作", R.drawable.zhy_help_systemhelp, ""),
    PROJECTDETAILS("产品介绍", R.drawable.zhy_help_projectdetails, ""),
    TALK("洽谈技巧", R.drawable.zhy_help_talk, ""),
    PLATFORM("平台入驻", R.drawable.zhy_help_platform, ""),
    OTHER("其它问题", R.drawable.zhy_help_other, "");

    private String name;
    private int picId;
    private String url;

    HelpEnum(String name, int picId, String url) {
        this.name = name;
        this.picId = picId;
        this.url = url;
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
