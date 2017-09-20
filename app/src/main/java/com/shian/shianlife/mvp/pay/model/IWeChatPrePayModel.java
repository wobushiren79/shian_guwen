package com.shian.shianlife.mvp.pay.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;

/**
 * Created by zm.
 */

public interface IWeChatPrePayModel {
    void wechatPrePay(Context context, WeChatPrePayBean params, OnGetDataListener listener);
}
