package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2017/2/17.
 */

public class HrGetMsgNumberForUntreated {
    private int talk;//洽谈、
    private int service;//待服务、
    private int assignment;//服务派单中、
    private int unpaid;//待收款、
    private int auditing;//待评审

    private int endService;//服务结束（未启用）

    public int getTalk() {
        return talk;
    }

    public void setTalk(int talk) {
        this.talk = talk;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(int unpaid) {
        this.unpaid = unpaid;
    }

    public int getAuditing() {
        return auditing;
    }

    public void setAuditing(int auditing) {
        this.auditing = auditing;
    }

    public int getEndService() {
        return endService;
    }

    public void setEndService(int endService) {
        this.endService = endService;
    }
}
