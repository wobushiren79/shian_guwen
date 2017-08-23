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
import com.shian.shianlife.provide.params.HpSaveSendOrderDataThree;
import com.shian.shianlife.provide.params.HpSaveTime;
import com.shian.shianlife.provide.result.HrGetSendOrderDataThree;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Request;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep2 extends BaseSendOrder {
    HpSaveSendOrderDataThree params = new HpSaveSendOrderDataThree();

    MapSelectViewNormal mWriteLocation;

    public SendOrderStep2(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_2, consultId);
        initView();
    }

    @Override
    public void saveData() {

        if (mWriteLocation.getData().equals("")) {
            ToastUtils.show(getContext(), "当前地址不能为空");
            return;
        }
        params.setConsultId(consultId);
        params.setAfterLocation(mWriteLocation.getData());
        MHttpManagerFactory.getFuneralManager().saveSendOrderDataThree(getContext(), params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理出殡前开始服务成功");
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
        MHttpManagerFactory.getFuneralManager().getSendOrderDataThree(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataThree>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataThree result) {
                Utils.LogVPrint("getFuneralLocation" + result.getFuneralLocation());
                Utils.LogVPrint("getFireWay" + result.getFireWay());
                Utils.LogVPrint("getTrafficWay" + result.getTrafficWay());
                Utils.LogVPrint("getMeetTime" + result.getMeetTime());
                Utils.LogVPrint("getMeetLocation" + result.getMeetLocation());
                Utils.LogVPrint("getProcedureName" + result.getProcedureName());
                Utils.LogVPrint("getFirstDayRemark" + result.getFirstDayRemark());
                Utils.LogVPrint("getAfterLocation" + result.getAfterLocation());
                Utils.LogVPrint("getDeadLocation" + result.getDeadLocation());
                Utils.LogVPrint("getFuneralLocation" + result.getFuneralLocation());
                Utils.LogVPrint("getZsLocation" + result.getZsLocation());

                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                ArrayList<String> data = new ArrayList<String>();
                data.add(result.getFuneralLocation());
                data.add(result.getFireWay());
                data.add(result.getTrafficWay());
                data.add(result.getMeetTime());
                data.add(result.getMeetLocation());
                data.add(result.getProcedureName());
                data.add(result.getFirstDayRemark());
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
                mWriteLocation.initAutoTextView(listData);
                if (result.getAfterLocation() != null) {
                    mWriteLocation.setData(result.getAfterLocation());
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


    private void initView() {
        mWriteLocation = (MapSelectViewNormal) findViewById(R.id.write_location);
        mWriteLocation.setNumView(0);
        getData();

    }
}
