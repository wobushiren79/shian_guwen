package com.shian.shianlife.view.cemeteryinfoview.infolayout;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.SelectDictCode;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/12.
 */

public class CemeteryDeadManInfo extends BaseCemeteryInfo {
    EditTextViewNormal mUserName1;
    EditTextViewNormal mUserName2;
    EditTextViewNormal mUserAge1;
    EditTextViewNormal mUserAge2;
    SpinnerViewNormal mUserSex1;
    SpinnerViewNormal mUserSex2;
    SpinnerViewNormal mUserState1;
    SpinnerViewNormal mUserState2;
    EditTextViewNormal mUserCardId1;
    EditTextViewNormal mUserCardId2;
    TimeSelectViewNormal mUserDeadTime1;
    TimeSelectViewNormal mUserDeadTime2;
    EditTextViewNormal mRemark;

    TextView mTVNext;
    TextView mTVBack;

    LinearLayout mLLButton;

    CallBack thisCallBack;

    private Long deadmanOneId;
    private Long deadmanTwoId;

    public CemeteryDeadManInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_deadman, false);
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
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessDeadMan(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessDeadMan result) {
                if (result.getList() == null || result.getList().size() == 0) {

                } else {
                    if (result.getList().get(0).getName() != null)
                        mUserName1.setData(result.getList().get(0).getName());
                    if (result.getList().get(1) != null && result.getList().get(1).getName() != null)
                        mUserName2.setData(result.getList().get(1).getName());
                    if (result.getList().get(0).getAge() != null)
                        mUserAge1.setData(result.getList().get(0).getAge());
                    if (result.getList().get(1) != null && result.getList().get(1).getAge() != null)
                        mUserAge2.setData(result.getList().get(1).getAge());
                    if (result.getList().get(0).getSex() != null && !result.getList().get(0).getSex().isEmpty())
                        mUserSex1.setDataDict(result.getList().get(0).getSex());
                    if (result.getList().get(1) != null && result.getList().get(1).getSex() != null && !result.getList().get(1).getSex().isEmpty())
                        mUserSex2.setDataDict(result.getList().get(1).getSex());
                    if (result.getList().get(0).getStatus() != null && !result.getList().get(0).getStatus().isEmpty())
                        mUserState1.setDataDict(result.getList().get(0).getStatus());
                    if (result.getList().get(1) != null && result.getList().get(1).getStatus() != null && !result.getList().get(1).getStatus().isEmpty())
                        mUserState2.setDataDict(result.getList().get(1).getStatus());
                    if (result.getList().get(0).getIdCardNo() != null)
                        mUserCardId1.setData(result.getList().get(0).getIdCardNo());
                    if (result.getList().get(1) != null && result.getList().get(1).getIdCardNo() != null)
                        mUserCardId2.setData(result.getList().get(1).getIdCardNo());
                    if (result.getList().get(0).getDeathTime() != null)
                        mUserDeadTime1.setData(result.getList().get(0).getDeathTime());
                    if (result.getList().get(1) != null && result.getList().get(1).getDeathTime() != null)
                        mUserDeadTime2.setData(result.getList().get(1).getDeathTime());
                    if (result.getList().get(0).getRemark() != null)
                        mRemark.setData(result.getList().get(0).getRemark());

                    if (result.getList().get(0).getDeadId() != null) {
                        deadmanOneId = result.getList().get(0).getDeadId();
                    }
                    if (result.getList().get(1) != null && result.getList().get(1).getDeadId() != null) {
                        deadmanTwoId = result.getList().get(1).getDeadId();
                    }
                    if (thisCallBack != null)
                        thisCallBack.initDataSuccess();
                }

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void saveData() {
        if (mUserName1.getData().equals("")) {
            ToastUtils.show(getContext(), "使用者1不能为空");
            return;
        }
        HpSaveCemeteryTalkSuccessDeadMan params=new HpSaveCemeteryTalkSuccessDeadMan();
        List<HpSaveCemeteryTalkSuccessDeadMan.OrderDeath> list=new ArrayList<>();
        HpSaveCemeteryTalkSuccessDeadMan.OrderDeath itemOne = new HpSaveCemeteryTalkSuccessDeadMan.OrderDeath();
        itemOne.setName(mUserName1.getData());
        itemOne.setOrderId(orderId);
        itemOne.setAge(mUserAge1.getData());
        itemOne.setSex(mUserSex1.getData());
        itemOne.setStatus(mUserState1.getData());
        itemOne.setIdCardNo(mUserCardId1.getData());
        itemOne.setDeathTime(mUserDeadTime1.getData());
        if (deadmanOneId != null) {
            itemOne.setDeadId(deadmanOneId);
        }

        HpSaveCemeteryTalkSuccessDeadMan.OrderDeath itemTwo = new HpSaveCemeteryTalkSuccessDeadMan.OrderDeath();
        itemTwo.setName(mUserName2.getData());
        itemTwo.setOrderId(orderId);
        itemTwo.setAge(mUserAge2.getData());
        itemTwo.setSex(mUserSex2.getData());
        itemTwo.setStatus(mUserState2.getData());
        itemTwo.setIdCardNo(mUserCardId2.getData());
        itemTwo.setDeathTime(mUserDeadTime2.getData());
        if (deadmanTwoId != null) {
            itemTwo.setDeadId(deadmanTwoId);
        }
        list.add(itemOne);
        list.add(itemTwo);
        params.setList(list);

        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessDeadMan(getContext(), params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                if (callBack != null)
                    callBack.next(new CemeteryAgentManInfo(getContext(), orderId, beSpeakId));
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
        mUserName1 = (EditTextViewNormal) view.findViewById(R.id.write_username_1);
        mUserName2 = (EditTextViewNormal) view.findViewById(R.id.write_username_2);
        mUserAge1 = (EditTextViewNormal) view.findViewById(R.id.write_userage_1);
        mUserAge2 = (EditTextViewNormal) view.findViewById(R.id.write_userage_2);
        mUserSex1 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_1);
        mUserSex2 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_2);
        mUserState1 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_1);
        mUserState2 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_2);
        mUserCardId1 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_1);
        mUserCardId2 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_2);
        mUserDeadTime1 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_1);
        mUserDeadTime2 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_2);
        mRemark = (EditTextViewNormal) view.findViewById(R.id.write_remark);

        mTVNext = (TextView) view.findViewById(R.id.tv_submit);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);
        mLLButton = (LinearLayout) view.findViewById(R.id.ll_button);
        mUserAge1.setInputType(InputType.TYPE_CLASS_NUMBER);
        mUserAge2.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                    callBack.next(new CemeteryPreInfo(getContext(), orderId, beSpeakId));
            }
        });
    }

    @Override
    public void initData() {
        mUserSex1.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserSex2.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserState1.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mUserState2.initSpinner(SelectDictCode.DEAD_INFO_STATE);
    }

    public void setShowMode() {
        mLLButton.setVisibility(GONE);
    }

    public interface CallBack {
        void initDataSuccess();
    }
}
