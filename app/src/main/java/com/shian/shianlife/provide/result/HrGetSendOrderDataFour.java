package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HrGetSendOrderDataFour {
    private String zsLocation;//治丧地址
    private String crematorName;//火化炉
    private String serviceWindows;//服务窗口
    private String bodiesPark;//遗体停放类型
    private String bodiesParkName;//遗体停放厅名字
    private String fireTime;//火化时间
    private String bodiesByeTime;// 遗体告别仪式时间
    private String funeralTime;//出殡时间
    private String funeralRemark;//备注

    public String getZsLocation() {
        return zsLocation;
    }

    public void setZsLocation(String zsLocation) {
        this.zsLocation = zsLocation;
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

    public String getBodiesParkName() {
        return bodiesParkName;
    }

    public void setBodiesParkName(String bodiesParkName) {
        this.bodiesParkName = bodiesParkName;
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

    public String getFuneralRemark() {
        return funeralRemark;
    }

    public void setFuneralRemark(String funeralRemark) {
        this.funeralRemark = funeralRemark;
    }
}
