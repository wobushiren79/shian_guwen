package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/5.
 */

public class HpSaveCemeteryTalkData extends BaseHttpParams {

    private long bespeakId;//咨询ID
    private long orderedId;//订单ID
    private String planBuyCemetery;//计划购买墓型
    private String planBuyMoney;//计划购买价位
    private String userOneState;//使用者1现状
    private String userTwoState;//使用者2现状
    private String ashLocation;//骨灰当前所在地
    private String relation;//联系人是使用者的
    private String talkPoint;//	洽谈要点
    private int talkResult;//	洽谈结果(true:要预约二次洽谈，false:未预约)
    private String orderTime;//预约时间
    private String personNum;//人数
    private String trafficWay;//	交通方式
    private String orderLocation;//预约地点
    private String remark;//备注

    public long getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(long orderedId) {
        this.orderedId = orderedId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public String getPlanBuyCemetery() {
        return planBuyCemetery;
    }

    public void setPlanBuyCemetery(String planBuyCemetery) {
        this.planBuyCemetery = planBuyCemetery;
    }

    public String getPlanBuyMoney() {
        return planBuyMoney;
    }

    public void setPlanBuyMoney(String planBuyMoney) {
        this.planBuyMoney = planBuyMoney;
    }

    public String getUserOneState() {
        return userOneState;
    }

    public void setUserOneState(String userOneState) {
        this.userOneState = userOneState;
    }

    public String getUserTwoState() {
        return userTwoState;
    }

    public void setUserTwoState(String userTwoState) {
        this.userTwoState = userTwoState;
    }

    public String getAshLocation() {
        return ashLocation;
    }

    public void setAshLocation(String ashLocation) {
        this.ashLocation = ashLocation;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTalkPoint() {
        return talkPoint;
    }

    public void setTalkPoint(String talkPoint) {
        this.talkPoint = talkPoint;
    }

    public int getTalkResult() {
        return talkResult;
    }

    public void setTalkResult(int talkResult) {
        this.talkResult = talkResult;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public String getTrafficWay() {
        return trafficWay;
    }

    public void setTrafficWay(String trafficWay) {
        this.trafficWay = trafficWay;
    }

    public String getOrderLocation() {
        return orderLocation;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
