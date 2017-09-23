package com.shian.shianlife.mvp.userinfo.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListBean;

/**
 * Created by zm.
 */

public interface IUserInfoIntegralListModel {
    void getIntegralList(Context context, UserInfoIntegralListBean params, OnGetDataListener listener);
}
