package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

/**
 * Created by zm.
 */

public interface GoodsManager {

    /**
     * 获取渠道
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsChannel(Context context, GoodsChannelBean params, HttpResponseHandler<List<GoodsChannelResultBean>> handler);

    /**
     * 获取分类
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsClass(Context context, GoodsClassBean params, HttpResponseHandler<List<GoodsClassResultBean>> handler);

    /**
     * 获取分类属性
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsClassAttr(Context context, GoodsClassAttrBean params, HttpResponseHandler<List<GoodsClassAttrResultBean>> handler);


    /**
     * 获取商品查询数据列表
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsQuery(Context context, GoodsQueryListBean params, HttpResponseHandler<List<GoodsQueryListResultBean>> handler);

    /**
     * 获取商品详情
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsDetails(Context context, GoodsDetailsBean params, HttpResponseHandler<GoodsDetailsResultBean> handler);


    /**
     * 获取商品列表
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getGoodsDetailsList(Context context, GoodsDetailsListBean params, HttpResponseHandler<List<GoodsDetailsListResultBean>> handler);
}
