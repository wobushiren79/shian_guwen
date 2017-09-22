package com.shian.shianlife.mvp.userinfo.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSBean;

/**
 * Created by zm.
 */

public interface IChangePassWordSMSModel {
    void passWordSMS(Context context, ChangePassWordSMSBean params, OnGetDataListener listener);
}
