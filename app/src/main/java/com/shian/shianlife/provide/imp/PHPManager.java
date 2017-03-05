package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpresult.PHPHrGetLoginAdvertisement;


/**
 * Created by Administrator on 2017/3/4.
 */

public interface PHPManager extends HttpManager {
    /**
     * 获取登录页广告
     * @param context
     * @param handler
     */
    public void loginAdvertisement(Context context,HttpResponseHandler<PHPHrGetLoginAdvertisement> handler);

    /**
     * 获取首页广告
     * @param context
     * @param handler
     */
    public void mainAdvertisement(Context context,HttpResponseHandler<PHPHrGetLoginAdvertisement> handler);

}
