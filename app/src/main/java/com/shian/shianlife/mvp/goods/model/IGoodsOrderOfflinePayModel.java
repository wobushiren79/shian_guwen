package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderOfflinePayModel {
    void offlinePay(Context context, GoodsOrderOfflinePayBean params, OnGetDataListener listener);
}
