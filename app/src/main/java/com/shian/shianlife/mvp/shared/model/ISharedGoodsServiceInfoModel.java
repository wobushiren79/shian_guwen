package com.shian.shianlife.mvp.shared.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfo;

/**
 * Created by zm.
 */

public interface ISharedGoodsServiceInfoModel {
    void getSharedGoodsServiceInfo(Context context, OnGetDataListener listener);

    void setSharedGoodsServiceInfo(Context context, SharedGoodsServiceInfo params, OnGetDataListener listener);

    void chearSharedGoodsServiceInfo(Context context);
}
