package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HrGetSendOrderDataFive {
    private String deadLocation;// 去世地址
    private String agentmanLocation;//  经办人地点
    private String deadmanLocation;// 往生者地址
    private String zsLocation;//  治丧地址
    private String theDayLocation;// 出殡当天当前地址

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

    public String getTheDayLocation() {
        return theDayLocation;
    }

    public void setTheDayLocation(String theDayLocation) {
        this.theDayLocation = theDayLocation;
    }
}
