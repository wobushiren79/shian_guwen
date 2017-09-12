package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderCreateModel {
   void createGoodsOrder(Context context, GoodsOrderCreateBean params, OnGetDataListener listener);
}
