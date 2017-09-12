package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;
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

    @Override
    public void changeShoppingCartNumber(Context context, GoodsShoppingCartChangeNumberBean params, HttpResponseHandler<GoodsShoppingCartChangeNumberResultBean> handler) {
        requestPost(context, "api/goods/shopping/updateShopingNum", GoodsShoppingCartChangeNumberResultBean.class, params, handler, true);
    }

    @Override
    public void deleteShoppingCart(Context context, GoodsShoppingCartDeleteBean params, HttpResponseHandler<GoodsShoppingCartDeleteResultBean> handler) {
        requestPost(context, "api/goods/shopping/remove", GoodsShoppingCartDeleteResultBean.class, params, handler, true);
    }

    @Override
    public void createShoppingCart(Context context, GoodsShoppingCartCreateBean params, HttpResponseHandler<GoodsShoppingCartCreateResultBean> handler) {
        requestPost(context, "api/goods/shopping/save", GoodsShoppingCartCreateResultBean.class, params, handler, true);
    }

    @Override
    public void getStoreOrderDetails(Context context, StoreOrderDetailsBean params, HttpResponseHandler<StoreOrderDetailsResultBean> handler) {
        requestPost(context, "api/goods/order/findOrderDetailById", StoreOrderDetailsResultBean.class, params, handler, true);
    }

    @Override
    public void getPerformInfo(Context context, StoreOrderGetPerformBean params, HttpResponseHandler<StoreOrderGetPerformResultBean> handler) {
        requestPost(context, "api/goods/perform/findPerformInfoByPerformId", StoreOrderGetPerformResultBean.class, params, handler, true);
    }

    @Override
    public void createGoodsOrder(Context context, GoodsOrderCreateBean params, HttpResponseHandler<GoodsOrderCreateResultBean> handler) {
        requestPost(context, "api/goods/order/save", GoodsOrderCreateResultBean.class, params, handler, true);
    }

    @Override
    public void submitGoodsOrder(Context context, GoodsOrderSubmitBean params, HttpResponseHandler<GoodsOrderSubmitResultBean> handler) {
        requestPost(context, "api/goods/order/submit", GoodsOrderSubmitResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsOrderInfo(Context context, GoodsOrderInfoBean params, HttpResponseHandler<GoodsOrderInfoResultBean> handler) {
        requestPost(context, "api/goods/order/findById", GoodsOrderInfoResultBean.class, params, handler, true);
    }
}
