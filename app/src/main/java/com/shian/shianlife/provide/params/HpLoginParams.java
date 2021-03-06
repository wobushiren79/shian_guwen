package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpLoginParams extends BaseHttpParams {
    private String username;
    private String password;
    private String channelId;
    private String systemType;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    // @Override
    // public String getHttpParams() {
    // ContentParams<HpLoginParams> pa= new ContentParams<HpLoginParams>();
    // pa.setContent(this);
    // return JSONUtil.writeEntityToJSONString(pa);
    // }

}
