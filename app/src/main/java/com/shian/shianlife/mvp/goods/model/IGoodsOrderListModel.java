package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderListModel {
    void getGoodsOrderListData(Context context, GoodsOrderListBean params, OnGetDataListener listener);
}
