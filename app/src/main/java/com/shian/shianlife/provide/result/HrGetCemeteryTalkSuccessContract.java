package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HrGetCemeteryTalkSuccessContract {
    private String orderNum;//app订单编号
    private String orderNumber;//后台订单编号
    private boolean saveCan;//售后是否提交过（true:提交过。false:没有提交过）

    private String cemeteryType;//墓型
    private String tombUseProperty;//	墓穴属性
    private String planSale;//	挂牌价
    private String saleMoney;//成交价
    private String payState;//支付情况
    private String moneyDeposit;//金额
    private String cemeteryReceive;//公墓接待
    private String freeService;//赠送服务
    private String choiceService;//自选服务
    private String remark;//备注

    private long cemeteryId;//	公墓名称 id
    private long tombId;//苑 id
    private long parkId;//	区 id
    private long rowNumber;//	排 id
    private long tombPositionId;//	号 id

    private String cemeteryName;    //		公墓名称
    private String tombName;    //	墓园(苑)名称
    private String parkName;    //	园区名称
    private int num;//	墓位号
    private String cemeterySales;//	公墓业务员

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCemeterySales() {
        return cemeterySales;
    }

    public void setCemeterySales(String cemeterySales) {
        this.cemeterySales = cemeterySales;
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

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public String getTombName() {
        return tombName;
    }

    public void setTombName(String tombName) {
        this.tombName = tombName;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
