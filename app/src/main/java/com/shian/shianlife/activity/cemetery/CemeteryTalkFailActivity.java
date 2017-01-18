package com.shian.shianlife.activity.cemetery;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;


public class CemeteryTalkFailActivity extends BaseActivity implements CetemeryTextSelectLayoutView.onSelectedListener {
    int inType = -1;
    LinearLayout mLLDetails;

    EditText mETPlanToMoney;
    EditText mETTalkPoint;
    EditText mETTrafficWay;
    EditText mETPersonNum;
    EditText mETRemark;

    TextView mTVTime;
    Button mBTSubmit;

    CetemeryTextSelectLayoutView mSelectPlanToBuy;
    CetemeryTextSelectLayoutView mSelectState1;
    CetemeryTextSelectLayoutView mSelectState2;
    CetemeryTextSelectLayoutView mSelectRelation;
    CetemeryTextSelectLayoutView mSelectResult;

    MapSelectLayoutView mMapSelect1;
    MapSelectLayoutView mMapSelect2;


    List<String> planToBuyList = new ArrayList<>();
    List<String> stateList = new ArrayList<>();
    List<String> relationList = new ArrayList<>();
    List<String> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cemetery_talk_fail);

        initData();
        initView();
    }

    private void initView() {
        mLLDetails = (LinearLayout) findViewById(R.id.ll_details);
        mETPlanToMoney = (EditText) findViewById(R.id.et_plantomoney);
        mETTalkPoint = (EditText) findViewById(R.id.et_talkpoint);
        mETRemark = (EditText) findViewById(R.id.et_remark);
        mETTrafficWay = (EditText) findViewById(R.id.et_au_note);
        mETPersonNum = (EditText) findViewById(R.id.et_personnum);

        mTVTime = (TextView) findViewById(R.id.tv_time);

        mSelectPlanToBuy = (CetemeryTextSelectLayoutView) findViewById(R.id.select_plantobuy);
        mSelectState1 = (CetemeryTextSelectLayoutView) findViewById(R.id.select_state_1);
        mSelectState2 = (CetemeryTextSelectLayoutView) findViewById(R.id.select_state_2);
        mSelectRelation = (CetemeryTextSelectLayoutView) findViewById(R.id.select_relation);
        mSelectResult = (CetemeryTextSelectLayoutView) findViewById(R.id.select_result);

        mMapSelect1 = (MapSelectLayoutView) findViewById(R.id.mapselect);
        mMapSelect2 = (MapSelectLayoutView) findViewById(R.id.mapselect1);

        mBTSubmit = (Button) findViewById(R.id.bt_submit);

        mSelectPlanToBuy.setName("计划购买墓型：");
        mSelectPlanToBuy.setData(planToBuyList, 0, this);

        mSelectState1.setName("使用者1现状：");
        mSelectState2.setName("使用者2现状：");
        mSelectState1.setData(stateList, 1, this);
        mSelectState2.setData(stateList, 2, this);


        mSelectRelation.setName("联系人是使用者的：");
        mSelectRelation.setData(relationList, 3, this);

        mSelectResult.setName("洽谈结果：");
        mSelectResult.setData(resultList, 4, this);

        mMapSelect1.setData(0, new ArrayList<String>());
        mMapSelect2.setData(1, new ArrayList<String>());

        mTVTime.setOnClickListener(onClickListener);
        mBTSubmit.setOnClickListener(onClickListener);

        if (inType == 0) {
            mBTSubmit.setVisibility(View.VISIBLE);
        } else {
            mBTSubmit.setVisibility(View.GONE);
            setStateShow();
        }
    }

    private void initData() {
        inType = getIntent().getIntExtra(CemeteryQTView.BUILD_NEW_ORDER, -1);
        setTitle("洽谈信息");
        Log.v("this", "inType:" + inType);


        planToBuyList = Utils.stringsToList(SelectData.CEMETERY_TYPE);
        stateList = Utils.stringsToList(SelectData.CEMETERY_STATE);
        relationList = Utils.stringsToList(SelectData.CEMETERY_RELATION);
        resultList = Utils.stringsToList(SelectData.CEMETERY_RESULT);
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
            case 4:
                if (i == 0) {
                    mLLDetails.setVisibility(View.VISIBLE);
                } else if (i == 1) {
                    mLLDetails.setVisibility(View.GONE);
                }
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                Utils.timeSelect(CemeteryTalkFailActivity.this, mTVTime);
            } else if (view == mBTSubmit) {

            }
        }
    };

    public void setStateShow() {
        mETPlanToMoney.setFocusable(false);
        mETTalkPoint.setFocusable(false);
        mETTrafficWay.setFocusable(false);
        mETPersonNum.setFocusable(false);
        mETRemark.setFocusable(false);

        mTVTime.setClickable(false);

        mSelectPlanToBuy.setStateShow();
        mSelectState1.setStateShow();
        mSelectState2.setStateShow();
        mSelectRelation.setStateShow();
        mSelectResult.setStateShow();

         mMapSelect1.setStateShow();
         mMapSelect2.setStateShow();
    }
}
