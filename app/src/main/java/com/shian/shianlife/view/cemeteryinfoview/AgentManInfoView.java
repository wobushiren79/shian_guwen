package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessThree;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessThree;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class AgentManInfoView extends BaseInfoView implements CetemeryTextSelectLayoutView.onSelectedListener {
    private View view;

    CetemeryTextSelectLayoutView mSelectRelation;

    MapSelectLayoutView mMapAgentManLocation;

    EditText mETAgentManName;
    EditText mETAgentManPhone;
    EditText mETAgentManCardId;
    EditText mETAgentManEmail;
    EditText mETAgentManRemark;

    List<String> relationList = new ArrayList<>();

    public AgentManInfoView(Context context) {
        this(context, null);
    }

    public AgentManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_agentman_info, this);
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
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessThree(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessThree>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessThree result) {
                Utils.LogVPrint("getAgentmanName" + result.getAgentmanName());
                Utils.LogVPrint("getAgentmanPhone" + result.getAgentmanPhone());
                Utils.LogVPrint("getRelation" + result.getRelation());
                Utils.LogVPrint("getAgentmanLocation" + result.getAgentmanLocation());
                Utils.LogVPrint("getAgentmanCardId" + result.getAgentmanCardId());
                Utils.LogVPrint("getAgentmanEmail" + result.getAgentmanEmail());
                Utils.LogVPrint("getRemark" + result.getRemark());

                if (result != null) {
                    if (result.getAgentmanName() != null) {
                        mETAgentManName.setText(result.getAgentmanName());
                    }
                    if (result.getAgentmanPhone() != null) {
                        mETAgentManPhone.setText(result.getAgentmanPhone());
                    }
                    if (result.getRelation() != null) {
                        mSelectRelation.setString(result.getRelation());
                    }
                    if (result.getAgentmanLocation() != null) {
                        mMapAgentManLocation.setLocation(result.getAgentmanLocation());
                    }
                    if (result.getAgentmanCardId() != null) {
                        mETAgentManCardId.setText(result.getAgentmanCardId());
                    }
                    if (result.getAgentmanEmail() != null) {
                        mETAgentManEmail.setText(result.getAgentmanEmail());
                    }
                    if (result.getRemark() != null) {
                        mETAgentManRemark.setText(result.getRemark());
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void initData() {
        relationList = Utils.stringsToList(SelectData.CEMETERY_RELATION);
    }

    private void initView() {
        mETAgentManName = (EditText) view.findViewById(R.id.et_agentmanname);
        mETAgentManPhone = (EditText) view.findViewById(R.id.et_agentmanphone);
        mSelectRelation = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_relation);
        mMapAgentManLocation = (MapSelectLayoutView) view.findViewById(R.id.mapselect);
        mETAgentManCardId = (EditText) view.findViewById(R.id.et_agentmancardid);
        mETAgentManEmail = (EditText) view.findViewById(R.id.et_agentemail);
        mETAgentManRemark = (EditText) view.findViewById(R.id.et_remark);

        mSelectRelation.setName("经办人是使用者的：");
        mSelectRelation.setData(relationList, 0, this);

        mMapAgentManLocation.setData(0, new ArrayList<String>());
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {

    }

    public void setStateShow() {
        mSelectRelation.setStateShow();
        mMapAgentManLocation.setStateShow();
        mETAgentManName.setFocusable(false);
        mETAgentManPhone.setFocusable(false);
        mETAgentManCardId.setFocusable(false);
        mETAgentManEmail.setFocusable(false);
        mETAgentManRemark.setFocusable(false);
    }

    @Override
    public void saveData() {
        HpSaveCemeteryTalkSuccessThree params = new HpSaveCemeteryTalkSuccessThree();
        params.setBespeakId(beSpeakId);
        params.setSaveType(changeState);
        params.setOrderId(orderId);
        params.setAgentmanName(mETAgentManName.getText().toString());
        params.setAgentmanPhone(mETAgentManPhone.getText().toString());
        params.setRelation(mSelectRelation.getSelectedData());
        params.setAgentmanLocation(mMapAgentManLocation.getLocation());
        params.setAgentmanCardId(mETAgentManCardId.getText().toString());
        params.setAgentmanEmail(mETAgentManEmail.getText().toString());
        params.setRemark(mETAgentManRemark.getText().toString());

        if (params.getBespeakId() == -1 || params.getSaveType() == -1 || params.getOrderId() == -1) {
            ToastUtils.show(getContext(), "加载数据异常，请重新加载");
            return;
        }
        if (params.getAgentmanName().isEmpty()) {
            ToastUtils.show(getContext(), "经办人姓名不能为空");
            return;
        }
        if (params.getAgentmanPhone().isEmpty()) {
            ToastUtils.show(getContext(), "经办人电话不能为空");
            return;
        }
        if (params.getRelation().isEmpty()) {
            ToastUtils.show(getContext(), "经办人关系不能为空");
            return;
        }
        if (params.getAgentmanLocation().isEmpty()) {
            ToastUtils.show(getContext(), "地址不能为空");
            return;
        }
        if (params.getAgentmanCardId().isEmpty()) {
            ToastUtils.show(getContext(), "经办人身份证不能为空");
            return;
        }
//        if (params.getAgentmanEmail().isEmpty()) {
//            ToastUtils.show(getContext(), "经办人邮箱不能为空");
//            return;
//        }
//        if (!Utils.isEmail(params.getAgentmanEmail())) {
//            Log.v("this","params.getAgentmanEmail():"+params.getAgentmanEmail());
//            ToastUtils.show(getContext(), "邮箱格式不对");
//            return;
//        }
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessThree(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "数据提交成功");
                AgentManInfoView.super.saveData();
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
