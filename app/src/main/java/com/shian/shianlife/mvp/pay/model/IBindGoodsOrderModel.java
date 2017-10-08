package com.shian.shianlife.mvp.pay.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderBean;

/**
 * Created by zm.
 */

public interface IBindGoodsOrderModel  {
    void bindGoodsOrder(Context context, BindGoodsOrderBean params, OnGetDataListener listener);
}
