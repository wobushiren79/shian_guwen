package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/19.
 */

public class HrGetWaitServicePostData {
    private String deadTime;//去世时间
    private String deadLocation;//去世地址
    private String agentmanLocation;//经办人地点
    private String deadmanLocation;//往生者地址
    private String zsLocation;//治丧地址

    public String getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    public String getDeadLocation() {
        return deadLocation;
    }

    public void setDeadLocation(String deadLocation) {
        this.deadLocation = deadLocation;
    }

    public String getAgentmanLocation() {
        return agentmanLocation;
    }

    public void setAgentmanLocation(String agentmanLocation) {
        this.agentmanLocation = agentmanLocation;
    }

    public String getDeadmanLocation() {
        return deadmanLocation;
    }

    public void setDeadmanLocation(String deadmanLocation) {
        this.deadmanLocation = deadmanLocation;
    }

    public String getZsLocation() {
        return zsLocation;
    }

    public void setZsLocation(String zsLocation) {
        this.zsLocation = zsLocation;
    }
}
