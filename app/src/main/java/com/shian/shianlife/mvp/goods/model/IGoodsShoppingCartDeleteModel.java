package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartDeleteModel {
    void deleteGoodsShoppingCart(Context context, GoodsShoppingCartDeleteBean params, OnGetDataListener listener);
}
