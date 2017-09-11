package com.shian.shianlife.mvp.shared.bean;

import java.io.Serializable;

/**
 * Created by zm.
 */

public class SharedGoodsInvoiceInfoBean implements Serializable{
    private Integer isNeedInvoice;//是否需要发票
    private Integer invoiceType;//个人or单位
    private String companyName;//单位名称
    private String companyTaxNumber;//税号
    private String companyRemark;//单位备注

    private String receiverName;//收件人姓名
    private String receiverPhone;//收件人电话
    private String receiverLocation;//收件人地址
    private String receiverDetailsLocation;//详细地址

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTaxNumber() {
        return companyTaxNumber;
    }

    public void setCompanyTaxNumber(String companyTaxNumber) {
        this.companyTaxNumber = companyTaxNumber;
    }

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverLocation() {
        return receiverLocation;
    }

    public void setReceiverLocation(String receiverLocation) {
        this.receiverLocation = receiverLocation;
    }

    public String getReceiverDetailsLocation() {
        return receiverDetailsLocation;
    }

    public void setReceiverDetailsLocation(String receiverDetailsLocation) {
        this.receiverDetailsLocation = receiverDetailsLocation;
    }

    public Integer getIsNeedInvoice() {
        return isNeedInvoice;
    }

    public void setIsNeedInvoice(Integer isNeedInvoice) {
        this.isNeedInvoice = isNeedInvoice;
    }
}
