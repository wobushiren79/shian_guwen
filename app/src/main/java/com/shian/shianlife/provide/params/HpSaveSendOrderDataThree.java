package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HpSaveSendOrderDataThree extends BaseHttpParams {
    private long consultId;
    private String afterLocation;//出殡前当前地址

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public String getAfterLocation() {
        return afterLocation;
    }

    public void setAfterLocation(String afterLocation) {
        this.afterLocation = afterLocation;
    }
}
