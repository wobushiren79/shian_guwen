package com.shian.shianlife.provide.params;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HpSaveCemeteryTalkSuccessContract extends BaseHttpParams {
    private long bespeakId;//咨询ID
    private long orderId;//订单ID
    private int saveType;//提交人的类型（1，洽谈人。2，售后。如果是售后，那么这个提交只能进行一次）
    private String orderNum;//订单编号
    private String cemeteryName;//公墓名称
    private long cemeteryId;//公墓名称 id
    private String garden;//	墓园（苑）
    private long tombId;//墓园（苑）id
    private String district;//区
    private long parkId;//园区id
    private String platoon;//排
    private long rowNumber;//排号
    private String mark;//墓位
    private long tombPositionId;//墓位id
    private String cemeteryType;//	墓型
    private String tombUseProperty ;//	墓穴属性
    private String planSale;//挂牌价
    private String saleMoney;//成交价
    private String payState;//支付情况
    private String moneyDeposit;//s金额
    private String cemeteryReceive;//	公墓接待
    private String freeService;//赠送服务
    private String choiceService;//自选服务
    private String remark;//	备注
    private String cemeterySales;//	公墓业务员

    public String getCemeterySales() {
        return cemeterySales;
    }

    public void setCemeterySales(String cemeterySales) {
        this.cemeterySales = cemeterySales;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public long getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(long cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public long getTombId() {
        return tombId;
    }

    public void setTombId(long tombId) {
        this.tombId = tombId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }

    public String getPlatoon() {
        return platoon;
    }

    public void setPlatoon(String platoon) {
        this.platoon = platoon;
    }

    public long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getTombPositionId() {
        return tombPositionId;
    }

    public void setTombPositionId(long tombPositionId) {
        this.tombPositionId = tombPositionId;
    }

    public String getCemeteryType() {
        return cemeteryType;
    }

    public void setCemeteryType(String cemeteryType) {
        this.cemeteryType = cemeteryType;
    }

    public String getTombUseProperty() {
        return tombUseProperty;
    }

    public void setTombUseProperty(String tombUseProperty) {
        this.tombUseProperty = tombUseProperty;
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

    public String getMoneyDeposit() {
        return moneyDeposit;
    }

    public void setMoneyDeposit(String moneyDeposit) {
        this.moneyDeposit = moneyDeposit;
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
