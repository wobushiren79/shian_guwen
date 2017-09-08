package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class StoreOrderGetPerformBean extends BaseHttpParams {
    private Long performId;//执行单id

    public Long getPerformId() {
        return performId;
    }

    public void setPerformId(Long performId) {
        this.performId = performId;
    }
}
