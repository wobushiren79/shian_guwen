package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.GoodsManager;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsManagerImpl extends BaseManagerImpl implements GoodsManager {
    private static volatile GoodsManagerImpl manager;

    private GoodsManagerImpl() {
        super();
        baseUrl = AppContansts.Goods_BaseUrl;
    }

    public static GoodsManagerImpl getInstance() {
        if (manager == null) {
            synchronized (GoodsManagerImpl.class) {
                if (manager == null) {
                    manager = new GoodsManagerImpl();
                }
            }
        }
        return manager;
    }

    @Override
    public void getGoodsChannel(Context context, GoodsChannelBean params, HttpResponseHandler<List<GoodsChannelResultBean>> handler) {
        requestPostFormToList(context, "Api/Channel/channel", GoodsChannelResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsClass(Context context, GoodsClassBean params, HttpResponseHandler<List<GoodsClassResultBean>> handler) {
        requestPostFormToList(context, "Api/Goods/goodsclass", GoodsClassResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsClassAttr(Context context, GoodsClassAttrBean params, HttpResponseHandler<List<GoodsClassAttrResultBean>> handler) {
        requestPostFormToList(context, "Api/Goods/classattr", GoodsClassAttrResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsQuery(Context context, GoodsQueryListBean params, HttpResponseHandler<List<GoodsQueryListResultBean>> handler) {
        requestPostFormToList(context, "Api/goods/goods", GoodsQueryListResultBean.class, params, handler);
    }
}
