package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/19.
 */

public class HpSaveWaitServicePostData extends BaseHttpParams {
    private long consultId;
    private long deadTime;
    private String deadLocation;

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public long getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(long deadTime) {
        this.deadTime = deadTime;
    }

    public String getDeadLocation() {
        return deadLocation;
    }

    public void setDeadLocation(String deadLocation) {
        this.deadLocation = deadLocation;
    }
}
