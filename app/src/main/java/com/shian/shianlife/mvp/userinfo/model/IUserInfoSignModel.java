package com.shian.shianlife.mvp.userinfo.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignBean;

/**
 * Created by zm.
 */

public interface IUserInfoSignModel {
    void userInfoSign(Context context, UserInfoSignBean params, OnGetDataListener listener);
}
