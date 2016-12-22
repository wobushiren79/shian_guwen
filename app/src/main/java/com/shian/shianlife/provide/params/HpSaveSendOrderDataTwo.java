package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2016/12/22.
 */

public class HpSaveSendOrderDataTwo extends BaseHttpParams {
    private long consultId;
    private String funeralLocation;//殡仪馆地址
    private String fireWay;//火化手续办理方式
    private String trafficWay;//交通方式
    private long meetTime;//约定见面时间
    private String meetLocation;//约定见面地点

    private String deadmanCardIdPic;//逝者身份证照片地址
    private String deadmanAccountPic;//逝者户口薄照片地址
    private String agentmanCardIdPic;// 经办人身份证照片地址
    private String deadPic;//死亡证明照片地址

    private String firstDayRemark;//备注

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public String getFuneralLocation() {
        return funeralLocation;
    }

    public void setFuneralLocation(String funeralLocation) {
        this.funeralLocation = funeralLocation;
    }

    public String getFireWay() {
        return fireWay;
    }

    public void setFireWay(String fireWay) {
        this.fireWay = fireWay;
    }

    public String getTrafficWay() {
        return trafficWay;
    }

    public void setTrafficWay(String trafficWay) {
        this.trafficWay = trafficWay;
    }

    public long getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(long meetTime) {
        this.meetTime = meetTime;
    }

    public String getMeetLocation() {
        return meetLocation;
    }

    public void setMeetLocation(String meetLocation) {
        this.meetLocation = meetLocation;
    }

    public String getDeadmanCardIdPic() {
        return deadmanCardIdPic;
    }

    public void setDeadmanCardIdPic(String deadmanCardIdPic) {
        this.deadmanCardIdPic = deadmanCardIdPic;
    }

    public String getDeadmanAccountPic() {
        return deadmanAccountPic;
    }

    public void setDeadmanAccountPic(String deadmanAccountPic) {
        this.deadmanAccountPic = deadmanAccountPic;
    }

    public String getAgentmanCardIdPic() {
        return agentmanCardIdPic;
    }

    public void setAgentmanCardIdPic(String agentmanCardIdPic) {
        this.agentmanCardIdPic = agentmanCardIdPic;
    }

    public String getDeadPic() {
        return deadPic;
    }

    public void setDeadPic(String deadPic) {
        this.deadPic = deadPic;
    }

    public String getFirstDayRemark() {
        return firstDayRemark;
    }

    public void setFirstDayRemark(String firstDayRemark) {
        this.firstDayRemark = firstDayRemark;
    }
}
