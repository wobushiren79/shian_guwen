package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/24.
 */

public class HrGetCustomerFuneralOther {
    private String deadTime;// 去世时间，Date
    private String deadLocation;// 去世地址，String
    private String zsLocation;//治丧地址,
    private String funeralLocation;//殡仪馆地址
    private String funeralTime;//出殡时间 data
    private String fireTime;//火化时间
    private String crematorName;// 火化炉
    private String serviceWindows;// 服务窗口
    private String bodiesPark;//遗体停放类型
    private String bodiesByeTime;//遗体告别仪式时间 data
    private String ashDeal;//骨灰处理

    public String getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    public String getFuneralTime() {
        return funeralTime;
    }

    public void setFuneralTime(String funeralTime) {
        this.funeralTime = funeralTime;
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

    public String getDeadLocation() {
        return deadLocation;
    }

    public void setDeadLocation(String deadLocation) {
        this.deadLocation = deadLocation;
    }

    public String getZsLocation() {
        return zsLocation;
    }

    public void setZsLocation(String zsLocation) {
        this.zsLocation = zsLocation;
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

    public String getServiceWindows() {
        return serviceWindows;
    }

    public void setServiceWindows(String serviceWindows) {
        this.serviceWindows = serviceWindows;
    }

    public String getBodiesPark() {
        return bodiesPark;
    }

    public void setBodiesPark(String bodiesPark) {
        this.bodiesPark = bodiesPark;
    }



    public String getAshDeal() {
        return ashDeal;
    }

    public void setAshDeal(String ashDeal) {
        this.ashDeal = ashDeal;
    }
}
