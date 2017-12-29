package com.shian.shianlife.mvp.login.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;

/**
 * Created by zm.
 */

public interface ISubSystemLoginModel {
    void subSystemStoreLogin(Context context, String loginKey, OnGetDataListener listener);

    void subSystemOrderCenterLogin(Context context, String loginKey, OnGetDataListener listener);

    void subSystemCemeteryLogin(Context context, String loginKey, OnGetDataListener listener);
}
