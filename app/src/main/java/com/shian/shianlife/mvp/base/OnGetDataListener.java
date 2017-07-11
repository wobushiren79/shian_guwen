package com.shian.shianlife.mvp.base;

/**
 * Created by zm.
 */

public interface OnGetDataListener<T> {
    /**
     * 获取数据成功
     *
     * @param result
     */
    void getDataSuccess(T result);

    /**
     * 获取数据失败
     *
     * @param msg
     */
    void getDataFail(String msg);
}
