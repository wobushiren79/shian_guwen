package com.shian.shianlife.mvp.base;

/**
 * Created by zm.
 */

public interface OnDownLoadDataListener<T> extends OnGetDataListener<T> {
    /**
     * 下载进度
     * @param total 总进度
     * @param progress 已进行进度
     */
    void downloadInProgress(long total, float progress);
}
