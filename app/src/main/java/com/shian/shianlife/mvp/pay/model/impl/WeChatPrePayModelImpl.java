package com.shian.shianlife.mvp.pay.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;
import com.shian.shianlife.mvp.pay.model.IWeChatPrePayModel;
import com.shian.shianlife.provide.MHttpManagerFactory;

/**
 * Created by zm.
 */

public class WeChatPrePayModelImpl implements IWeChatPrePayModel {
    @Override
    public void wechatPrePay(Context context, WeChatPrePayBean params, OnGetDataListener listener) {
//        MHttpManagerFactory.getWeChatManager().loginDuiBa();
        listener.getDataSuccess(null);
    }
}
