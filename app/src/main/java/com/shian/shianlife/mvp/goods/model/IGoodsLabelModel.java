package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelBean;

/**
 * Created by zm.
 */

public interface IGoodsLabelModel {
    void getGoodsMainRecommendTitle(Context context, GoodsLabelBean params, OnGetDataListener listener);
}
