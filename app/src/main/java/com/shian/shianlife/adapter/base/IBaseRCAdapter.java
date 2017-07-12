package com.shian.shianlife.adapter.base;

import java.util.List;

/**
 * Created by zm.
 */

public interface IBaseRCAdapter<T> {
    List<T> setData(List<T> mDatas);

    List<T> setData(T[] mDatas);

    List<T> addData(List<T> mDatas);

    List<T> addData(T[] mDatas);

    List<T> removeData(List<T> mDatas);

    List<T> removeData(T[] mDatas);
}
