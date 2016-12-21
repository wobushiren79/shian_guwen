package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.util.Log;


import com.shian.shianlife.R;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;
import com.shian.shianlife.view.MapSelectLayoutView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep0 extends BaseSendOrder {


    MapSelectLayoutView mSelectLayoutView;

    long consultId;
    Context context;

    public SendOrderStep0(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_one);
        this.context = context;
        this.consultId = consultId;

        initView();
        getData();
    }

    private void getData() {

        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getSendOrderDataOne(context, params,
                new HttpResponseHandler<HrGetSendOrderDataOne>() {

                    @Override
                    public void onSuccess(HrGetSendOrderDataOne result) {
                        // TODO Auto-generated method stub
                        Log.v("this", "SendOrderStep0 onSuccess");
                        Log.v("this", "Time:" + result.getDeadTime());
                        Log.v("this", "AgentmanLocation:" + result.getAgentmanLocation());
                        Log.v("this", "DeadLocation:" + result.getDeadLocation());
                        Log.v("this", "DeadLocation:" + result.getDeadmanLocation());
                        Log.v("this", "ZsLocation:" + result.getZsLocation());
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void initView() {
        mSelectLayoutView = (MapSelectLayoutView) view.findViewById(R.id.mapselect);
        mSelectLayoutView.setData(1, new ArrayList<String>());
    }


}
