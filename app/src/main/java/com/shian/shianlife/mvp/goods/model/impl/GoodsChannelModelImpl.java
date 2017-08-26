package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsChannelModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsChannelModelImpl implements IGoodsChannelModel {
    @Override
    public void getGoodsChannel(Context context, GoodsChannelBean channelBean, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodsChannel(context, channelBean, new HttpResponseHandler<List<GoodsChannelResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsChannelResultBean> result) {
                listener.getDataSuccess(result);
            }


            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
