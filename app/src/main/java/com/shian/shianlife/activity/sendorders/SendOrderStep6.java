package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataSeven;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataSeven;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewEdit;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;

import java.util.ArrayList;

import okhttp3.Request;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep6 extends BaseSendOrder {

    HpSaveSendOrderDataSeven params = new HpSaveSendOrderDataSeven();

    SpinnerViewNormal mWriteAsl;
    EditTextViewNormal mWriteCemeteryName;
    SpinnerViewNormal mWriteDeadmanIdentity;
    SpinnerViewEdit mWriteAgentmanIdentity;

    public SendOrderStep6(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_6, consultId);
        initView();
        getData();
    }

    private void initView() {
        mWriteAsl = (SpinnerViewNormal) findViewById(R.id.write_asl);
        mWriteCemeteryName = (EditTextViewNormal) findViewById(R.id.write_cemeteryname);
        mWriteAgentmanIdentity = (SpinnerViewEdit) findViewById(R.id.write_agentmanidentity);
        mWriteDeadmanIdentity = (SpinnerViewNormal) findViewById(R.id.write_deadmanidentity);

        mWriteAsl.initSpinner(R.array.asl_deal);
        mWriteAgentmanIdentity.initSpinner(R.array.dead_ind);
        mWriteDeadmanIdentity.initSpinner(R.array.dead_ind);
    }

    @Override
    public void saveData() {
        if (mWriteCemeteryName.getData().equals("")) {
            ToastUtils.show(getContext(), "公墓名字还没有设置");
            return;
        }
        if(mWriteAgentmanIdentity.getData().equals("")){
            ToastUtils.show(getContext(), "经办人身份没有设置");
            return;
        }
        params.setAgentmanIdentity(mWriteAgentmanIdentity.getData());
        params.setAshDeal(mWriteAsl.getData());
        params.setDeadmanIdentity(mWriteDeadmanIdentity.getData());
        params.setConsultId(consultId);
        params.setCemeteryName(mWriteCemeteryName.getData());
        MHttpManagerFactory.getFuneralManager().saveSendOrderDataSeven(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理出殡当天殡仪馆服务成功");
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
        MHttpManagerFactory.getFuneralManager().getSendOrderDataSeven(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataSeven>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataSeven result) {
                Utils.LogVPrint("getAshDeal" + result.getAshDeal());
                Utils.LogVPrint("getCemeteryName" + result.getCemeteryName());
                Utils.LogVPrint("getDeadmanIdentity" + result.getDeadmanIdentity());
                Utils.LogVPrint("getAgentmanIdentity" + result.getAgentmanIdentity());
                if (result.getCemeteryName() != null) {
                    mWriteCemeteryName.setData(result.getCemeteryName());
                }
                if (result.getAshDeal() != null) {
                    mWriteAsl.setData(result.getAshDeal());
                }
                if (result.getDeadmanIdentity() != null) {
                    mWriteDeadmanIdentity.setData(result.getDeadmanIdentity());
                }
                if (result.getAgentmanIdentity() != null) {
                    mWriteAgentmanIdentity.setData(result.getAgentmanIdentity());
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
