package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsLabelModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsLabelModelImpl implements IGoodsLabelModel {
    @Override
    public void getGoodsMainRecommendTitle(Context context, GoodsLabelBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodLabel(context, params, new HttpResponseHandler<List<GoodsLabelResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsLabelResultBean> result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
