package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/2/8.
 */

public class BaseInfoView extends LinearLayout {
    long beSpeakId = -1;
    int changeState=-1;
    public BaseInfoView(Context context) {
        super(context);
    }

    public BaseInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBeSpeakId(long beSpeakId) {
        this.beSpeakId = beSpeakId;
    }

    public void saveData() {

    }

    public void setChangeState(int changeState) {
        this.changeState = changeState;
    }
}
