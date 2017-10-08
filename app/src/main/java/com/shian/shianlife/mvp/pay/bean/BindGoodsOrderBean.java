package com.shian.shianlife.mvp.pay.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class BindGoodsOrderBean extends BaseHttpParams {
    private Long orderId;//订单id
    private String outTradeNo;//商户订单号

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
