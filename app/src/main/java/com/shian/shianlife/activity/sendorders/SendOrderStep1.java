package com.shian.shianlife.activity.sendorders;

import android.content.Context;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep1 extends BaseSendOrder {
    long consultId;
    public SendOrderStep1(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_1);
        this.consultId=consultId;
    }
}
