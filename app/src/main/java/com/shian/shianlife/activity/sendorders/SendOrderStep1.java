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
import com.shian.shianlife.activity.updata.JBRDataActivity;
import com.shian.shianlife.activity.updata.WSZDataActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataOne;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataTwo;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;
import com.shian.shianlife.provide.result.HrGetSendOrderDataTwo;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.PicUpDataLayoutView;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep1 extends BaseSendOrder {

    Spinner mSPLFuneralHome;
    Spinner mSPFireWay;
    Spinner mSPTrafficWay;

    PicUpDataLayoutView picUpDataLayout1;
    PicUpDataLayoutView picUpDataLayout2;
    PicUpDataLayoutView picUpDataLayout3;
    PicUpDataLayoutView picUpDataLayout4;

    TextView mTVMeetTime;
    MapSelectLayoutView mSelectLayout;

    EditText mETRemark;
    EditText mETFuneralHome;

    HpSaveSendOrderDataTwo params = new HpSaveSendOrderDataTwo();


    public SendOrderStep1(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_1, consultId);
        initView();
        getData();
    }

    private void initView() {
        mSPLFuneralHome = (Spinner) findViewById(R.id.sp_jbr_0);
        mETFuneralHome = (EditText) findViewById(R.id.et_jbr_2);
        mSPTrafficWay = (Spinner) findViewById(R.id.sp_traffic_way);
        mSPFireWay = (Spinner) findViewById(R.id.sp_fireway);
        mTVMeetTime = (TextView) findViewById(R.id.tv_meettime);
        mSelectLayout = (MapSelectLayoutView) findViewById(R.id.mapselect);
        mETRemark = (EditText) findViewById(R.id.et_remark);

        picUpDataLayout1 = (PicUpDataLayoutView) findViewById(R.id.pic_one);
        picUpDataLayout2 = (PicUpDataLayoutView) findViewById(R.id.pic_two);
        picUpDataLayout3 = (PicUpDataLayoutView) findViewById(R.id.pic_three);
        picUpDataLayout4 = (PicUpDataLayoutView) findViewById(R.id.pic_four);

        mTVMeetTime.setOnClickListener(onClickListener);

        picUpDataLayout1.setName("往生者身份证");
        picUpDataLayout2.setName("往生者户口薄");
        picUpDataLayout3.setName("经办人身份证");
        picUpDataLayout4.setName("死亡证明");

        initSp1("其他");
        initSp2("其他");
        initSp3("其他");

        mSelectLayout.setData(1,new ArrayList<String>());
    }


    @Override
    public void saveData() {

        if (picUpDataLayout1.isCheckChecBox()) {
            if (picUpDataLayout1.getUpdataState() == 0) {
                ToastUtils.show(getContext(), "还没有上传逝者身份证或者正在上传中");
                return;
            } else {
                params.setDeadmanCardIdPic(picUpDataLayout1.getPic());
            }
        }
        if (picUpDataLayout2.isCheckChecBox()) {
            if (picUpDataLayout2.getUpdataState() == 0) {
                ToastUtils.show(getContext(), "还没有上传逝者户口薄或者正在上传中");
                return;
            } else {
                params.setDeadmanAccountPic(picUpDataLayout2.getPic());
            }
        }
        if (picUpDataLayout3.isCheckChecBox()) {
            if (picUpDataLayout3.getUpdataState() == 0) {
                ToastUtils.show(getContext(), "还没有上传经办人身份证或者正在上传中");
                return;
            } else {
                params.setAgentmanCardIdPic(picUpDataLayout3.getPic());
            }
        }
        if (picUpDataLayout4.isCheckChecBox()) {
            if (picUpDataLayout4.getUpdataState() == 0) {
                ToastUtils.show(getContext(), "还没有死亡证明或者正在上传中");
                return;
            } else {
                params.setDeadPic(picUpDataLayout4.getPic());
            }
        }

        if (mSPLFuneralHome.getSelectedItemPosition() == 0) {
            if (mETFuneralHome.getText().toString().equals("")) {
                params.setFuneralLocation("其他");
            } else {
                params.setFuneralLocation(mETFuneralHome.getText().toString());
            }
        }
        long meetTime;
        String meetLocation;
        if (!mTVMeetTime.getText().toString().equals("")) {
            meetTime = TransitionDate.StrToDate(mTVMeetTime.getText().toString(), "yyyy-MM-dd").getTime();
        } else {
            ToastUtils.show(getContext(), "还没有设置约定见面时间");
            return;
        }
        if (!mSelectLayout.getLocation().equals("")) {
            meetLocation = mSelectLayout.getLocation();
        } else {
            ToastUtils.show(getContext(), "还没有设置约定见面地址");
            return;
        }
        params.setMeetLocation(meetLocation);
        params.setConsultId(consultId);
        params.setMeetTime(meetTime);
        params.setFirstDayRemark(mETRemark.getText().toString());
        Log.v("this", "ConsultId" + params.getConsultId());
        Log.v("this", "FuneralLocation:" + params.getFuneralLocation());
        Log.v("this", "FireWay:" + params.getFireWay());
        Log.v("this", "Traffice:" + params.getTrafficWay());
        Log.v("this", "MeetTime" + params.getMeetTime());
        Log.v("this", "MeetLocation" + params.getMeetLocation());
        Log.v("this", "DeadmanCardIdPic:" + params.getDeadmanCardIdPic());
        Log.v("this", "DeadmanCardIdPic:" + params.getDeadmanAccountPic());
        Log.v("this", "AgentmanCardIdPic:" + params.getAgentmanCardIdPic());
        Log.v("this", "DeadPic:" + params.getDeadPic());
        Log.v("this", "FirstDayRemark:" + params.getFirstDayRemark());
        MHttpManagerFactory.getAccountManager().saveSendOrderDataTwo(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理治丧第一天服务成功");
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
        MHttpManagerFactory.getAccountManager().getSendOrderDataTwo(context, params,
                new HttpResponseHandler<HrGetSendOrderDataTwo>() {

                    @Override
                    public void onSuccess(HrGetSendOrderDataTwo result) {
                        // TODO Auto-generated method stub
                        Log.v("this", "getFuneralLocation:" + result.getFuneralLocation());


                        Log.v("this", "getFireWay:" + result.getFireWay());
                        Log.v("this", "getTrafficWay:" + result.getTrafficWay());
                        Log.v("this", "getMeetTime:" + result.getMeetTime());
                        Log.v("this", "getMeetLocation:" + result.getMeetLocation());
                        Log.v("this", "getFirstDayRemark:" + result.getFirstDayRemark());

                        Log.v("this", "getDeadmanCardIdPic:" + result.getDeadmanCardIdPic());
                        Log.v("this", "getDeadmanAccountPic:" + result.getDeadmanAccountPic());
                        Log.v("this", "getDeadPic:" + result.getDeadPic());
                        Log.v("this", "getAgentmanCardIdPic:" + result.getAgentmanCardIdPic());


                        Log.v("this", "getDeadLocation:" + result.getDeadLocation());
                        Log.v("this", "getAgentmanLocation:" + result.getAgentmanLocation());
                        Log.v("this", "getDeadmanLocation:" + result.getDeadmanLocation());
                        Log.v("this", "getZsLocation:" + result.getZsLocation());
                        if (result.getFuneralLocation() != null) {
                            mETFuneralHome.setText(result.getFuneralLocation());
                        }
                        if (result.getFireWay() != null) {
                            if (result.getFireWay().equals("其他")) {
                                mSPFireWay.setSelection(0);
                            } else if (result.getFireWay().equals("代办")) {
                                mSPFireWay.setSelection(1);
                            } else if (result.getFireWay().equals("陪同办理")) {
                                mSPFireWay.setSelection(2);
                            }
                        }
                        if (result.getTrafficWay() != null) {
                            Log.v("this", "trafficway!=null");

                            if (result.getTrafficWay().equals("其他")) {
                                Log.v("this", "0");
                                mSPTrafficWay.setSelection(0);
                            } else if (result.getTrafficWay().equals("客户自驾")) {
                                Log.v("this", "1");
                                mSPTrafficWay.setSelection(1);
                            } else if (result.getTrafficWay().equals("公司派车")) {
                                Log.v("this", "2");
                                mSPTrafficWay.setSelection(2);
                            }
                        }
                        if (result.getMeetTime() != 0) {
                            mTVMeetTime.setText(TransitionDate.DateToStr(new Date(result.getMeetTime()), "yyyy-MM-dd HH:ss"));
                        }
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
                        mSelectLayout.setData(1, listData);
                        if (result.getDeadmanCardIdPic() != null) {
                            picUpDataLayout1.setPic(result.getDeadmanCardIdPic());
                            picUpDataLayout1.setCheck(true);
                        }
                        if (result.getDeadmanAccountPic() != null) {
                            picUpDataLayout2.setPic(result.getDeadmanAccountPic());
                            picUpDataLayout2.setCheck(true);
                        }
                        if (result.getAgentmanCardIdPic() != null) {
                            picUpDataLayout3.setPic(result.getAgentmanCardIdPic());
                            picUpDataLayout3.setCheck(true);
                        }
                        if (result.getDeadPic() != null) {
                            picUpDataLayout4.setPic(result.getDeadPic());
                            picUpDataLayout4.setCheck(true);
                        }
                        if (result.getFirstDayRemark() != null) {
                            mETRemark.setText(result.getFirstDayRemark());
                        }
                        if (result.getMeetLocation() != null) {
                            mSelectLayout.setLocation(result.getMeetLocation());
                        }

                        Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                        ArrayList<String> data = new ArrayList<String>();
                        if (result.getZsLocation() != null) {
                            data.add(result.getZsLocation());
                        }
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

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVMeetTime) {
                setBirthdayTime();
            }
        }
    };


    private void initSp1(String i) {
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), R.array.byg_location,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(R.array.byg_location);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPLFuneralHome.setAdapter(province_adapter);
        mSPLFuneralHome.setSelection(Utils.getArrayINdex(arrs, i));
        mSPLFuneralHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                params.setFuneralLocation(arrs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initSp2(String i) {
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), R.array.fire_deal_way,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(R.array.fire_deal_way);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPFireWay.setAdapter(province_adapter);
        mSPFireWay.setSelection(Utils.getArrayINdex(arrs, i));
        mSPFireWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                params.setFireWay(arrs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initSp3(String i) {
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), R.array.traffic_way,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(R.array.traffic_way);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSPTrafficWay.setAdapter(province_adapter);
        mSPTrafficWay.setSelection(Utils.getArrayINdex(arrs, i));
        mSPTrafficWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                params.setTrafficWay(arrs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setBirthdayTime() {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(getContext());
        dialog.setShowHour(true);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {

            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                mTVMeetTime.setText(selectedDate);
            }
        });
    }
}
