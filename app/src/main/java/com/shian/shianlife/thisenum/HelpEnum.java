package com.shian.shianlife.thisenum;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public enum HelpEnum {
    ALL("热门问题", R.drawable.zhy_help_other, 0),
    MONEY("财务结算", R.drawable.zhy_help_money, 1),
    SYSTEMHELP("系统操作", R.drawable.zhy_help_systemhelp, 2),
    PROJECTDETAILS("产品介绍", R.drawable.zhy_help_projectdetails, 3),
    TALK("洽谈技巧", R.drawable.zhy_help_talk, 4),
    PLATFORM("平台入驻", R.drawable.zhy_help_platform, 5),
    OTHER("其它问题", R.drawable.zhy_help_other, 6);

    private String name;
    private int picId;
    private int code;

    HelpEnum(String name, int picId, int code) {
        this.name = name;
        this.picId = picId;
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
