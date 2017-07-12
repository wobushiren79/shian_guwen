package com.shian.shianlife.mvp.order.view;

import android.content.Context;

import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;

/**
 * Created by zm.
 */

public interface IOrderShowView {
    Context getContext();

    void showOrderItems(OrderShowResultBean resultBean);
}
