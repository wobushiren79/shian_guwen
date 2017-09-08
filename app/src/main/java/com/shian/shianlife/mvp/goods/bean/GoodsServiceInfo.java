package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * Created by zm.
 */

public class GoodsServiceInfo extends BaseEntity {
    private Long orderId;
    private Integer serviceWay;
    private String selfDelivery;
    private String selfDeliveryTime;
    private String bookTime;
    private String contact;
    private String contactPhone;
    private String serviceLocation;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceWay() {
        return serviceWay;
    }

    public void setServiceWay(Integer serviceWay) {
        this.serviceWay = serviceWay;
    }

    public String getSelfDelivery() {
        return selfDelivery;
    }

    public void setSelfDelivery(String selfDelivery) {
        this.selfDelivery = selfDelivery;
    }

    public String getSelfDeliveryTime() {
        return selfDeliveryTime;
    }

    public void setSelfDeliveryTime(String selfDeliveryTime) {
        this.selfDeliveryTime = selfDeliveryTime;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }
}
