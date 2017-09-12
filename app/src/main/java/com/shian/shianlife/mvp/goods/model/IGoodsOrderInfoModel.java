package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderInfoModel {
    void getGoodsOrderInfo(Context context, GoodsOrderInfoBean params, OnGetDataListener listener);
}
