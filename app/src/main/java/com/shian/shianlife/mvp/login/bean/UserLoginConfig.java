package com.shian.shianlife.mvp.login.bean;

/**
 * Created by zm.
 */

public class UserLoginConfig {
    boolean isAutoLogin;
    boolean isKeepAccount;
    String userName;
    String passWord;

    public boolean isAutoLogin() {
        return isAutoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        isAutoLogin = autoLogin;
    }

    public boolean isKeepAccount() {
        return isKeepAccount;
    }

    public void setKeepAccount(boolean keepAccount) {
        isKeepAccount = keepAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
