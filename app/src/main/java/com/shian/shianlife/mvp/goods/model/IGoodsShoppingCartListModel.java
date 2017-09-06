package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartListModel {
    void getShoppingCartList(Context context, GoodsShoppingCartListBean params, OnGetDataListener listener);
}
