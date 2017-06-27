package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/21.
 */

public class HrGetSendOrderDataOne {
    private String deadTime;
    private String deadLocation;//去世地址，String
    private String agentmanLocation;//经办人地点，String
    private String deadmanLocation; //往生者地址，String
    private String zsLocation;//治丧地址，String

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
