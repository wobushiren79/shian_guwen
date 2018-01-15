package com.shian.shianlife.mvp.download.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnDownLoadDataListener;
import com.shian.shianlife.mvp.download.bean.DownLoadFileBean;
import com.zhy.http.okhttp.request.RequestCall;


/**
 * Created by zm.
 */

public interface IDownLoadFileModel {
    RequestCall startDownLoadFile(Context context, DownLoadFileBean params, OnDownLoadDataListener listener);
}
