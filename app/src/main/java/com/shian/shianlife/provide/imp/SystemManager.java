package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignResultBean;
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

    /**
     * 用户签到
     *
     * @param context
     * @param params
     * @param handler
     */
    void userInfoSign(Context context, UserInfoSignBean params, HttpResponseHandler<UserInfoSignResultBean> handler);

    /**
     * 获取签到积分
     *
     * @param context
     * @param params
     * @param handler
     */
    void getUserInfoIntegral(Context context, UserInfoIntegralBean params, HttpResponseHandler<UserInfoIntegralResultBean> handler);

    /**
     * 通过短信修改密码
     * @param context
     * @param params
     * @param handler
     */
    void changePassWordSMS(Context context, ChangePassWordSMSBean params,HttpResponseHandler<ChangePassWordSMSResultBean> handler);
}
