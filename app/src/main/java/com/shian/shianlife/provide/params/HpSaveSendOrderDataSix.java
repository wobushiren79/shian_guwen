package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HpSaveSendOrderDataSix extends BaseHttpParams {
    private long consultId;
    private String funeralCarNum;//出殡车辆
    private String funeralPersonNum;// 出殡人数

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public String getFuneralCarNum() {
        return funeralCarNum;
    }

    public void setFuneralCarNum(String funeralCarNum) {
        this.funeralCarNum = funeralCarNum;
    }

    public String getFuneralPersonNum() {
        return funeralPersonNum;
    }

    public void setFuneralPersonNum(String funeralPersonNum) {
        this.funeralPersonNum = funeralPersonNum;
    }
}
