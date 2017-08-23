package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.provide.base.HttpResponseHandler;


/**
 * Created by zm.
 */

public interface SystemManager {

    /**
     * 登陸系統
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginSystem(Context context, SystemLoginBean params, HttpResponseHandler<SystemLoginResultBean> handler);

    /**
     * 退出登陆
     *
     * @param context
     * @param params
     * @param handler
     */
    void loginOutSystem(Context context, SystemLoginOutBean params, HttpResponseHandler<SystemLoginOutResultBean> handler);

    /**
     * 单项系统登陆
     *
     * @param context
     * @param loginKey
     */
    void loginStoreSystem(Context context, String loginKey);
}
