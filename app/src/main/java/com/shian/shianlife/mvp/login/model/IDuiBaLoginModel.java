package com.shian.shianlife.mvp.login.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;

/**
 * Created by zm.
 */

public interface IDuiBaLoginModel {
    void loginDuiBa(Context context, DuiBaLoginBean params, OnGetDataListener listener);
}
