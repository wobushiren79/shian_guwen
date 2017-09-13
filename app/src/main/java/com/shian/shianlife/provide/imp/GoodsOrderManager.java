package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;
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

    /**
     * 获取单项订单详情
     *
     * @param context
     * @param params
     * @param handler
     */
    void getStoreOrderDetails(Context context, StoreOrderDetailsBean params, HttpResponseHandler<StoreOrderDetailsResultBean> handler);

    /**
     * 获取执行信息
     *
     * @param context
     * @param params
     * @param handler
     */
    void getPerformInfo(Context context, StoreOrderGetPerformBean params, HttpResponseHandler<StoreOrderGetPerformResultBean> handler);


    /**
     * 生成订单
     *
     * @param context
     * @param params
     * @param handler
     */
    void createGoodsOrder(Context context, GoodsOrderCreateBean params, HttpResponseHandler<GoodsOrderCreateResultBean> handler);


    /**
     * 提交订单
     *
     * @param context
     * @param params
     * @param handler
     */
    void submitGoodsOrder(Context context, GoodsOrderSubmitBean params, HttpResponseHandler<GoodsOrderSubmitResultBean> handler);


    /**
     * 获取订单信息
     *
     * @param context
     * @param params
     * @param handler
     */
    void getGoodsOrderInfo(Context context, GoodsOrderInfoBean params, HttpResponseHandler<GoodsOrderInfoResultBean> handler);


    /**
     * 获取列表
     *
     * @param context
     * @param params
     * @param handler
     */
    void getGoodsOrderList(Context context, GoodsOrderListBean params, HttpResponseHandler<GoodsOrderListResultBean> handler);
}
