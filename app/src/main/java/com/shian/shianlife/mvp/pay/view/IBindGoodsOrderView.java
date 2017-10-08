package com.shian.shianlife.mvp.pay.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderBean;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderResultBean;

/**
 * Created by zm.
 */

public interface IBindGoodsOrderView extends BaseMVPView {
    void bindGoodsOrderSuccess(BindGoodsOrderResultBean resultBean);

    void bindGoodsOrderFail(String msg);

    /**
     * 获取订单ID
     * @return
     */
    Long getOrderId();

    /**
     * 获取统一下单生成的订单编号
     * @return
     */
    String getOutTradeNo();
}
