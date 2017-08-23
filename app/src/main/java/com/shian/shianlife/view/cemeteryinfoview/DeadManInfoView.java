package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessTwo;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessTwo;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DeadManInfoView extends BaseInfoView implements CetemeryTextSelectLayoutView.onSelectedListener {
    private View view;

    CetemeryTextSelectLayoutView mSelectDeadManSex1;
    CetemeryTextSelectLayoutView mSelectDeadManState1;

    EditText mETDeadManName1;
    EditText mETDeadManAge1;
    EditText mETDeadManCardId1;
    TextView mTVDeadManTime1;

    CetemeryTextSelectLayoutView mSelectDeadManSex2;
    CetemeryTextSelectLayoutView mSelectDeadManState2;

    EditText mETDeadManName2;
    EditText mETDeadManAge2;
    EditText mETDeadManCardId2;
    TextView mTVDeadManTime2;

    EditText mETRemark;

    List<String> stateList = new ArrayList<>();
    List<String> sexList = new ArrayList<>();

    public DeadManInfoView(Context context) {
        this(context, null);
    }

    public DeadManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_deadman_info, this);

        initData();
        initView();
    }

    @Override
    public void getDataStart() {
        super.getDataStart();
        getData();
    }

    private void getData() {
        if (beSpeakId == -1) {
            return;
        }
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
        MHttpManagerFactory.getCemeteryManager().getCemeteryTalkSuccessTwo(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessTwo>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessTwo result) {


                Utils.LogVPrint("getDeadmanOneName:" + result.getDeadmanOneName());
                Utils.LogVPrint("getDeadmanOneAge:" + result.getDeadmanOneAge());
                Utils.LogVPrint("getDeadmanOneSex:" + result.getDeadmanOneSex());
                Utils.LogVPrint("getDeadmanOneState:" + result.getDeadmanOneState());
                Utils.LogVPrint("getDeadmanOneCardId:" + result.getDeadmanOneCardId());
                Utils.LogVPrint("getDeadmanOneDeadTime:" + result.getDeadmanOneDeadTime());

                Utils.LogVPrint("getDeadmanTwoName:" + result.getDeadmanTwoName());
                Utils.LogVPrint("getDeadmanTwoAge:" + result.getDeadmanTwoAge());
                Utils.LogVPrint("getDeadmanTwoSex:" + result.getDeadmanTwoSex());
                Utils.LogVPrint("getDeadmanTwoState:" + result.getDeadmanTwoState());
                Utils.LogVPrint("getDeadmanTwoCardId:" + result.getDeadmanTwoCardId());
                Utils.LogVPrint("getDeadmanTwoDeadTime:" + result.getDeadmanTwoDeadTime());

                Utils.LogVPrint("getRemark" + result.getRemark());

                if (result != null) {

                    if (result.getDeadmanOneName() != null) {
                        mETDeadManName1.setText(result.getDeadmanOneName());
                    }
                    if (result.getDeadmanOneAge() != null) {
                        mETDeadManAge1.setText(result.getDeadmanOneAge());
                    }
                    if (result.getDeadmanOneSex() != null) {
                        mSelectDeadManSex1.setString(result.getDeadmanOneSex());
                    }
                    if (result.getDeadmanOneState() != null) {
                        mSelectDeadManState1.setString(result.getDeadmanOneState());
                    }
                    if (result.getDeadmanOneCardId() != null) {
                        mETDeadManCardId1.setText(result.getDeadmanOneCardId());
                    }
                    if (result.getDeadmanOneDeadTime() != null) {
                        mTVDeadManTime1.setText(result.getDeadmanOneDeadTime());
                    }
                    if (result.getDeadmanTwoName() != null) {
                        mETDeadManName2.setText(result.getDeadmanTwoName());
                    }
                    if (result.getDeadmanTwoAge() != null) {
                        mETDeadManAge2.setText(result.getDeadmanTwoAge());
                    }
                    if (result.getDeadmanTwoSex() != null) {
                        mSelectDeadManSex2.setString(result.getDeadmanTwoSex());
                    }
                    if (result.getDeadmanTwoState() != null) {
                        mSelectDeadManState2.setString(result.getDeadmanTwoState());
                    }
                    if (result.getDeadmanTwoCardId() != null) {
                        mETDeadManCardId2.setText(result.getDeadmanTwoCardId());
                    }
                    if (result.getDeadmanTwoDeadTime() != null) {
                        mTVDeadManTime2.setText(result.getDeadmanTwoDeadTime());
                    }
                    if (result.getRemark() != null) {
                        mETRemark.setText(result.getRemark());
                    }


                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void initData() {
        sexList = Utils.stringsToList(SelectData.CEMETERY_SEX);
        stateList = Utils.stringsToList(SelectData.CEMETERY_STATE);
    }

    private void initView() {
        mETDeadManName1 = (EditText) view.findViewById(R.id.et_deadmanname_1);
        mETDeadManAge1 = (EditText) view.findViewById(R.id.et_deadmanage_1);
        mETDeadManCardId1 = (EditText) view.findViewById(R.id.et_deadmancardid_1);
        mTVDeadManTime1 = (TextView) view.findViewById(R.id.tv_deadmantime_1);
        mSelectDeadManSex1 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_1);
        mSelectDeadManState1 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_1);

        mETDeadManName2 = (EditText) view.findViewById(R.id.et_deadmanname_2);
        mETDeadManAge2 = (EditText) view.findViewById(R.id.et_deadmanage_2);
        mETDeadManCardId2 = (EditText) view.findViewById(R.id.et_deadmancardid_2);
        mTVDeadManTime2 = (TextView) view.findViewById(R.id.tv_deadmantime_2);
        mSelectDeadManSex2 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_2);
        mSelectDeadManState2 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_2);

        mETRemark = (EditText) view.findViewById(R.id.et_remark);

        mSelectDeadManSex1.setName("使用者1性别：");
        mSelectDeadManSex2.setName("使用者2性别：");
        mSelectDeadManState1.setName("使用者1现状：");
        mSelectDeadManState2.setName("使用者2现状：");

        mSelectDeadManSex1.setData(sexList, 0, this);
        mSelectDeadManSex2.setData(sexList, 1, this);
        mSelectDeadManState1.setData(stateList, 2, this);
        mSelectDeadManState2.setData(stateList, 3, this);

        mTVDeadManTime1.setOnClickListener(onClickListener);
        mTVDeadManTime2.setOnClickListener(onClickListener);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVDeadManTime1) {
                timeSelect(mTVDeadManTime1);
            } else if (v == mTVDeadManTime2) {
                timeSelect(mTVDeadManTime2);
            }

        }
    };

    public void timeSelect(TextView textView) {
        Utils.timeSelect(getContext(), textView);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {
        switch (num) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void setStateShow() {
        mSelectDeadManSex1.setStateShow();
        mSelectDeadManState1.setStateShow();

        mETDeadManName1.setFocusable(false);
        mETDeadManAge1.setFocusable(false);
        mETDeadManCardId1.setFocusable(false);
        mTVDeadManTime1.setClickable(false);

        mSelectDeadManSex2.setStateShow();
        mSelectDeadManState2.setStateShow();

        mETDeadManName2.setFocusable(false);
        mETDeadManAge2.setFocusable(false);
        mETDeadManCardId2.setFocusable(false);
        mTVDeadManTime2.setClickable(false);

        mETRemark.setFocusable(false);
    }

    @Override
    public void saveData() {


        HpSaveCemeteryTalkSuccessTwo params = new HpSaveCemeteryTalkSuccessTwo();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
        params.setSaveType(changeState);
        params.setDeadmanOneName(mETDeadManName1.getText().toString());
        params.setDeadmanOneAge(mETDeadManName1.getText().toString());
        params.setDeadmanOneSex(mSelectDeadManSex1.getSelectedData());
        params.setDeadmanOneState(mSelectDeadManState1.getSelectedData());
        params.setDeadmanOneCardId(mETDeadManCardId1.getText().toString());
        params.setDeadmanOneDeadTime(mTVDeadManTime1.getText().toString());
        params.setDeadmanTwoName(mETDeadManName2.getText().toString());
        params.setDeadmanTwoAge(mETDeadManName2.getText().toString());
        params.setDeadmanTwoSex(mSelectDeadManSex2.getSelectedData());
        params.setDeadmanTwoState(mSelectDeadManState2.getSelectedData());
        params.setDeadmanTwoCardId(mETDeadManCardId2.getText().toString());
        params.setDeadmanTwoDeadTime(mTVDeadManTime2.getText().toString());
        params.setRemark(mETRemark.getText().toString());

        Utils.LogVPrint("DeadmanOneName:" + params.getDeadmanOneName());
        Utils.LogVPrint("OrderedId" + params.getOrderId());
        Utils.LogVPrint("DeadmanOneAge:" + params.getDeadmanOneAge());
        Utils.LogVPrint("DeadmanOneSex:" + params.getDeadmanOneSex());
        Utils.LogVPrint("DeadmanOneState:" + params.getDeadmanOneState());
        Utils.LogVPrint("DeadmanOneCardId:" + params.getDeadmanOneCardId());
        Utils.LogVPrint("DeadmanOneDeadTime:" + params.getDeadmanOneDeadTime());
        Utils.LogVPrint("DeadmanTwoName:" + params.getDeadmanTwoName());
        Utils.LogVPrint("DeadmanTwoAge:" + params.getDeadmanTwoAge());
        Utils.LogVPrint("gDeadmanTwoSex:" + params.getDeadmanTwoSex());
        Utils.LogVPrint("DeadmanTwoState:" + params.getDeadmanTwoState());
        Utils.LogVPrint("DeadmanTwoCardId:" + params.getDeadmanTwoCardId());
        Utils.LogVPrint("DeadmanTwoDeadTime:" + params.getDeadmanTwoDeadTime());
        Utils.LogVPrint("getRemark" + params.getRemark());

        if (params.getBespeakId() == -1 || params.getSaveType() == -1 || params.getOrderId() == -1) {
            ToastUtils.show(getContext(), "数据加载异常，请重新载入");
            return;
        }
        if (params.getDeadmanOneName().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1名字不能为空");
            return;
        }
        if (params.getDeadmanOneAge().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1年龄不能为空");
            return;
        }
        if (params.getDeadmanOneSex().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1性别不能为空");
            return;
        }
        if (params.getDeadmanOneState().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1状态不能为空");
            return;
        }
        if (params.getDeadmanOneCardId().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1身份证不能为空");
            return;
        }
        if (params.getDeadmanOneCardId().getBytes().length != 18) {
            ToastUtils.show(getContext(), "往生者身份证号码不足18位");
            return;
        }
        if (params.getDeadmanOneDeadTime().isEmpty()) {
            ToastUtils.show(getContext(), "使用者1去世时间不能为空");
            return;
        }
        MHttpManagerFactory.getCemeteryManager().saveCemeteryTalkSuccessTwo(getContext(), params, new HttpResponseHandler<Object>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "提交数据成功");
                DeadManInfoView.super.saveData();
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(getContext(), "提交数据失败");
            }
        });

    }
}
