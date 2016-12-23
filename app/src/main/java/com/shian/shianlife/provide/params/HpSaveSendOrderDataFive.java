package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HpSaveSendOrderDataFive extends BaseHttpParams {
    private long consultId;
    private String theDayLocation;//出殡当天当前地址

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public String getTheDayLocation() {
        return theDayLocation;
    }

    public void setTheDayLocation(String theDayLocation) {
        this.theDayLocation = theDayLocation;
    }
}
