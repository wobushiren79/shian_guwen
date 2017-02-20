package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/19.
 */

public class HpSaveContractData extends BaseHttpParams {
    private long consultId;
    private long orderId;
    private String picUrl;
    private float contractAmount;

    public float getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(float contractAmount) {
        this.contractAmount = contractAmount;
    }

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
