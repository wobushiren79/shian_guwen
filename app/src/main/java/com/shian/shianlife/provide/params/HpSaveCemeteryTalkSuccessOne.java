package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HpSaveCemeteryTalkSuccessOne extends BaseHttpParams {
    private long bespeakId;//咨询ID
    private int saveType;//提交人的类型（1，洽谈人。2，售后。如果是售后，那么这个提交只能进行一次）
    private String orderNum;//订单编号
    private String cemeteryName;//公墓名称
    private String garden;//	苑
    private String district;//区
    private String platoon;//排
    private String mark;//号
    private String cemeteryType;//	墓型
    private String cemeteryProperties;//	墓穴属性
    private String planSale;//挂牌价
    private String saleMoney;//成交价
    private String payState;//支付情况
    private String moneyPay;//金额
    private String cemeteryReceive;//	公墓接待
    private String freeService;//赠送服务
    private String choiceService;//自选服务
    private String remark;//	备注

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public int getSaveType() {
        return saveType;
    }

    public void setSaveType(int saveType) {
        this.saveType = saveType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPlatoon() {
        return platoon;
    }

    public void setPlatoon(String platoon) {
        this.platoon = platoon;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCemeteryType() {
        return cemeteryType;
    }

    public void setCemeteryType(String cemeteryType) {
        this.cemeteryType = cemeteryType;
    }

    public String getCemeteryProperties() {
        return cemeteryProperties;
    }

    public void setCemeteryProperties(String cemeteryProperties) {
        this.cemeteryProperties = cemeteryProperties;
    }

    public String getPlanSale() {
        return planSale;
    }

    public void setPlanSale(String planSale) {
        this.planSale = planSale;
    }

    public String getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(String saleMoney) {
        this.saleMoney = saleMoney;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getMoneyPay() {
        return moneyPay;
    }

    public void setMoneyPay(String moneyPay) {
        this.moneyPay = moneyPay;
    }

    public String getCemeteryReceive() {
        return cemeteryReceive;
    }

    public void setCemeteryReceive(String cemeteryReceive) {
        this.cemeteryReceive = cemeteryReceive;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    public String getChoiceService() {
        return choiceService;
    }

    public void setChoiceService(String choiceService) {
        this.choiceService = choiceService;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
