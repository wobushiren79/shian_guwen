package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataFour;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFour;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewEdit;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep3 extends BaseSendOrder {
    SpinnerViewNormal mWriteCremator;
    EditTextViewNormal mWriteServiceWindows;
    SpinnerViewEdit mWriteBodiespark;
    TimeSelectViewNormal mWriteBodiesByeTime;
    TimeSelectViewNormal mWriteFuneralTime;
    TimeSelectViewNormal mWriteFireTime;
    EditTextViewNormal mWriteRemark;


    HpSaveSendOrderDataFour params = new HpSaveSendOrderDataFour();

    public SendOrderStep3(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_3, consultId);
        initView();

    }

    private void initView() {
        mWriteCremator = (SpinnerViewNormal) findViewById(R.id.write_cremator);
        mWriteServiceWindows = (EditTextViewNormal) findViewById(R.id.write_service_window);
        mWriteBodiespark = (SpinnerViewEdit) findViewById(R.id.write_body_park);

        mWriteFireTime = (TimeSelectViewNormal) findViewById(R.id.write_fire_timeselect);
        mWriteBodiesByeTime = (TimeSelectViewNormal) findViewById(R.id.write_bye_timeselect);
        mWriteFuneralTime = (TimeSelectViewNormal) findViewById(R.id.write_out_timeselect);
        mWriteRemark = (EditTextViewNormal) findViewById(R.id.write_remark);

        mWriteCremator.initSpinner(R.array.fire_cremator);
        mWriteBodiespark.initSpinner(R.array.bodies_park);
        getData();
    }

    @Override
    public void saveData() {
        params.setConsultId(consultId);
        if (!mWriteFireTime.getData().equals("")) {
            params.setFireTime(TransitionDate.StrToDate(mWriteFireTime.getData(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "火化时间没有设置");
            return;
        }

        if (!mWriteBodiesByeTime.getData().equals("")) {
            params.setBodiesByeTime(TransitionDate.StrToDate(mWriteBodiesByeTime.getData(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "遗体告别仪式时间没有设置");
            return;
        }

        if (!mWriteFuneralTime.getData().equals("")) {
            params.setFuneralTime(TransitionDate.StrToDate(mWriteFuneralTime.getData(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "出殡时间没有设置");
            return;
        }

        if (!mWriteBodiespark.getData().equals("")) {
            params.setBodiesPark(mWriteBodiespark.getData());
        } else {
            ToastUtils.show(getContext(), "遗体停放还没有填写");
            return;
        }
        params.setServiceWindows(mWriteServiceWindows.getData());
        params.setCrematorName(mWriteCremator.getData());
        params.setFuneralRemark(mWriteRemark.getData());


        MHttpManagerFactory.getAccountManager().saveSendOrderDataFour(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理出殡前服务成功");
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
        MHttpManagerFactory.getAccountManager().getSendOrderDataFour(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataFour>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataFour result) {
                Utils.LogVPrint("getZsLocation:" + result.getZsLocation());
                Utils.LogVPrint("getCrematorName:" + result.getCrematorName());
                Utils.LogVPrint("getServiceWindows:" + result.getServiceWindows());
                Utils.LogVPrint("getBodiesPark:" + result.getBodiesPark());
                Utils.LogVPrint("getBodiesParkName:" + result.getBodiesParkName());
                Utils.LogVPrint("getFireTime:" + result.getFireTime());
                Utils.LogVPrint("getBodiesByeTime:" + result.getBodiesByeTime());
                Utils.LogVPrint("getFuneralTime:" + result.getFuneralTime());
                Utils.LogVPrint("getFuneralRemark:" + result.getFuneralRemark());

                if(result.getCrematorName()!=null){
                    mWriteCremator.setData(result.getCrematorName());
                }
                if (result.getServiceWindows() != null) {
                    mWriteServiceWindows.setData(result.getServiceWindows());
                }
                if(result.getBodiesPark()!=null){
                    mWriteBodiespark.setData(result.getBodiesPark());
                }
                if (result.getFireTime() != null) {
                    mWriteFireTime.setData(result.getFireTime());
                }
                if (result.getFuneralTime() != null) {
                    mWriteFuneralTime.setData(result.getFuneralTime());
                }
                if (result.getBodiesByeTime() != null) {
                    mWriteBodiesByeTime.setData(result.getBodiesByeTime());
                }
                if (result.getFuneralRemark() != null) {
                    mWriteRemark.setData(result.getFuneralRemark());
                }
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                ArrayList<String> data = new ArrayList<String>();
                data.add(result.getZsLocation());
                intent.putStringArrayListExtra("data", data);
                getContext().sendBroadcast(intent);
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
