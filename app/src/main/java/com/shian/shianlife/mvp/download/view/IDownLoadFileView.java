package com.shian.shianlife.mvp.download.view;


import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.download.bean.DownLoadFileResultBean;

/**
 * Created by zm.
 */

public interface IDownLoadFileView extends BaseMVPView {
    /**
     * 获取下载地址
     *
     * @return
     */
    String getDownLoadFileUrl();

    /**
     * 获取下载文件名称
     *
     * @return
     */
    String getDownLoadFileName();


    /**
     * 下载文件成功
     *
     * @param resultBean
     */
    void downloadSuccess(DownLoadFileResultBean resultBean);


    /**
     * 下载文件失败
     *
     * @param msg
     */
    void downloadFail(String msg);

    /**
     * 下载进度
     *
     * @param total    总进度
     * @param progress 已进行进度
     */
    void downloadInProgress(long total, float progress);
}
