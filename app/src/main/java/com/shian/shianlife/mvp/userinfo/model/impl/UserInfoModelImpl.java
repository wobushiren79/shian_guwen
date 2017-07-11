package com.shian.shianlife.mvp.userinfo.model.impl;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.shian.shianlife.activity.UserInfoActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoResultBean;
import com.shian.shianlife.mvp.userinfo.model.IUserInfoModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.result.HrUserInfo;

import org.codehaus.jackson.map.util.BeanUtil;

/**
 * Created by zm.
 */

public class UserInfoModelImpl implements IUserInfoModel {
    @Override
    public void getUserInfoData(Context context, UserInfoBean params, final OnGetDataListener<UserInfoResultBean> listener) {
        MHttpManagerFactory.getAccountManager().getUserInfoData(context, new HttpResponseHandler<UserInfoResultBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(final UserInfoResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
