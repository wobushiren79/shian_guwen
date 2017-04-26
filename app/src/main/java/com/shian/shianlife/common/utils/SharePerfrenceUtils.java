package com.shian.shianlife.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import static android.content.Context.MODE_PRIVATE;

public class SharePerfrenceUtils {
    private static final String C_sShare_Login_F = "Login_Share_f";
    private static final String C_sShareLogin_username = "share_username";
    private static final String C_sShareLogin_password = "share_password";
    private static final String C_sShareLogin_isAutoLogin = "share_isAutoLogin";
    private static final String C_sShareLogin_isRemeberPassword = "share_isRePassword";
    private static final String C_sShareLogin_channelId = "share_channelId";
    //新添加登录账号类型
    private static final String C_sShareLogin_Type = "share_type";

    public static void setLoginShare(Context c, String username,
                                     String password, boolean isRemeber, boolean isAuto, int loginType) {
        Editor editor = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putString(C_sShareLogin_username, username);
        editor.putString(C_sShareLogin_password, password);
        editor.putBoolean(C_sShareLogin_isRemeberPassword, isRemeber);
        editor.putBoolean(C_sShareLogin_isAutoLogin, isAuto);

        editor.putInt(C_sShareLogin_Type, loginType);//新添加登录账号类型
        editor.commit();
    }

    public static void setShareAutoLogin(Context c, boolean isAuto) {
        Editor editor = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putBoolean(C_sShareLogin_isAutoLogin, isAuto);
        editor.commit();
    }

    public static ShareLogin getLoginShare(Context c) {
        SharedPreferences share = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        String username = share.getString(C_sShareLogin_username, "");
        String password = share.getString(C_sShareLogin_password, "");
        boolean isRember = share.getBoolean(C_sShareLogin_isRemeberPassword,
                false);
        boolean isAuto = share.getBoolean(C_sShareLogin_isAutoLogin, false);
        int loginType = share.getInt(C_sShareLogin_Type, MODE_PRIVATE);//新添加登录账号类型
        ShareLogin loginS = new ShareLogin();
        loginS.setUsername(username);
        loginS.setPassword(password);
        loginS.setRemeberPassword(isRember);
        loginS.setAutoLogin(isAuto);
        loginS.setLoginType(loginType);//新添加登录账号类型
        return loginS;
    }



    public static void setShareChannelId(Context c, String channelId) {
        Editor editor = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE).edit();
        editor.putString(C_sShareLogin_channelId, channelId);
        editor.commit();
    }

    public static String getShareChannelId(Context c) {
        SharedPreferences share = c.getSharedPreferences(C_sShare_Login_F, MODE_PRIVATE);
        return share.getString(C_sShareLogin_channelId, "1");
    }

    public static class ShareLogin {
        private String username;
        private String password;
        private boolean isAutoLogin;
        private boolean isRemeberPassword;
        private int loginType;//新增登录类型

        public int getLoginType() {
            return loginType;
        }

        public void setLoginType(int loginType) {
            this.loginType = loginType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isAutoLogin() {
            return isAutoLogin;
        }

        public void setAutoLogin(boolean isAutoLogin) {
            this.isAutoLogin = isAutoLogin;
        }

        public boolean isRemeberPassword() {
            return isRemeberPassword;
        }

        public void setRemeberPassword(boolean isRemeberPassword) {
            this.isRemeberPassword = isRemeberPassword;
        }

    }
}
