package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListBean;

/**
 * Created by zm.
 */

public interface IGoodsDetailsListModel {
    void getGoodsDetailsList(Context context, GoodsDetailsListBean params, OnGetDataListener listener);
}
