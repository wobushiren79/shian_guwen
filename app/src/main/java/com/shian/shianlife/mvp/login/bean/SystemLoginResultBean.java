package com.shian.shianlife.mvp.login.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class SystemLoginResultBean {
    private String sessionId;//会话id
    private Long userId;//用户id
    private List<String> resourceCodes;//资源权限集
    private UserObject userObj;//用户基本资料

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getResourceCodes() {
        return resourceCodes;
    }

    public void setResourceCodes(List<String> resourceCodes) {
        this.resourceCodes = resourceCodes;
    }

    public UserObject getUserObj() {
        return userObj;
    }

    public void setUserObj(UserObject userObj) {
        this.userObj = userObj;
    }

    public static class UserObject {
        private String name;//	用户名
        private String phone;//	手机号
        private String email;//	e-mail

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
