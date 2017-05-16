package com.shian.shianlife.view.cemeteryinfoview.infolayout;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/12.
 */

public abstract class BaseCemeteryInfo extends LinearLayout {
    long orderId = -1;
    long beSpeakId = -1;
    boolean isShowMode = false;
    View view;
    CallBack callBack;

    public BaseCemeteryInfo(Context context, long orderId, long beSpeakId, int layoutId, boolean isShowMode) {
        super(context);
        this.orderId = orderId;
        this.beSpeakId = beSpeakId;
        this.isShowMode = isShowMode;
        view = View.inflate(context, layoutId, this);

        initView();
        initData();
        getData();
    }


    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 獲取數據
     */
    abstract void getData();

    /**
     * 保存數據
     */
    abstract void saveData();

    /**
     * 初始化控件
     */
    abstract void initView();

    /**
     * 初始化數據
     */
    abstract void initData();

    public interface CallBack {
        void next(BaseCemeteryInfo view);
    }
}
