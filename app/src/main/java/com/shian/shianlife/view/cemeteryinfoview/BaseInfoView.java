package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/2/8.
 */

public class BaseInfoView extends LinearLayout {
    long beSpeakId = -1;
    long orderId=-1;
    int changeState=-1;

    InfoCallBack infoCallBack;

    public void setInfoCallBack(InfoCallBack infoCallBack) {
        this.infoCallBack = infoCallBack;
    }

    public BaseInfoView(Context context) {
        super(context);
    }

    public BaseInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBeSpeakId(long beSpeakId) {
        this.beSpeakId = beSpeakId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void saveData() {
        infoCallBack.SaveSuccess();
    }

    public void setChangeState(int changeState) {
        this.changeState = changeState;
    }

    public interface InfoCallBack{
        void SaveSuccess();
        void getOrderId(long orderId);
    }
    public void getDataStart(){

    }
}
