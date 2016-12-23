package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataSix;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataSix;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep5 extends BaseSendOrder {

    EditText mETCarNum;
    EditText mETPersonNum;
    HpSaveSendOrderDataSix params = new HpSaveSendOrderDataSix();

    @Override
    public void saveData() {
        params.setConsultId(consultId);
        if (mETCarNum.getText().toString().equals("")) {
            ToastUtils.show(getContext(), "还没有设置出殡车辆");
            return;
        }
        params.setFuneralCarNum(mETCarNum.getText().toString());
        if (mETPersonNum.getText().toString().equals("")) {
            ToastUtils.show(getContext(), "还没有设置出殡人数");
            return;
        }
        params.setFuneralCarNum(mETCarNum.getText().toString());
        params.setFuneralPersonNum(mETPersonNum.getText().toString());
        MHttpManagerFactory.getAccountManager().saveSendOrderDataSix(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理开始出殡前服务成功");
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
                Log.v("this", "getFuneralLocation" + result.getFuneralLocation());
                Log.v("this", "getCrematorName" + result.getCrematorName());
                Log.v("this", "getBodiesPark" + result.getBodiesPark());
                Log.v("this", "getFireTime" + result.getFireTime());
                Log.v("this", "getBodiesByeTime" + result.getBodiesByeTime());
                Log.v("this", "getFuneralTime" + result.getFuneralTime());
                Log.v("this", "getFuneralCarNum" + result.getFuneralCarNum());
                Log.v("this", "getFuneralPersonNum" + result.getFuneralPersonNum());


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
                    mETPersonNum.setText(result.getFuneralPersonNum());
                }
                if (result.getFuneralCarNum() != null) {
                    mETCarNum.setText(result.getFuneralCarNum());
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

    public SendOrderStep5(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_5, consultId);


        initView();
        getData();
    }

    private void initView() {
        mETCarNum = (EditText) findViewById(R.id.et_carnum);
        mETPersonNum = (EditText) findViewById(R.id.et_personnum);
    }
}
