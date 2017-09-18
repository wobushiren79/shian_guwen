package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsBean;


/**
 * Created by zm.
 */

public interface IGoodsLabelDetailsModel {
    void getGoodsLabelDetails(Context context, GoodsLabelDetailsBean params, OnGetDataListener listener);
}
