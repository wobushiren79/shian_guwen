package com.shian.shianlife.mvp.login.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class DuiBaLoginBean extends BaseHttpParams {
    private Long uid;
    private Integer credits;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
