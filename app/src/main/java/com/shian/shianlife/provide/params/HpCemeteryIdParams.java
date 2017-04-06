package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HpCemeteryIdParams extends BaseHttpParams {
    long bespeakId;
    long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }
}
