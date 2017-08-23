package com.shian.shianlife.mvp.login.view;


import android.content.Context;

import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;

/**
 * Created by zm.
 */

public interface IUserLoginOutView {
    /**
     * 获取上下文对象
     *
     * @return
     */
    Context getContext();


    /**
     * 退出平臺成功
     *
     * @param result
     */
    void loginOutSystemSuccess(SystemLoginOutResultBean result);

    /**
     * 退出平臺失败
     *
     * @param message
     */
    void loginOutSystemFail(String message);
}
