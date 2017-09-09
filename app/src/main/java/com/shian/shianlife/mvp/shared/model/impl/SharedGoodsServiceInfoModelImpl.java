package com.shian.shianlife.mvp.shared.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.BaseSharedModel;
import com.shian.shianlife.mvp.shared.SharedTag;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfo;
import com.shian.shianlife.mvp.shared.model.ISharedGoodsServiceInfoModel;

/**
 * Created by zm.
 */

public class SharedGoodsServiceInfoModelImpl extends BaseSharedModel implements ISharedGoodsServiceInfoModel {

    private final String ShareKey_CustomerName = "CustomerName";
    private final String ShareKey_CustomerPhone = "CustomerPhone";
    private final String ShareKey_ServiceWay = "ServiceWay";
    private final String ShareKey_ServiceLocation = "ServiceLocation";
    private final String ShareKey_ServiceDetailsLocation = "ServiceDetailsLocation";
    private final String ShareKey_ServiceTime = "ServiceTime";

    @Override
    public void getSharedGoodsServiceInfo(Context context, OnGetDataListener listener) {
        try {
            SharedPreferences data = getDataShared(context, SharedTag.Tag_Goods_Service_Info);
            String customerName = data.getString(ShareKey_CustomerName, "");
            String customerPhone = data.getString(ShareKey_CustomerPhone, "");
            Integer serviceWay = data.getInt(ShareKey_ServiceWay, -1);
            String serviceLocation = data.getString(ShareKey_ServiceLocation, "");
            String serviceDetailsLocation = data.getString(ShareKey_ServiceDetailsLocation, "");
            String serviceTime = data.getString(ShareKey_ServiceTime, "");

            SharedGoodsServiceInfo serviceInfo = new SharedGoodsServiceInfo();
            serviceInfo.setCustomerName(customerName);
            serviceInfo.setCustomerPhone(customerPhone);
            serviceInfo.setServiceWay(serviceWay);
            serviceInfo.setServiceLocation(serviceLocation);
            serviceInfo.setServiceDetailsLocation(serviceDetailsLocation);
            serviceInfo.setServiceTime(serviceTime);

            listener.getDataSuccess(serviceInfo);
        } catch (Exception e) {
            listener.getDataFail("获取失败");
            e.printStackTrace();
        }
    }

    @Override
    public void setSharedGoodsServiceInfo(Context context, SharedGoodsServiceInfo params, OnGetDataListener listener) {
        try {
            SharedPreferences.Editor editor = getEditShared(context, SharedTag.Tag_Goods_Service_Info);
            editor.putString(ShareKey_CustomerName, params.getCustomerName());
            editor.putString(ShareKey_CustomerPhone, params.getCustomerPhone());
            editor.putInt(ShareKey_ServiceWay, params.getServiceWay());
            editor.putString(ShareKey_ServiceLocation, params.getServiceLocation());
            editor.putString(ShareKey_ServiceDetailsLocation, params.getServiceDetailsLocation());
            editor.putString(ShareKey_ServiceTime, params.getServiceTime());
            editor.commit();
            listener.getDataSuccess("保存成功");
        } catch (Exception e) {
            listener.getDataFail("保存失败");
            e.printStackTrace();
        }
    }

    @Override
    public void chearSharedGoodsServiceInfo(Context context) {
        clearShared(context, SharedTag.Tag_Goods_Service_Info);
    }
}
