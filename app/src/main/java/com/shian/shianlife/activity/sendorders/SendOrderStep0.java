package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataOne;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;
import com.shian.shianlife.view.MapSelectLayoutView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep0 extends BaseSendOrder {


    MapSelectLayoutView mSelectLayoutView;


    public SendOrderStep0(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_0, consultId);
        initView();
        getData();
    }

    @Override
    public void getData() {
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

                        if (result.getAgentmanLocation() != null) {
                            listData.add(result.getAgentmanLocation());
                        }
                        if (result.getDeadLocation() != null) {
                            listData.add(result.getDeadLocation());
                        }
                        if (result.getDeadmanLocation() != null) {
                            listData.add(result.getDeadmanLocation());
                        }
                        if (result.getZsLocation() != null) {
                            listData.add(result.getZsLocation());
                        }
                        mSelectLayoutView.setData(1, listData);
                        if (result.getZsLocation() != null) {
                            mSelectLayoutView.setLocation(result.getZsLocation());
                        }
                        String Time = TransitionDate.DateToStr(new Date(result.getDeadTime()), "yyyy-MM-dd");
                        Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                        ArrayList<String> data = new ArrayList<String>();
                        data.add(Time + "");
                        data.add(result.getDeadLocation() + "");

                        intent.putStringArrayListExtra("data", data);
                        getContext().sendBroadcast(intent);
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
    }


    @Override
    public void saveData() {
        if (mSelectLayoutView.getLocation().equals("")) {
            ToastUtils.show(getContext(), "治丧地址不能为空");
            return;
        }
        HpSaveSendOrderDataOne params = new HpSaveSendOrderDataOne();
        params.setConsultId(consultId);
        params.setZsLocation(mSelectLayoutView.getLocation());
        MHttpManagerFactory.getAccountManager().saveSendOrderDataOne(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理临终现场成功");
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                intent.putExtra("finsh", 0);
                getContext().sendBroadcast(intent);
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
