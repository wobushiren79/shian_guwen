package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleBean;

/**
 * Created by zm.
 */

public interface IGoodsMainRecommendTitleModel {
    void getGoodsMainRecommendTitle(Context context, GoodsMainRecommendTitleBean params, OnGetDataListener listener);
}
