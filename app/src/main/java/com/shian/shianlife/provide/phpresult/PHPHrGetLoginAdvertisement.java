package com.shian.shianlife.provide.phpresult;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PHPHrGetLoginAdvertisement extends BaseHttpParams {
    private String banner;
    private String url;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
