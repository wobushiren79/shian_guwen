package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.provide.base.HttpResponseHandler;

/**
 * Created by zm.
 */

public interface GoodsOrderManager {
    /**
     * 获取商品详情
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getShoppingCartNumber(Context context, GoodsShoppingCartNumberBean params, HttpResponseHandler<GoodsShoppingCartNumberResultBean> handler);
}
