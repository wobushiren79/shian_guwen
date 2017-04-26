package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/1/17.
 */

public class HpSaveCemeteryBuildData extends BaseHttpParams {
    private int submitType;  //提交类型（0：只有新建权限的人提交,提交后不能直接进入洽谈。1：既有新建权限也有洽谈权限的提交，提交后直接进入洽谈）
    private String customerName;    //客户姓名
    private String promiseTime;  //预约时间
    private String planCemeteryLocation; //	预约地点（预约参观公墓）
    private String trafficWay; //交通方式
    private String personNum;  //参观人数
    private String customerLocation;  //客户地址
    private String customerMobile;  //联系电话（客户电话）
    private long planCemeteryId;//参观公墓ID
    /**
     * 登录通行key
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getPlanCemeteryId() {
        return planCemeteryId;
    }
    public void setPlanCemeteryId(long planCemeteryId) {
        this.planCemeteryId = planCemeteryId;
    }
    public int getSubmitType() {
        return submitType;
    }

    public void setSubmitType(int submitType) {
        this.submitType = submitType;
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

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
}
