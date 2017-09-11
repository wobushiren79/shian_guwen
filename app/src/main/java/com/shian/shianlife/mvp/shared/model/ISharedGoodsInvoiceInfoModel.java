package com.shian.shianlife.mvp.shared.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;


/**
 * Created by zm.
 */

public interface ISharedGoodsInvoiceInfoModel {
    void getSharedGoodsInvoiceInfo(Context context, OnGetDataListener listener);

    void setSharedGoodsInvoiceInfo(Context context, SharedGoodsInvoiceInfoBean params, OnGetDataListener listener);

    void chearSharedGoodsInvoiceInfo(Context context);
}
