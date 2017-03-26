package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataOne;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep0 extends BaseSendOrder {

    MapSelectViewNormal mSelectLayoutView;

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
                        Utils.LogVPrint("Time:" + result.getDeadTime());
                        Utils.LogVPrint("AgentmanLocation:" + result.getAgentmanLocation());
                        Utils.LogVPrint("DeadLocation:" + result.getDeadLocation());
                        Utils.LogVPrint("DeadmanLocation:" + result.getDeadmanLocation());
                        Utils.LogVPrint("ZsLocation:" + result.getZsLocation());

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
                        if (result.getZsLocation() != null) {
                            mSelectLayoutView.setData(result.getZsLocation());
                        }
                        mSelectLayoutView.initAutoTextView(listData);
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
                        Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                        intent.putExtra("finsh", 1);
                        getContext().sendBroadcast(intent);
                    }
                });
    }

    private void initView() {
        mSelectLayoutView = (MapSelectViewNormal) view.findViewById(R.id.write_mapselect_zslocation);

        mSelectLayoutView.setNumView(0);
    }


    @Override
    public void saveData() {
        if (mSelectLayoutView.getData().equals("")) {
            ToastUtils.show(getContext(), "治丧地址不能为空");
            return;
        }
        HpSaveSendOrderDataOne params = new HpSaveSendOrderDataOne();
        params.setConsultId(consultId);
        params.setZsLocation(mSelectLayoutView.getData());
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
