package com.shian.shianlife.mvp.userinfo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zm.
 */

public class UserInfoIntegralResultBean {


    private Integer usableCredit;//用户积分
    private Boolean canCheckin;//是否能签到
    private Integer keeps;//联系签到次数

    private int totalCredit;
    private int lockedCredit;
    private int cemeteryCredit;
    private int goodsCredit;
    private int id;
    private int sysUserId;
    private int cashedCredit;

    public Integer getUsableCredit() {
        return usableCredit;
    }

    public void setUsableCredit(Integer usableCredit) {
        this.usableCredit = usableCredit;
    }

    public Boolean getCanCheckin() {
        return canCheckin;
    }

    public void setCanCheckin(Boolean canCheckin) {
        this.canCheckin = canCheckin;
    }

    public Integer getKeeps() {
        return keeps;
    }

    public void setKeeps(Integer keeps) {
        this.keeps = keeps;
    }
}
