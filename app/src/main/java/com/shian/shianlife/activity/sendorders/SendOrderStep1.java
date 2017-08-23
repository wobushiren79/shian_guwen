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
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewEdit;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2016/12/21.
 */

public class SendOrderStep1 extends BaseSendOrder {
    PicUpDataLayoutView picUpDataLayout1;
    PicUpDataLayoutView picUpDataLayout2;
    PicUpDataLayoutView picUpDataLayout3;
    PicUpDataLayoutView picUpDataLayout4;

    HpSaveSendOrderDataTwo params = new HpSaveSendOrderDataTwo();

    SpinnerViewEdit mWriteFuneralLocatiaon;
    SpinnerViewNormal mWriteFireWay;
    SpinnerViewNormal mWriteTrafficWay;
    TimeSelectViewNormal mWriteTimeSelect;
    MapSelectViewNormal mWriteMapSelect;
    EditTextViewNormal mWriteRemark;

    public SendOrderStep1(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_1, consultId);
        initView();
        getData();
    }

    private void initView() {
        mWriteFuneralLocatiaon = (SpinnerViewEdit) findViewById(R.id.write_spinner_edit);
        mWriteFireWay = (SpinnerViewNormal) findViewById(R.id.write_spinner_fire);
        mWriteTrafficWay = (SpinnerViewNormal) findViewById(R.id.write_spinner_traffic);
        mWriteTimeSelect = (TimeSelectViewNormal) findViewById(R.id.write_timeselect);
        mWriteMapSelect = (MapSelectViewNormal) findViewById(R.id.write_mapselect_plan);
        mWriteRemark = (EditTextViewNormal) findViewById(R.id.write_remark);

        picUpDataLayout1 = (PicUpDataLayoutView) findViewById(R.id.pic_one);
        picUpDataLayout2 = (PicUpDataLayoutView) findViewById(R.id.pic_two);
        picUpDataLayout3 = (PicUpDataLayoutView) findViewById(R.id.pic_three);
        picUpDataLayout4 = (PicUpDataLayoutView) findViewById(R.id.pic_four);

        picUpDataLayout1.setName("往生者身份证");
        picUpDataLayout2.setName("往生者户口薄");
        picUpDataLayout3.setName("经办人身份证");
        picUpDataLayout4.setName("死亡证明");

        mWriteFuneralLocatiaon.initSpinner(R.array.byg_location);
        mWriteFireWay.initSpinner(R.array.fire_deal_way);
        mWriteTrafficWay.initSpinner(R.array.traffic_way);

        mWriteMapSelect.setNumView(0);
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
        if (!mWriteFuneralLocatiaon.getData().equals("")) {
            params.setFuneralLocation(mWriteFuneralLocatiaon.getData());
        } else {
            ToastUtils.show(getContext(), "还没有设置殡仪馆地址");
            return;
        }
        long meetTime;
        String meetLocation;
        if (!mWriteTimeSelect.getData().equals("")) {
            meetTime = TransitionDate.StrToDate(mWriteTimeSelect.getData(), "yyyy-MM-dd").getTime();
        } else {
            ToastUtils.show(getContext(), "还没有设置约定见面时间");
            return;
        }
        if (!mWriteMapSelect.getData().equals("")) {
            meetLocation = mWriteMapSelect.getData();
        } else {
            ToastUtils.show(getContext(), "还没有设置约定见面地址");
            return;
        }
        params.setMeetLocation(meetLocation);
        params.setConsultId(consultId);
        params.setMeetTime(meetTime);
        params.setFirstDayRemark(mWriteRemark.getData());
        params.setFireWay(mWriteFireWay.getData());
        params.setTrafficWay(mWriteTrafficWay.getData());
        Utils.LogVPrint("ConsultId" + params.getConsultId());
        Utils.LogVPrint("FuneralLocation:" + params.getFuneralLocation());
        Utils.LogVPrint("FireWay:" + params.getFireWay());
        Utils.LogVPrint("Traffice:" + params.getTrafficWay());
        Utils.LogVPrint("MeetTime" + params.getMeetTime());
        Utils.LogVPrint("MeetLocation" + params.getMeetLocation());
        Utils.LogVPrint("DeadmanCardIdPic:" + params.getDeadmanCardIdPic());
        Utils.LogVPrint("DeadmanAccountPic:" + params.getDeadmanAccountPic());
        Utils.LogVPrint("AgentmanCardIdPic:" + params.getAgentmanCardIdPic());
        Utils.LogVPrint("DeadPic:" + params.getDeadPic());
        Utils.LogVPrint("FirstDayRemark:" + params.getFirstDayRemark());

        MHttpManagerFactory.getFuneralManager().saveSendOrderDataTwo(getContext(), params, new HttpResponseHandler<Object>() {

            @Override
            public void onStart(Request request, int id) {

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
        MHttpManagerFactory.getFuneralManager().getSendOrderDataTwo(context, params,
                new HttpResponseHandler<HrGetSendOrderDataTwo>() {

                    @Override
                    public void onStart(Request request, int id) {

                    }

                    @Override
                    public void onSuccess(HrGetSendOrderDataTwo result) {
                        // TODO Auto-generated method stub
                        Utils.LogVPrint("getFuneralLocation:" + result.getFuneralLocation());
                        Utils.LogVPrint("getFireWay:" + result.getFireWay());
                        Utils.LogVPrint("getTrafficWay:" + result.getTrafficWay());
                        Utils.LogVPrint("getMeetTime:" + result.getMeetTime());
                        Utils.LogVPrint("getMeetLocation:" + result.getMeetLocation());
                        Utils.LogVPrint("getFirstDayRemark:" + result.getFirstDayRemark());
                        Utils.LogVPrint("getDeadmanCardIdPic:" + result.getDeadmanCardIdPic());
                        Utils.LogVPrint("getDeadmanAccountPic:" + result.getDeadmanAccountPic());
                        Utils.LogVPrint("getDeadPic:" + result.getDeadPic());
                        Utils.LogVPrint("getAgentmanCardIdPic:" + result.getAgentmanCardIdPic());
                        Utils.LogVPrint("getDeadLocation:" + result.getDeadLocation());
                        Utils.LogVPrint("getAgentmanLocation:" + result.getAgentmanLocation());
                        Utils.LogVPrint("getDeadmanLocation:" + result.getDeadmanLocation());
                        Utils.LogVPrint("getZsLocation:" + result.getZsLocation());
                        if (result.getFuneralLocation() != null) {
                            mWriteFuneralLocatiaon.setData(result.getFuneralLocation());
                        }
                        if (result.getFireWay() != null) {
                            mWriteFireWay.setData(result.getFireWay());
                        }
                        if (result.getTrafficWay() != null) {
                            mWriteFireWay.setData(result.getTrafficWay());
                        }
                        if (result.getMeetTime() != null) {
                            mWriteTimeSelect.setData(result.getMeetTime());
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
                        mWriteMapSelect.initAutoTextView(listData);
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
                            mWriteRemark.setData(result.getFirstDayRemark());
                        }
                        if (result.getMeetLocation() != null) {
                            mWriteMapSelect.setData(result.getMeetLocation());
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
                    public void onError(String message) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                        intent.putExtra("finsh", 1);
                        getContext().sendBroadcast(intent);
                    }
                });
    }

}
