package com.shian.shianlife.provide.phpparams;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class PHPHpSiftDataParams extends BaseHttpParams {

    private int type;
    private Long userid;
    private int siftID;
    private int userType;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public int getSiftID() {
        return siftID;
    }

    public void setSiftID(int siftID) {
        this.siftID = siftID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
