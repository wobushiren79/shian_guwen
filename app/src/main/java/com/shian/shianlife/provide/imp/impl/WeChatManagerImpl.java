package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginResultBean;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.WeChatManager;

/**
 * Created by zm.
 */

public class WeChatManagerImpl extends BaseManagerImpl implements WeChatManager {
    private static WeChatManagerImpl manager;

    private WeChatManagerImpl() {
        super();
        baseUrl = AppContansts.PHP_WeChat_BaseUrl;
    }


    public static WeChatManagerImpl getInstance() {
        if (manager == null) {
            manager = new WeChatManagerImpl();
        }
        return manager;
    }

    @Override
    public void loginDuiBa(Context context, DuiBaLoginBean params, HttpResponseHandler<DuiBaLoginResultBean> handler) {
        requestPostFormToListForObj(context, "wechatApi/login/app_get_duiba", DuiBaLoginResultBean.class, params, handler);
    }
}
