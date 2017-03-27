package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataSix;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataSix;
import com.shian.shianlife.view.writeview.EditTextViewNormal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep5 extends BaseSendOrder {

    HpSaveSendOrderDataSix params = new HpSaveSendOrderDataSix();

    EditTextViewNormal mWriteCarNum;
    EditTextViewNormal mWritePersonNum;

    public SendOrderStep5(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_5, consultId);
        initView();
        getData();
    }

    private void initView() {
        mWriteCarNum = (EditTextViewNormal) findViewById(R.id.write_carnum);
        mWritePersonNum = (EditTextViewNormal) findViewById(R.id.write_personnum);
    }

    @Override
    public void saveData() {
        params.setConsultId(consultId);
        if (mWriteCarNum.getData().equals("")) {
            ToastUtils.show(getContext(), "还没有设置出殡车辆");
            return;
        }

        if (mWritePersonNum.getData().toString().equals("")) {
            ToastUtils.show(getContext(), "还没有设置出殡人数");
            return;
        }
        params.setFuneralCarNum(mWriteCarNum.getData());
        params.setFuneralPersonNum(mWritePersonNum.getData());
        MHttpManagerFactory.getAccountManager().saveSendOrderDataSix(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理开始出殡当天现场服务成功");
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
        MHttpManagerFactory.getAccountManager().getSendOrderDataSix(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataSix>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataSix result) {
                Utils.LogVPrint("getFuneralLocation" + result.getFuneralLocation());
                Utils.LogVPrint("getCrematorName" + result.getCrematorName());
                Utils.LogVPrint("getBodiesPark" + result.getBodiesPark());
                Utils.LogVPrint("getFuneralLocation" + result.getFuneralLocation());
                Utils.LogVPrint("getFireTime" + result.getFireTime());
                Utils.LogVPrint("getBodiesByeTime" + result.getBodiesByeTime());
                Utils.LogVPrint("getFuneralTime" + result.getFuneralTime());
                Utils.LogVPrint("getFuneralLocation" + result.getFuneralLocation());
                Utils.LogVPrint("getFuneralCarNum" + result.getFuneralCarNum());
                Utils.LogVPrint("getFuneralPersonNum" + result.getFuneralPersonNum());


                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                ArrayList<String> data = new ArrayList<String>();
                data.add(result.getFuneralLocation() + "");
                data.add(result.getCrematorName() + "");
                data.add(result.getBodiesPark() + "");
                data.add(TransitionDate.DateToStr(new Date(result.getFireTime()), "yyyy-MM-dd HH:ss"));
                data.add(TransitionDate.DateToStr(new Date(result.getBodiesByeTime()), "yyyy-MM-dd HH:ss"));
                data.add(TransitionDate.DateToStr(new Date(result.getFuneralTime()), "yyyy-MM-dd HH:ss"));
                intent.putStringArrayListExtra("data", data);
                getContext().sendBroadcast(intent);

                if (result.getFuneralPersonNum() != null) {
                    mWritePersonNum.setData(result.getFuneralPersonNum());
                }
                if (result.getFuneralCarNum() != null) {
                    mWriteCarNum.setData(result.getFuneralCarNum());
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
