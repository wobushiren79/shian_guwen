package com.shian.shianlife.activity.sendorders;

import android.content.Context;

import com.shian.shianlife.R;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep3 extends BaseSendOrder {
    public SendOrderStep3(Context context,  long consultId) {
        super(context, R.layout.layout_sendorder_3,consultId);
        initView();
    }

    private void initView() {

    }

    @Override
    public void saveData() {
        getData();
    }

    @Override
    public void getData() {

    }


}
