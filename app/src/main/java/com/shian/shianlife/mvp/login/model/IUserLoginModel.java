package com.shian.shianlife.mvp.login.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.bean.UserLoginConfig;


/**
 * Created by zm.
 */

public interface IUserLoginModel {

    /**
     * 登录平台
     *
     * @param context
     * @param params
     * @param listener
     */
    void loginSystem(Context context, SystemLoginBean params, OnGetDataListener<SystemLoginResultBean> listener);

    /**
     * 退出登录平台
     *
     * @param context
     * @param params
     * @param listener
     */
    void loginOutSystem(Context context, SystemLoginOutBean params, OnGetDataListener<SystemLoginOutResultBean> listener);

    /**
     * 保存登录设置
     *
     * @param context
     * @param loginConfig
     */
    void saveLoginConfig(Context context, UserLoginConfig loginConfig);

    /**
     * 获取登录设置
     *
     * @param context
     * @return
     */
    UserLoginConfig getLoginConfig(Context context);
}
