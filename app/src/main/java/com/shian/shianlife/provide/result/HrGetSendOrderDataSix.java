package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HrGetSendOrderDataSix {
    private String funeralLocation;//殡仪馆地址
    private String crematorName;// 火化炉
    private String bodiesPark;//遗体停放类型

    private String funeralCarNum;//出殡车辆
    private String funeralPersonNum;// 出殡人数
    private String fireTime;//火化时间
    private String bodiesByeTime;// 遗体告别仪式时间
    private String funeralTime;//出殡时间

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

    public String getFuneralLocation() {
        return funeralLocation;
    }

    public void setFuneralLocation(String funeralLocation) {
        this.funeralLocation = funeralLocation;
    }

    public String getCrematorName() {
        return crematorName;
    }

    public void setCrematorName(String crematorName) {
        this.crematorName = crematorName;
    }

    public String getBodiesPark() {
        return bodiesPark;
    }

    public void setBodiesPark(String bodiesPark) {
        this.bodiesPark = bodiesPark;
    }

    public String getFireTime() {
        return fireTime;
    }

    public void setFireTime(String fireTime) {
        this.fireTime = fireTime;
    }

    public String getBodiesByeTime() {
        return bodiesByeTime;
    }

    public void setBodiesByeTime(String bodiesByeTime) {
        this.bodiesByeTime = bodiesByeTime;
    }

    public String getFuneralTime() {
        return funeralTime;
    }

    public void setFuneralTime(String funeralTime) {
        this.funeralTime = funeralTime;
    }
}
