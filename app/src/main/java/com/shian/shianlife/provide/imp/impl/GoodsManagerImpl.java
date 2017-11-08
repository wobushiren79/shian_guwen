package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelResultBean;
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
    private static GoodsManagerImpl manager;

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
        requestPostFormToListForList(context, "Api/Channel/channel", GoodsChannelResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsClass(Context context, GoodsClassBean params, HttpResponseHandler<List<GoodsClassResultBean>> handler) {
        requestPostFormToListForList(context, "Api/Goods/goodsclass", GoodsClassResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsClassAttr(Context context, GoodsClassAttrBean params, HttpResponseHandler<List<GoodsClassAttrResultBean>> handler) {
        requestPostFormToListForList(context, "Api/Goods/classattr", GoodsClassAttrResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsQuery(Context context, GoodsQueryListBean params, HttpResponseHandler<List<GoodsQueryListResultBean>> handler) {
        requestPostFormToListForList(context, "Api/goods/goods", GoodsQueryListResultBean.class, params, handler);
    }

    @Override
    public void getGoodsTextQuery(Context context, GoodsQueryListBean params, HttpResponseHandler<List<GoodsQueryListResultBean>> handler) {
        requestPostFormToListForList(context, "Api/search/search", GoodsQueryListResultBean.class, params, handler);
    }

    @Override
    public void getGoodsDetails(Context context, GoodsDetailsBean params, HttpResponseHandler<GoodsDetailsResultBean> handler) {
        requestPostFormToListForObj(context, "Api/goods/details", GoodsDetailsResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodsDetailsList(Context context, GoodsDetailsListBean params, HttpResponseHandler<List<GoodsDetailsListResultBean>> handler) {
        requestPostFormToListForList(context, "Api/getgoods/getattrgoods", GoodsDetailsListResultBean.class, params, handler, true);
    }

    @Override
    public void getGoodLabel(Context context, GoodsLabelBean params, HttpResponseHandler<List<GoodsLabelResultBean>> handler) {
        requestPostFormToListForList(context, "Api/Label/label", GoodsLabelResultBean.class, params, handler);
    }

    @Override
    public void getGoodLabelDetails(Context context, GoodsLabelDetailsBean params, HttpResponseHandler<List<GoodsLabelDetailsResultBean>> handler) {
        requestPostFormToListForList(context, "Api/Label/lobelgoods", GoodsLabelDetailsResultBean.class, params, handler);
    }

    @Override
    public void getGoodClassAttrMain(Context context, GoodsClassAttrMainBean params, HttpResponseHandler<List<GoodsClassAttrMainResultBean>> handler) {
        requestPostFormToListForList(context, "Api/Goods/label_goods_class", GoodsClassAttrMainResultBean.class, params, handler);
    }


}
