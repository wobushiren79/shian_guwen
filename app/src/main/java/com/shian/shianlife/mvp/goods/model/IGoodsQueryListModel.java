package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListBean;

/**
 * Created by zm.
 */

public interface IGoodsQueryListModel {

    void getGoodsQueryData(Context context, GoodsQueryListBean params, OnGetDataListener listener);

    void getGoodsQueryTextData(Context context, GoodsQueryListBean params, OnGetDataListener listener);
}
