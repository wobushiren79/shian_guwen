package com.shian.shianlife.view.cemeteryinfoview.infolayout;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.SelectDictCode;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;


/**
 * Created by Administrator on 2017/4/12.
 */

public class CemeteryAgentManInfo extends BaseCemeteryInfo {

    EditTextViewNormal mAgentManName;
    EditTextViewNormal mAgentManPhone;
    SpinnerViewNormal mRelation;
    MapSelectViewNormal mAgentManLocation;
    EditTextViewNormal mAgentManCardId;
    EditTextViewNormal mAgentManEmail;
    EditTextViewNormal mRemark;
    TextView mTVBack;
    TextView mTVNext;

    LinearLayout mLLButton;
    CallBack thisCallBack;

    public CemeteryAgentManInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_agentman, false);
    }

    public void setThisCallBack(CallBack thisCallBack) {
        this.thisCallBack = thisCallBack;
    }

    @Override
    public void getData() {
        if (beSpeakId == -1) {
            return;
        }
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        params.setOrderId(orderId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessAgentMan(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessAgentMan result) {
                if (result.getAgentmanName() != null)
                    mAgentManName.setData(result.getAgentmanName());
                if (result.getAgentmanPhone() != null)
                    mAgentManPhone.setData(result.getAgentmanPhone());
                if (result.getRelation() != null)
                    mRelation.setDataDict(result.getRelation());
                if (result.getAgentmanLocation() != null)
                    mAgentManLocation.setData(result.getAgentmanLocation());
                if (result.getAgentmanCardId() != null)
                    mAgentManCardId.setData(result.getAgentmanCardId());
                if (result.getAgentmanEmail() != null)
                    mAgentManEmail.setData(result.getAgentmanEmail());
                if (result.getRemark() != null)
                    mRemark.setData(result.getRemark());
                if (thisCallBack != null)
                    thisCallBack.initDataSuccess();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void saveData() {
        if (mAgentManName.getData().equals("")) {
            ToastUtils.show(getContext(), "经办人姓名不能为空");
            return;
        }
        if (mAgentManPhone.getData().equals("")) {
            ToastUtils.show(getContext(), "经办人电话不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(mAgentManPhone.getData())) {
            ToastUtils.show(getContext(), "经办人电话格式不对");
            return;
        }
        HpSaveCemeteryTalkSuccessAgentMan params = new HpSaveCemeteryTalkSuccessAgentMan();
        params.setBespeakId(beSpeakId);
//        params.setSaveType(changeState);
        params.setOrderId(orderId);
        params.setAgentmanName(mAgentManName.getData());
        params.setAgentmanPhone(mAgentManPhone.getData());
        params.setRelation(mRelation.getData());
        params.setAgentmanLocation(mAgentManLocation.getData());
        params.setAgentmanCardId(mAgentManCardId.getData());
        params.setAgentmanEmail(mAgentManEmail.getData());
        params.setRemark(mRemark.getData());
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessAgentMan(getContext(), params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                if (callBack != null)
                    callBack.next(null);
                ToastUtils.show(getContext(), "提交成功");
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(getContext(), "提交失败");
            }
        });
    }

    @Override
    public void initView() {
        mAgentManName = (EditTextViewNormal) view.findViewById(R.id.write_agentmanname);
        mAgentManPhone = (EditTextViewNormal) view.findViewById(R.id.write_agentmanphone);
        mRelation = (SpinnerViewNormal) view.findViewById(R.id.write_relation);
        mAgentManLocation = (MapSelectViewNormal) view.findViewById(R.id.write_agentmanlocation);
        mAgentManCardId = (EditTextViewNormal) view.findViewById(R.id.write_agentmancardid);
        mAgentManEmail = (EditTextViewNormal) view.findViewById(R.id.write_agentmanemail);
        mRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);

        mTVNext = (TextView) view.findViewById(R.id.tv_submit);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);
        mLLButton = (LinearLayout) view.findViewById(R.id.ll_button);

        mTVNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        mTVBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.next(new CemeteryDeadManInfo(getContext(), orderId, beSpeakId));
            }
        });
    }

    @Override
    public void initData() {
        mRelation.initSpinner(SelectDictCode.MAN_RELATION);
    }

    public void setShowMode() {
        mLLButton.setVisibility(GONE);
    }

    public interface CallBack {
        void initDataSuccess();
    }
}
