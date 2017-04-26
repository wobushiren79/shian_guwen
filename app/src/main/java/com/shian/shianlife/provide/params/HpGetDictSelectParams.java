package com.shian.shianlife.provide.params;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/12.
 */

public class HpGetDictSelectParams extends BaseHttpParams {
    private String dictCode;
    /**
     * 登录通行key
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
}
