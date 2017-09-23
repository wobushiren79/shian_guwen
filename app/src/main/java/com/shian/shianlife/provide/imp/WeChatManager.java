package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginResultBean;
import com.shian.shianlife.provide.base.HttpResponseHandler;

/**
 * Created by zm.
 */

public interface WeChatManager {

    /**
     * 登陸對吧
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginDuiBa(Context context, DuiBaLoginBean params, HttpResponseHandler<DuiBaLoginResultBean> handler);
}
