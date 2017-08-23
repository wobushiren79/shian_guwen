package com.shian.shianlife.mvp.login.presenter;

/**
 * Created by zm.
 */

public interface IUserLoginPresenter {
    /**
     * 登陸平臺
     */
    void loginSystem();
    /**
     * 退出登陸
     */
    void loginOutSystem();
    /**
     * 保存配置信息
     */
    void saveLoginConfig();

    /**
     * 获取配置信息
     */
    void getLoginConfig();

}
