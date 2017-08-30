package com.shian.shianlife.mvp.login.view;

import android.content.Context;

import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;


/**
 * Created by zm.
 */

public interface IUserLoginView {
    /**
     * 获取登陆姓名
     *
     * @return
     */
    String getUserName();

    /**
     * 设置登陆姓名
     *
     * @param userName
     */
    void setUserName(String userName);

    /**
     * 获取登陆密码
     *
     * @return
     */
    String getPassWord();

    /**
     * 设置登陆密码
     *
     * @param passWord
     */
    void setPassWord(String passWord);

    /**
     * 获取是否自动登陆
     *
     * @return
     */
    boolean getIsAutoLogin();

    /**
     * 设置是否登陆
     *
     * @param isAutoLogin
     */
    void setIsAutoLogin(boolean isAutoLogin);

    /**
     * 获取是否记住账号
     *
     * @return
     */
    boolean getIsKeepAccount();

    /**
     * 设置是否保存用户账号
     *
     * @param isKeepAccount
     */
    void setIsKeepAccount(boolean isKeepAccount);


    /**
     * 获取上下文对象
     *
     * @return
     */
    Context getContext();

    /**
     * 登陆成功
     *
     * @param result
     */
    void loginSystemSuccess(SystemLoginResultBean result);

    /**
     * 登陆失败
     *
     * @param message
     */
    void loginSystemFail(String message);
}
