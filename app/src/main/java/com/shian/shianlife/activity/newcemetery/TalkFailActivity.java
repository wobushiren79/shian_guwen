package com.shian.shianlife.activity.newcemetery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.contanst.SelectDictCode;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkData;
import com.shian.shianlife.view.SelectData;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;


public class TalkFailActivity extends BaseActivity {
    SpinnerViewNormal mWritePlanBuyType;
    EditTextViewNormal mWritePlanBuyMoney;
    SpinnerViewNormal mWriteDeadState1;
    SpinnerViewNormal mWriteDeadState2;
    MapSelectViewNormal mWriteAslLocation;
    SpinnerViewNormal mWriteRelation;
    EditTextViewNormal mWriteTalkPoint;
    SpinnerViewNormal mWriteResult;
    SpinnerViewNormal mWriteTraffic;
    TimeSelectViewNormal mWriteMeetTime;
    EditTextViewNormal mWritePersonNum;
    MapSelectViewNormal mWriteMeetLocation;
    EditTextViewNormal mWriteRemark;

    LinearLayout mLLOtherInfo;
    LinearLayout mLLContent;
    RelativeLayout mRLContent;
    HrGetCemeteryTalkData resultData;
    long beSpeakId = -1;
    long orderId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_fail);
        setTitle("洽谈失败");

        initView();
        initData();
        getData();
    }


    private void initView() {
        mWritePlanBuyType = (SpinnerViewNormal) findViewById(R.id.write_planbuytype);
        mWritePlanBuyMoney = (EditTextViewNormal) findViewById(R.id.write_planbuymoney);
        mWriteDeadState1 = (SpinnerViewNormal) findViewById(R.id.write_deadman_state_1);
        mWriteDeadState2 = (SpinnerViewNormal) findViewById(R.id.write_deadman_state_2);
        mWriteAslLocation = (MapSelectViewNormal) findViewById(R.id.write_asllocation);
        mWriteRelation = (SpinnerViewNormal) findViewById(R.id.write_relation);
        mWriteTalkPoint = (EditTextViewNormal) findViewById(R.id.write_talkpoint);
        mWriteResult = (SpinnerViewNormal) findViewById(R.id.write_result);
        mWriteTraffic = (SpinnerViewNormal) findViewById(R.id.write_traffic);
        mWriteMeetTime = (TimeSelectViewNormal) findViewById(R.id.write_time);
        mWritePersonNum = (EditTextViewNormal) findViewById(R.id.write_personnum);
        mWriteMeetLocation = (MapSelectViewNormal) findViewById(R.id.write_meetlocation);
        mWriteRemark = (EditTextViewNormal) findViewById(R.id.write_remark);

        mLLContent = (LinearLayout) findViewById(R.id.ll_content);
        mLLOtherInfo = (LinearLayout) findViewById(R.id.ll_other_info);
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);

        mWriteResult.setSpinnerCallBack(new SpinnerViewNormal.SpinnerCallBack() {

            @Override
            public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
                //预约二次洽谈
                if (position == 0) {
                    mLLOtherInfo.setVisibility(View.GONE);
                } else {
                    mLLOtherInfo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void check(SpinnerViewNormal view) {

            }
        });
    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);

        mWritePlanBuyType.initSpinner(SelectDictCode.TOMB_TYPE);
        mWriteDeadState1.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mWriteDeadState2.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mWriteRelation.initSpinner(SelectDictCode.MAN_RELATION);
        mWriteResult.initSpinner(SelectData.CEMETERY_RESULT);
        mWriteTraffic.initSpinner(SelectDictCode.CONSULT_TRAFFICWAY);
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkInfo(TalkFailActivity.this, params, new HttpResponseHandler<HrGetCemeteryTalkData>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkData result) {
                resultData = result;
                setData();
                setMask();

            }

            @Override
            public void onError(String message) {
                setMask();

            }
        });
    }

    private void setMask() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mLLContent.getHeight());
        TextView mMask = new TextView(TalkFailActivity.this);
        mMask.setLayoutParams(layoutParams);
        mMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRLContent.addView(mMask);
    }

    /**
     * 设置数据
     */
    private void setData() {
        if (resultData.getPlanBuyCemetery() != null && !resultData.getPlanBuyCemetery().isEmpty())
            mWritePlanBuyType.setDataDict(resultData.getPlanBuyCemetery());
        if (resultData.getUserOneState() != null&& !resultData.getUserOneState().isEmpty())
            mWriteDeadState1.setDataDict(resultData.getUserOneState());
        if (resultData.getUserTwoState() != null&& !resultData.getUserTwoState().isEmpty())
            mWriteDeadState2.setDataDict(resultData.getUserTwoState());
        if (resultData.getRelation() != null&& !resultData.getRelation().isEmpty())
            mWriteRelation.setDataDict(resultData.getRelation());
        if (resultData.getTrafficWay() != null)
            mWriteTraffic.setDataDict(resultData.getTrafficWay());
        if (resultData.getPersonNum() != null)
            mWritePersonNum.setData(resultData.getPersonNum());
        if (resultData.getOrderTime() != null)
            mWriteMeetTime.setData(resultData.getOrderTime());
        if (resultData.getOrderLocation() != null)
            mWriteMeetLocation.setData(resultData.getOrderLocation());
        if (resultData.getRemark() != null)
            mWriteRemark.setData(resultData.getRemark());
        if (resultData.getTalkPoint() != null)
            mWriteTalkPoint.setData(resultData.getTalkPoint());

        mWriteResult.setData(resultData.getTalkResult());
    }


}
