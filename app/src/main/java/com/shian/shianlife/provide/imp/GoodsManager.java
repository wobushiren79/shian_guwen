package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
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
}
