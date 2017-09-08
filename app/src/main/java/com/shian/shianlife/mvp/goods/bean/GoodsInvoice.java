package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsInvoice 实体
 * 创建人： CQ
 * 创建时间：2017-07-21
 */

public class GoodsInvoice extends BaseEntity {


    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 发票抬头类型:0个人，1公司
     */
    private Integer titleType;

    /**
     * 发票抬头
     */
    private String title;

    /**
     * 公司税号
     */
    private String companyTaxId;

    /**
     * 确认开发票时间
     */
    private String checkInvoiceTime;

    /**
     * 发票配送方式
     */
    private String deliveryWay;

    /**
     * 快递公司名称: 顺丰、圆通......
     */
    private String expressName;

    /**
     * 配送单号
     */
    private String deliveryNumber;

    /**
     * 发票备注
     */
    private String invoiceRemark;

    /**
     * 收件人
     */
    private String receiptName;

    /**
     * 收件人电话
     */
    private String receiptPhone;

    /**
     * 收件人地址
     */
    private String receiptLocation;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTitleType() {
        return titleType;
    }

    public void setTitleType(Integer titleType) {
        this.titleType = titleType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyTaxId() {
        return companyTaxId;
    }

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public String getCheckInvoiceTime() {
        return checkInvoiceTime;
    }

    public void setCheckInvoiceTime(String checkInvoiceTime) {
        this.checkInvoiceTime = checkInvoiceTime;
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }

    public String getReceiptLocation() {
        return receiptLocation;
    }

    public void setReceiptLocation(String receiptLocation) {
        this.receiptLocation = receiptLocation;
    }
}
