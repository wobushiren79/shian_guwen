package com.shian.shianlife.provide.model;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryOrderModel {
    private long bespeakId;//	咨询ID
    private long orderId;//订单ID
    private Integer bespeakStatus;//咨询状态:1：未接单，2：已接单，3：洽谈失败（未购墓），4：洽谈成功（购墓），5，服务结束
    private Integer talkFailResult;//"洽谈失败的结果：1.未洽谈，2洽谈失败，3，预约2次洽谈"
    private String customerName;//客户姓名
    private String promiseTime;//	预约时间
    private String planCemeteryLocation;//预约参观公墓
    private String customerLocation;//	客户地址
    private String customerMobile;//客户联系电话
    private String trafficWay;//交通方式
    private String remark;//备注

    private String agentmanName;//经办人姓名
    private String agentmanMoblie;//经办人电话
    private String deadmanName;//	使用者（往生者1）
    private String choiceCemeteryName;//		选择的公墓名字
    private String detailsLocation;//		园区详细地址

    private Integer infoStatus;//资料填写状态：0，未填写信息。1，填写了购墓订单，2.填写了使用者信息，3填写了经办人信息
    private long bespeakAssignId;//咨询指派ID
    private Integer isEditInfo;//是否可编辑（1:可以编辑 0：不可编辑 ）

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public Integer getBespeakStatus() {
        return bespeakStatus;
    }

    public void setBespeakStatus(Integer bespeakStatus) {
        this.bespeakStatus = bespeakStatus;
    }

    public long getBespeakAssignId() {
        return bespeakAssignId;
    }

    public void setBespeakAssignId(long bespeakAssignId) {
        this.bespeakAssignId = bespeakAssignId;
    }

    public String getAgentmanName() {
        return agentmanName;
    }

    public void setAgentmanName(String agentmanName) {
        this.agentmanName = agentmanName;
    }

    public String getAgentmanMoblie() {
        return agentmanMoblie;
    }

    public void setAgentmanMoblie(String agentmanMoblie) {
        this.agentmanMoblie = agentmanMoblie;
    }

    public String getPlanCemeteryLocation() {
        return planCemeteryLocation;
    }

    public void setPlanCemeteryLocation(String planCemeteryLocation) {
        this.planCemeteryLocation = planCemeteryLocation;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getDeadmanName() {
        return deadmanName;
    }

    public void setDeadmanName(String deadmanName) {
        this.deadmanName = deadmanName;
    }

    public String getChoiceCemeteryName() {
        return choiceCemeteryName;
    }

    public void setChoiceCemeteryName(String choiceCemeteryName) {
        this.choiceCemeteryName = choiceCemeteryName;
    }

    public String getDetailsLocation() {
        return detailsLocation;
    }

    public void setDetailsLocation(String detailsLocation) {
        this.detailsLocation = detailsLocation;
    }



    public Integer getTalkFailResult() {
        return talkFailResult;
    }

    public void setTalkFailResult(Integer talkFailResult) {
        this.talkFailResult = talkFailResult;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPromiseTime() {
        return promiseTime;
    }

    public void setPromiseTime(String promiseTime) {
        this.promiseTime = promiseTime;
    }


    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getTrafficWay() {
        return trafficWay;
    }

    public void setTrafficWay(String trafficWay) {
        this.trafficWay = trafficWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Integer getIsEditInfo() {
        return isEditInfo;
    }

    public void setIsEditInfo(Integer isEditInfo) {
        this.isEditInfo = isEditInfo;
    }
}
