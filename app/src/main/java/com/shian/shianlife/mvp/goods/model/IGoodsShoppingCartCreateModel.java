package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartCreateModel {
    void createGoodsShoppingCart(Context context, GoodsShoppingCartCreateBean params, OnGetDataListener listener);
}
