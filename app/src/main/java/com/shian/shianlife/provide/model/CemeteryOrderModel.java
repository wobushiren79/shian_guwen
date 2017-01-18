package com.shian.shianlife.provide.model;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryOrderModel {
    private long consultId;//	咨询ID
    private Integer consultStatus;//咨询状态:1：未接单，2：已接单，3：洽谈失败（未购墓），4：洽谈成功（购墓），5，服务结束
    private Integer talkFailResult;//"洽谈失败的结果：1.未洽谈，2洽谈失败，3，预约2次洽谈"
    private String customerName;//客户姓名
    private String promiseTime;//	预约时间
    private String customerAddress;//	预约地点
    private String customerMobile;//客户联系电话
    private String trafficWay;//交通方式
    private String remark;//备注
    private Integer infoStatus;//资料填写状态：0，未填写信息。1，填写了购墓订单，2.填写了使用者信息，3填写了经办人信息
    private long consultAssignId;//咨询指派ID
    private boolean isEdit;//是否可编辑（true:可以编辑 false：不可编辑 ）

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public Integer getConsultStatus() {
        return consultStatus;
    }

    public void setConsultStatus(Integer consultStatus) {
        this.consultStatus = consultStatus;
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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

    public long getConsultAssignId() {
        return consultAssignId;
    }

    public void setConsultAssignId(long consultAssignId) {
        this.consultAssignId = consultAssignId;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
