package com.shian.shianlife.provide.imp;

import android.content.Context;


import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpresult.PHPHrGetAdvertisement;
import com.shian.shianlife.provide.phpresult.PHPHrGetDynamic;
import com.shian.shianlife.provide.phpresult.PHPHrGetHotIssue;
import com.shian.shianlife.provide.phpresult.PHPHrGetSiftListData;
import com.shian.shianlife.provide.phpresult.PHPHrGetVersion;


/**
 * Created by Administrator on 2017/3/4.
 */

public interface PHPManager extends HttpManager {


    /**
     * 获取广告
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getAdvertisement(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetAdvertisement> handler);

    /**
     * 获取重要通知
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getDynamicInfo(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetDynamic> handler);


    /**
     * 获取收藏列表
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSiftListData(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetSiftListData> handler);


    /**
     * 点赞与收藏接口
     *
     * @param context
     * @param params
     */
    public void setSiftData(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler);

    /**
     * 获取热门问题
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getHotIssue(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetHotIssue> handler);


    /**
     * 用户意见接口
     *
     * @param context
     * @param params
     * @param handler
     */
    public void setOpinion(Context context, BaseHttpParams params, HttpResponseHandler<Object> handler, boolean isDialog);


    /**
     * 获取版本号
     * @param context
     * @param params
     * @param handler
     */
    public void getVersion(Context context, BaseHttpParams params, HttpResponseHandler<PHPHrGetVersion> handler, boolean isDialog);
}
