package com.shian.shianlife.provide.imp.impl;

import android.content.Context;
import android.util.Log;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignResultBean;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.SystemManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

/**
 * Created by zm.
 */

public class SystemManagerImpl extends BaseManagerImpl implements SystemManager {
    private static SystemManagerImpl manager;
    private CustomDialog customDialog;

    private SystemManagerImpl() {
        super();
        baseUrl = AppContansts.Login_BaseUrl;
    }


    public static SystemManagerImpl getInstance() {
        if (manager == null) {
            manager = new SystemManagerImpl();
        }
        return manager;
    }


    @Override
    public void loginSystem(Context context, SystemLoginBean params, HttpResponseHandler<SystemLoginResultBean> handler) {
        requestPost(context, "applogin", SystemLoginResultBean.class, params, handler);
    }

    @Override
    public void loginOutSystem(Context context, SystemLoginOutBean params, HttpResponseHandler<SystemLoginOutResultBean> handler) {
        requestPost(context, "app_logout", SystemLoginOutResultBean.class, params, handler);
    }

    @Override
    public void loginStoreSystem(final Context context, String loginKey) {
        String storeUrl = AppContansts.Login_Store_Url + "?" + loginKey;
        loginSubSystem(context, storeUrl);
    }

    @Override
    public void loginCemeterySystem(Context context, String loginKey) {
        String cemeteryUrl = AppContansts.Login_Cemetery_Url + "?" + loginKey;
        loginSubSystem(context, cemeteryUrl);
    }

    @Override
    public void userInfoSign(Context context, UserInfoSignBean params, HttpResponseHandler<UserInfoSignResultBean> handler) {
        requestPost(context, "api/credit/checkin", UserInfoSignResultBean.class, params, handler, true);
    }

    @Override
    public void getUserInfoIntegral(Context context, UserInfoIntegralBean params, HttpResponseHandler<UserInfoIntegralResultBean> handler) {
        requestPost(context, "api/credit/getCredit", UserInfoIntegralResultBean.class, params, handler);
    }

    @Override
    public void getUserInfoListIntegral(Context context, UserInfoIntegralListBean params, HttpResponseHandler<UserInfoIntegralListResultBean> handler) {
        requestPost(context, "api/credit/queryUserCreditLogsForPage", UserInfoIntegralListResultBean.class, params, handler);
    }

    @Override
    public void changePassWordSMS(Context context, ChangePassWordSMSBean params, HttpResponseHandler<ChangePassWordSMSResultBean> handler) {
        requestPost(context, "api/usersInfo/forgetKeys", ChangePassWordSMSResultBean.class, params, handler, true);
    }


    private void loginSubSystem(final Context context, String storeUrl) {
        if (customDialog == null || !customDialog.isShowing()) {
            customDialog = new CustomDialog(context);
            customDialog.show();
        }
        Log.v("tag", "storeUrl:" + storeUrl);
        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(storeUrl);
        getBuilder.addHeader("client-Type", "wechatapp");
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtils.show(context, e.getMessage());
                customDialog.cancel();
                if (customDialog != null)
                    customDialog.cancel();
//                Utils.jumpLogin(context);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.v("tag", "storeResponse:" + response);
                if (customDialog != null)
                    customDialog.cancel();
            }
        });
    }


}
