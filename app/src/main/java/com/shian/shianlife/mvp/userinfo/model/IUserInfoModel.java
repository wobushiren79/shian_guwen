package com.shian.shianlife.mvp.userinfo.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoResultBean;

/**
 * Created by zm.
 */

public interface IUserInfoModel {
    void getUserInfoData(Context context, UserInfoBean params, OnGetDataListener<SystemLoginResultBean.UserObject> listener);
}
