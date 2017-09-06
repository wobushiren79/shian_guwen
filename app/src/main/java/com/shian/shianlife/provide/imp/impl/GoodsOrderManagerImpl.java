package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.GoodsOrderManager;

/**
 * Created by zm.
 */

public class GoodsOrderManagerImpl extends BaseManagerImpl implements GoodsOrderManager {
    private static GoodsOrderManagerImpl manager;

    private GoodsOrderManagerImpl() {
        super();
        baseUrl = AppContansts.Store_BaseUrl;
    }

    public static GoodsOrderManagerImpl getInstance() {
        if (manager == null) {
            synchronized (GoodsOrderManagerImpl.class) {
                if (manager == null) {
                    manager = new GoodsOrderManagerImpl();
                }
            }
        }
        return manager;
    }

    @Override
    public void getShoppingCartNumber(Context context, GoodsShoppingCartNumberBean params, HttpResponseHandler<GoodsShoppingCartNumberResultBean> handler) {
        requestPost(context, "api/goods/shopping/getShoppingNumber", GoodsShoppingCartNumberResultBean.class, params, handler);
    }

    @Override
    public void getShoppingCartList(Context context, GoodsShoppingCartListBean params, HttpResponseHandler<GoodsShoppingCartListResultBean> handler) {
        requestPost(context, "api/goods/shopping/list", GoodsShoppingCartListResultBean.class, params, handler);
    }
}
