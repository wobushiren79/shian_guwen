package com.shian.shianlife.mvp.shared.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;

/**
 * Created by zm.
 */

public interface ISharedGoodsInvoiceInfoView extends BaseMVPView {
    void getSharedGoodsInvoiceInfoSuccess(SharedGoodsInvoiceInfoBean result);

    void getSharedGoodsInvoiceInfoFail(String msg);

    void setSharedGoodsInvoiceInfoSuccess(SharedGoodsInvoiceInfoBean msg);

    void setSharedGoodsInvoiceInfoFail(String msg);


    /**
     * 设置发票类型 个人还是单位
     *
     * @param invoiceType
     */
    void setInvoiceType(Integer invoiceType);

    /**
     * 设置是否需要发票
     *
     * @param isNeedInvoice
     */
    void setIsNeedInvoice(Integer isNeedInvoice);

    /**
     * 设置单位名称
     *
     * @param companyName
     */
    void setInvoiceCompanyName(String companyName);

    /**
     * 设置税号
     *
     * @param companyTaxNumber
     */
    void setInvoiceCompanyTaxNumber(String companyTaxNumber);

    /**
     * 设置单位备注
     *
     * @param companyRemark
     */
    void setInvoiceCompanyRemark(String companyRemark);

    /**
     * 设置收件人姓名
     *
     * @param receiverName
     */
    void setInvoiceReceiverName(String receiverName);

    /**
     * 设置收件人电话
     *
     * @param receiverPhone
     */
    void setInvoiceReceiverPhone(String receiverPhone);

    /**
     * 设置详细地址
     *
     * @param receiverDetailsLocation
     */
    void setInvoiceReceiverDetailsLocation(String receiverDetailsLocation);

    /**
     * 设置收件人地址
     *
     * @param receiverLocation
     */
    void setInvoiceReceiverLocation(String receiverLocation);

    /**
     * 获取是否需要发票
     * @return
     */
    Integer getIsNeedInvoice();

    /**
     * 获取发票类型
     * @return
     */
    Integer getInvoiceType();

    /**
     * 获取单位名称
     * @return
     */
    String getInvoiceCompanyName();

    /**
     * 获取单位备注
     * @return
     */
    String getInvoiceCompanyRemark();

    /**
     * 获取税号
     * @return
     */
    String getInvoiceCompanyTaxNumber();

    /**
     * 获取收件人姓名
     * @return
     */
    String getInvoiceReceiverName();

    /**
     * 获取收件人电话
     * @return
     */
    String getInvoiceReceiverPhone();

    /**
     * 获取收件人地址
     * @return
     */
    String getInvoiceReceiverLocation();

    /**
     * 获取收件人详细地址
     * @return
     */
    String getInvoiceReceiverDetailsLocation();
}
