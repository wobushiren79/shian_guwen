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

import java.util.ArrayList;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep6 extends BaseSendOrder {
    Spinner mSPASL;
    Spinner mSPDeadmanIdentity;
    Spinner mSPAgentmanIdentity;
    EditText mETName;
    EditText mETAgentmanID;

    HpSaveSendOrderDataSeven params = new HpSaveSendOrderDataSeven();

    @Override
    public void saveData() {
        if (mETName.getText().toString().equals("")) {
            ToastUtils.show(getContext(), "公墓名字还没有设置");
            return;
        }
        if (mSPAgentmanIdentity.getSelectedItemPosition() == 0) {
            if (mETAgentmanID.getText().toString().equals("")) {
                params.setAgentmanIdentity("其他");
            } else {
                params.setAgentmanIdentity(mETAgentmanID.getText().toString());
            }
        }
        params.setConsultId(consultId);
        params.setCemeteryName(mETName.getText().toString());
        MHttpManagerFactory.getAccountManager().saveSendOrderDataSeven(getContext(), params, new HttpResponseHandler<Object>() {
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
        MHttpManagerFactory.getAccountManager().getSendOrderDataSeven(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataSeven>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataSeven result) {
                Log.v("this", "getAshDeal" + result.getAshDeal());
                Log.v("this", "getCemeteryName" + result.getCemeteryName());
                Log.v("this", "getDeadmanIdentity" + result.getDeadmanIdentity());
                Log.v("this", "getAgentmanIdentity" + result.getAgentmanIdentity());

                if (result.getCemeteryName() != null) {
                    mETName.setText(result.getCemeteryName());
                }
                if (result.getAshDeal() != null) {
                    if (result.getAshDeal().equals("其他")) {
                        mSPASL.setSelection(0);
                    } else if (result.getAshDeal().equals("寄存")) {
                        mSPASL.setSelection(1);
                    } else if (result.getAshDeal().equals("安葬")) {
                        mSPASL.setSelection(2);
                    }
                }
                if (result.getDeadmanIdentity() != null) {
                    if (result.getDeadmanIdentity().equals("其他")) {
                        mSPDeadmanIdentity.setSelection(0);
                    } else if (result.getDeadmanIdentity().equals("政府")) {
                        mSPDeadmanIdentity.setSelection(1);
                    } else if (result.getDeadmanIdentity().equals("商界")) {
                        mSPDeadmanIdentity.setSelection(2);
                    } else if (result.getDeadmanIdentity().equals("一般职工")) {
                        mSPDeadmanIdentity.setSelection(3);
                    }
                }
                if (result.getAgentmanIdentity() != null) {
                    if (result.getAgentmanIdentity().equals("其他")) {
                        mSPAgentmanIdentity.setSelection(0);
                    } else if (result.getAgentmanIdentity().equals("政府")) {
                        mSPAgentmanIdentity.setSelection(1);
                    } else if (result.getAgentmanIdentity().equals("商界")) {
                        mSPAgentmanIdentity.setSelection(2);
                    } else if (result.getAgentmanIdentity().equals("一般职工")) {
                        mSPAgentmanIdentity.setSelection(3);
                    } else {
                        mETAgentmanID.setText(result.getAgentmanIdentity());
                    }
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

    public SendOrderStep6(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_6, consultId);

        initView();
        getData();
    }

    private void initView() {
        mSPASL = (Spinner) findViewById(R.id.sp_jbr_0);
        mSPDeadmanIdentity = (Spinner) findViewById(R.id.sp_jbr_1);
        mSPAgentmanIdentity = (Spinner) findViewById(R.id.sp_jbr_2);
        mETAgentmanID = (EditText) findViewById(R.id.et_agentmanid);
        mETName = (EditText) findViewById(R.id.et_name);

        initSp("其他",0);
        initSp("其他",1);
        initSp("其他",2);
    }


    private void initSp(String i, final int type) {
        int array;
        Spinner spinner;
        if (type == 0) {
            array = R.array.asl_deal;
            spinner = mSPASL;
        } else {
            array = R.array.dead_ind;
            if (type == 1) {
                spinner = mSPDeadmanIdentity;
            } else {
                spinner = mSPAgentmanIdentity;
            }
        }
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), array,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(array);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(province_adapter);
        spinner.setSelection(Utils.getArrayINdex(arrs, i));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (type == 0) {
                    params.setAshDeal(arrs[position]);
                } else if (type == 1) {
                    params.setDeadmanIdentity(arrs[position]);
                } else if (type == 2) {
                    params.setAgentmanIdentity(arrs[position]);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
