package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataThree;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;

import java.util.ArrayList;
import java.util.Date;

import okhttp3.Request;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep4 extends BaseSendOrder {
    MapSelectViewNormal mapSelectViewNormal;
    HpSaveSendOrderDataFive params = new HpSaveSendOrderDataFive();

    public SendOrderStep4(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_4, consultId);
        initView();
        getData();
    }

    private void initView() {
        mapSelectViewNormal = (MapSelectViewNormal) findViewById(R.id.write_location);
        mapSelectViewNormal.setNumView(0);
    }

    @Override
    public void saveData() {
        params.setConsultId(consultId);
        if (mapSelectViewNormal.getData().equals("")) {
            ToastUtils.show(getContext(), "地址不能为空");
            return;
        }
        params.setTheDayLocation(mapSelectViewNormal.getData());
        MHttpManagerFactory.getFuneralManager().saveSendOrderDataFive(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理开始出殡当天开始服务成功");
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                intent.putExtra("finsh", 0);
                getContext().sendBroadcast(intent);
            }

            @Override
            public void onError(String message) {

            }

        });
    }

    @Override
    public void getData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getFuneralManager().getSendOrderDataFive(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataFive>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataFive result) {
                Utils.LogVPrint("getDeadLocation" + result.getDeadLocation());
                Utils.LogVPrint("getZsLocation" + result.getZsLocation());
                Utils.LogVPrint("getAgentmanLocation" + result.getAgentmanLocation());
                Utils.LogVPrint("getTheDayLocation" + result.getTheDayLocation());
                Utils.LogVPrint("getDeadmanLocation" + result.getDeadmanLocation());

                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                ArrayList<String> data = new ArrayList<String>();
                data.add(result.getZsLocation());
                intent.putStringArrayListExtra("data", data);
                getContext().sendBroadcast(intent);

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
                mapSelectViewNormal.initAutoTextView(listData);
                if (result.getTheDayLocation() != null) {
                    mapSelectViewNormal.setData(result.getTheDayLocation());
                }
            }

            @Override
            public void onError(String message) {
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                intent.putExtra("finsh", 1);
                getContext().sendBroadcast(intent);
            }
        });
    }


}
