package com.shian.shianlife.mvp.shared.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.BaseSharedModel;
import com.shian.shianlife.mvp.shared.SharedTag;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfoBean;
import com.shian.shianlife.mvp.shared.model.ISharedGoodsInvoiceInfoModel;
import com.shian.shianlife.thisenum.GoodsFinanceDeliveryEnum;
import com.shian.shianlife.thisenum.GoodsInvoiceTitleTypeEnum;

/**
 * Created by zm.
 */

public class SharedGoodsInvoiceInfoModelImpl extends BaseSharedModel implements ISharedGoodsInvoiceInfoModel {

    private final String ShareKey_IsNeedInvoice = "isNeedInvoice";
    private final String ShareKey_InvoiceType = "invoiceType";
    private final String ShareKey_CompanyName = "companyName";
    private final String ShareKey_CompanyTaxNumber = "companyTaxNumber";
    private final String ShareKey_CompanyRemark = "companyRemark";

    private final String ShareKey_ReceiverName = "receiverName";
    private final String ShareKey_ReceiverPhone = "receiverPhone";
    private final String ShareKey_ReceiverLocation = "receiverLocation";
    private final String ShareKey_ReceiverDetailsLocation = "receiverDetailsLocation";

    @Override
    public void getSharedGoodsInvoiceInfo(Context context, OnGetDataListener listener) {
        try {
            SharedPreferences data = getDataShared(context, SharedTag.Tag_Goods_Invoice_Info);
            Integer isNeedInvoice = data.getInt(ShareKey_IsNeedInvoice, -1);
            Integer invoiceType = data.getInt(ShareKey_InvoiceType, -1);
            String companyName = data.getString(ShareKey_CompanyName, "");
            String companyTaxNumber = data.getString(ShareKey_CompanyTaxNumber, "");
            String companyRemark = data.getString(ShareKey_CompanyRemark, "");
            String receiverName = data.getString(ShareKey_ReceiverName, "");
            String receiverPhone = data.getString(ShareKey_ReceiverPhone, "");
            String receiverLocation = data.getString(ShareKey_ReceiverLocation, "");
            String receiverDetailsLocation = data.getString(ShareKey_ReceiverDetailsLocation, "");

            SharedGoodsInvoiceInfoBean invoiceInfo = new SharedGoodsInvoiceInfoBean();
            invoiceInfo.setIsNeedInvoice(isNeedInvoice);
            invoiceInfo.setInvoiceType(invoiceType);
            invoiceInfo.setCompanyName(companyName);
            invoiceInfo.setCompanyTaxNumber(companyTaxNumber);
            invoiceInfo.setCompanyRemark(companyRemark);
            invoiceInfo.setReceiverName(receiverName);
            invoiceInfo.setReceiverPhone(receiverPhone);
            invoiceInfo.setReceiverLocation(receiverLocation);
            invoiceInfo.setReceiverDetailsLocation(receiverDetailsLocation);

            listener.getDataSuccess(invoiceInfo);
        } catch (Exception e) {
            listener.getDataFail("获取失败");
            e.printStackTrace();
        }
    }

    @Override
    public void setSharedGoodsInvoiceInfo(Context context, SharedGoodsInvoiceInfoBean params, OnGetDataListener listener) {
        try {
            chearSharedGoodsInvoiceInfo(context);
            SharedPreferences.Editor editor = getEditShared(context, SharedTag.Tag_Goods_Invoice_Info);
            if (params.getIsNeedInvoice() != null)
                editor.putInt(ShareKey_IsNeedInvoice, params.getIsNeedInvoice());
            if (params.getInvoiceType() != null)
                editor.putInt(ShareKey_InvoiceType, params.getInvoiceType());
            if (params.getCompanyName() != null)
                editor.putString(ShareKey_CompanyName, params.getCompanyName());
            if (params.getCompanyTaxNumber() != null)
                editor.putString(ShareKey_CompanyTaxNumber, params.getCompanyTaxNumber());
            if (params.getCompanyRemark() != null)
                editor.putString(ShareKey_CompanyRemark, params.getCompanyRemark());
            if (params.getReceiverName() != null)
                editor.putString(ShareKey_ReceiverName, params.getReceiverName());
            if (params.getReceiverPhone() != null)
                editor.putString(ShareKey_ReceiverPhone, params.getReceiverPhone());
            if (params.getReceiverLocation() != null)
                editor.putString(ShareKey_ReceiverLocation, params.getReceiverLocation());
            if (params.getReceiverDetailsLocation() != null)
                editor.putString(ShareKey_ReceiverDetailsLocation, params.getReceiverDetailsLocation());
            editor.commit();
            listener.getDataSuccess(params);
        } catch (Exception e) {
            listener.getDataFail("保存失败");
            e.printStackTrace();
        }
    }

    @Override
    public void chearSharedGoodsInvoiceInfo(Context context) {
        clearShared(context, SharedTag.Tag_Goods_Invoice_Info);
    }
}
