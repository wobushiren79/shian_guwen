package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * Created by zm.
 */

public class GoodsOrder extends BaseEntity {
    private int orderStatus;
    private int orderChannel;
    private String customerName;
    private String customerPhone;
    private Long connectId;
    private String orderNumber;
    private String orderComment;
    private int needInvoice;
    private int auditStatus;
    private int allotAuditor;
    private Long auditorId;
    private Long customerReceiveId;
    private Long customerDistributeId;
    private Long financeId;
    private Integer totalPrice;
    private int checkOrder;
    private Integer showTotalPrice;
    private int waitAssignNum;

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(int orderChannel) {
        this.orderChannel = orderChannel;
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

    public Long getConnectId() {
        return connectId;
    }

    public void setConnectId(Long connectId) {
        this.connectId = connectId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public int getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(int needInvoice) {
        this.needInvoice = needInvoice;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public int getAllotAuditor() {
        return allotAuditor;
    }

    public void setAllotAuditor(int allotAuditor) {
        this.allotAuditor = allotAuditor;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Long getCustomerReceiveId() {
        return customerReceiveId;
    }

    public void setCustomerReceiveId(Long customerReceiveId) {
        this.customerReceiveId = customerReceiveId;
    }

    public Long getCustomerDistributeId() {
        return customerDistributeId;
    }

    public void setCustomerDistributeId(Long customerDistributeId) {
        this.customerDistributeId = customerDistributeId;
    }

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(int checkOrder) {
        this.checkOrder = checkOrder;
    }

    public Integer getShowTotalPrice() {
        return showTotalPrice;
    }

    public void setShowTotalPrice(Integer showTotalPrice) {
        this.showTotalPrice = showTotalPrice;
    }

    public int getWaitAssignNum() {
        return waitAssignNum;
    }

    public void setWaitAssignNum(int waitAssignNum) {
        this.waitAssignNum = waitAssignNum;
    }
}
