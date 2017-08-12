package com.shian.shianlife.provide.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryOrderModel implements Serializable{
    private String customerName;//string	客户姓名
    private String customerMobile;//string	联系电话
    private String promiseTime;//string	预约时间
    private String customerLocation;//string	客户地址
    private String planCemeteryLocation;//string	预约参观公墓
    private String trafficWay;//string	交通方式
    private String personNum;//string	人数
    private long bespeakId;//	long	预约id
    private int bespeakStatus;//int	预约洽谈状态，值：1未分配、2未指派、3未接单、4已接单、5再次洽谈、6洽谈失败（未购墓）、7已下单、8洽谈成功（购墓）、9服务结束
    private String cemeteryReceive;//string	公墓接待
    private long orderId;//long	订单id
    private String detailsLocation;//公墓名称位置、
    private String orderedTombDate;//date	订墓日期
    private String payOffTime;//date	完款日期

    private int isSentCar;//0未申请过派车 1申请过派车
    /**
     * 取消状态
     */
    private int cancelStatus;

    public int getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(int cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public int getIsSentCar() {
        return isSentCar;
    }

    public void setIsSentCar(int isSentCar) {
        this.isSentCar = isSentCar;
    }

    public String getDetailsLocation() {
        return detailsLocation;
    }

    public void setDetailsLocation(String detailsLocation) {
        this.detailsLocation = detailsLocation;
    }

    public String getOrderedTombDate() {
        return orderedTombDate;
    }

    public void setOrderedTombDate(String orderedTombDate) {
        this.orderedTombDate = orderedTombDate;
    }

    public String getPayOffTime() {
        return payOffTime;
    }

    public void setPayOffTime(String payOffTime) {
        this.payOffTime = payOffTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getPromiseTime() {
        return promiseTime;
    }

    public void setPromiseTime(String promiseTime) {
        this.promiseTime = promiseTime;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getPlanCemeteryLocation() {
        return planCemeteryLocation;
    }

    public void setPlanCemeteryLocation(String planCemeteryLocation) {
        this.planCemeteryLocation = planCemeteryLocation;
    }

    public String getTrafficWay() {
        return trafficWay;
    }

    public void setTrafficWay(String trafficWay) {
        this.trafficWay = trafficWay;
    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public int getBespeakStatus() {
        return bespeakStatus;
    }

    public void setBespeakStatus(int bespeakStatus) {
        this.bespeakStatus = bespeakStatus;
    }

    public String getCemeteryReceive() {
        return cemeteryReceive;
    }

    public void setCemeteryReceive(String cemeteryReceive) {
        this.cemeteryReceive = cemeteryReceive;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
