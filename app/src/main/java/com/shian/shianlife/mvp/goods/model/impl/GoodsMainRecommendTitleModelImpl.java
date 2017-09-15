package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleBean;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsMainRecommendTitleModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsMainRecommendTitleModelImpl implements IGoodsMainRecommendTitleModel {
    @Override
    public void getGoodsMainRecommendTitle(Context context, GoodsMainRecommendTitleBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodsMainRecommendTitle(context, params, new HttpResponseHandler<List<GoodsMainRecommendTitleResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsMainRecommendTitleResultBean> result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
