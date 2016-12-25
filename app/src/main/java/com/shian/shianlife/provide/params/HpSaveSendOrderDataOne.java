package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/21.
 */

public class HpSaveSendOrderDataOne extends BaseHttpParams {
    private long consultId;
    private String zsLocation;

    public String getZsLocation() {
        return zsLocation;
    }

    public void setZsLocation(String zsLocation) {
        this.zsLocation = zsLocation;
    }

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

}
