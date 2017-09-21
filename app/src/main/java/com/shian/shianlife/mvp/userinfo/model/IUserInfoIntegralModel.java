package com.shian.shianlife.mvp.userinfo.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralBean;

/**
 * Created by zm.
 */

public interface IUserInfoIntegralModel {
    void getUserInfoIntegral(Context context, UserInfoIntegralBean params, OnGetDataListener listener);
}
