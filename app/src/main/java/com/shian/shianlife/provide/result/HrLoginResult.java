package com.shian.shianlife.provide.result;

import java.util.ArrayList;
import java.util.List;

public class HrLoginResult {
    private String sessionId;
    private String lastAccessTime;
    private String startTimestamp;
    private ArrayList<Integer> roleIds;
    private List<String> permitionCodes;
    private long userId;


    private SysPlatAccount userData;
    /**
     * 登录通行key
     */
    private String token;

    public SysPlatAccount getUserData() {
        return userData;
    }

    public void setUserData(SysPlatAccount userData) {
        this.userData = userData;
    }

    public List<String> getPermitionCodes() {
        return permitionCodes;
    }

    public void setPermitionCodes(List<String> permitionCodes) {
        this.permitionCodes = permitionCodes;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(ArrayList<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(String lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(String startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class SysPlatAccount {
        private String name;
        private String mobile;
        private String email;
        private String qq;
        private Integer sex;
        private String loginName;
        private String loginPasswd;
        private String token;
        private Integer accountType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getLoginPasswd() {
            return loginPasswd;
        }

        public void setLoginPasswd(String loginPasswd) {
            this.loginPasswd = loginPasswd;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Integer getAccountType() {
            return accountType;
        }

        public void setAccountType(Integer accountType) {
            this.accountType = accountType;
        }
    }
}
