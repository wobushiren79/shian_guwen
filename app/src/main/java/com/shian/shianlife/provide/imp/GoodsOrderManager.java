package com.shian.shianlife.provide.imp;

import android.content.Context;

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
    void getShoppingCartNumber(Context context, GoodsShoppingCartNumberBean params, HttpResponseHandler<GoodsShoppingCartNumberResultBean> handler);


    /**
     * 获取购物车列表
     *
     * @param context
     * @param params
     * @param handler
     */
    void getShoppingCartList(Context context, GoodsShoppingCartListBean params, HttpResponseHandler<GoodsShoppingCartListResultBean> handler);


    /**
     * 修改购物车商品数量
     *
     * @param context
     * @param params
     * @param handler
     */
    void changeShoppingCartNumber(Context context, GoodsShoppingCartChangeNumberBean params, HttpResponseHandler<GoodsShoppingCartChangeNumberResultBean> handler);

    /**
     * 刪除购物车数据
     *
     * @param context
     * @param params
     * @param handler
     */
    void deleteShoppingCart(Context context, GoodsShoppingCartDeleteBean params, HttpResponseHandler<GoodsShoppingCartDeleteResultBean> handler);


    /**
     * 添加购物车商品
     *
     * @param context
     * @param params
     * @param handler
     */
    void createShoppingCart(Context context, GoodsShoppingCartCreateBean params, HttpResponseHandler<GoodsShoppingCartCreateResultBean> handler);

}
