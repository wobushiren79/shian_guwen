package com.shian.shianlife.mvp.pay.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class WeChatPrePayBean extends BaseHttpParams {
    private Integer total_fee;
    private String appid;
    private String mch_id;

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
