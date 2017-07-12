package com.shian.shianlife.mvp.order.model;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;

/**
 * Created by zm.
 */

public interface IOrderShowModel {
    void getOrderShowItems(OnGetDataListener<OrderShowResultBean> listener);
}
