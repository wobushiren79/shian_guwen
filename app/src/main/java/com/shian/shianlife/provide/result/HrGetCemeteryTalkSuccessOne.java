package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HrGetCemeteryTalkSuccessOne {
    private String orderNum;//订单编号
    private boolean saveCan;//售后是否提交过（true:提交过。false:没有提交过）
    private String cemeteryName;//	公墓名称
    private String garden;//苑
    private String district;//区
    private String platoon;//排
    private String mark;//号
    private String cemeteryType;//墓型
    private String cemeteryProperties;//墓穴属性
    private String planSale;//	挂牌价
    private String saleMoney;//成交价
    private String payState;//支付情况
    private String moneyPay;//金额
    private String cemeteryReceive;//公墓接待
    private String freeService;//赠送服务
    private String choiceService;//自选服务
    private String remark;//备注
    private long cemeteryId;//	公墓名称 id
    private long tombId;//苑 id
    private long parkId;//	区 id
    private long rowNumber;//	排 id
    private long tombPositionId;//	号 id

    public long getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(long cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public long getTombId() {
        return tombId;
    }

    public void setTombId(long tombId) {
        this.tombId = tombId;
    }

    public long getParkId() {
        return parkId;
    }

    public void setParkId(long parkId) {
        this.parkId = parkId;
    }

    public long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public long getTombPositionId() {
        return tombPositionId;
    }

    public void setTombPositionId(long tombPositionId) {
        this.tombPositionId = tombPositionId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isSaveCan() {
        return saveCan;
    }

    public void setSaveCan(boolean saveCan) {
        this.saveCan = saveCan;
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
