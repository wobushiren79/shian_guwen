package com.shian.shianlife.provide.phpparams;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class PHPHpSiftListParams extends BaseHttpParams {
    private int type;
    private int number;
    private int pagerNumber;
    private int userType;
    private Long userid;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(int pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
