package com.shian.shianlife.mvp.shared.bean;

/**
 * Created by zm.
 */

public class SharedGoodsServiceInfo {
    private String customerName;
    private String customerPhone;
    private Integer serviceWay;
    private String serviceLocation;
    private String serviceDetailsLocation;
    private String serviceTime;

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getServiceWay() {
        return serviceWay;
    }

    public void setServiceWay(Integer serviceWay) {
        this.serviceWay = serviceWay;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceDetailsLocation() {
        return serviceDetailsLocation;
    }

    public void setServiceDetailsLocation(String serviceDetailsLocation) {
        this.serviceDetailsLocation = serviceDetailsLocation;
    }
}
