package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;

/**
 * Created by zm.
 */

public interface IGoodsChannelModel {
    void getGoodsChannel(Context context, GoodsChannelBean channelBean, OnGetDataListener listener);
}
