package com.shian.shianlife.activity.cemetery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.view.cemeteryinfoview.AgentManInfoView;
import com.shian.shianlife.view.cemeteryinfoview.BaseInfoView;
import com.shian.shianlife.view.cemeteryinfoview.CemeteryInfoView;
import com.shian.shianlife.view.cemeteryinfoview.DeadManInfoView;

import static com.shian.shianlife.common.view.order.CemeteryQTView.BUY_INFO;
import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_CHANGE_INFO_STATE;
import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ID;
import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ORDER_ID;

public class BuyCemeteryInfoActivity extends BaseActivity implements BaseInfoView.InfoCallBack{
    int inType = -1;
    int changeState=-1;//修改信息（1为洽谈 2为售后）
    long beSpeakId=-1;
    long orderId=-1;
    LinearLayout mLLContent;


    BaseInfoView mBaseInfoView;
    Button mBTBack;
    Button mBTNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cemetery_info);
        inType = getIntent().getIntExtra(BUY_INFO, -1);
        changeState=getIntent().getIntExtra(TALK_CHANGE_INFO_STATE,-1);
        beSpeakId=getIntent().getLongExtra(TALK_INFO_ID,-1);
        orderId=getIntent().getLongExtra(TALK_INFO_ORDER_ID,-1);
        initView();
        initData();
    }

    private void initView() {
        mLLContent = (LinearLayout) findViewById(R.id.ll_content);

        mBTBack = (Button) findViewById(R.id.bt_cemeteryback);
        mBTNext = (Button) findViewById(R.id.bt_cemeterynext);

        mBTNext.setOnClickListener(onClickListener);
        mBTBack.setOnClickListener(onClickListener);
    }

    private void initData() {

        switch (inType) {
            case 0:
                setTitle("创建购墓订单");
                setButton();
                mBaseInfoView=new CemeteryInfoView(BuyCemeteryInfoActivity.this);
                break;
            case 1:
                setTitle("使用者信息");
                setButton();
                mBaseInfoView=new DeadManInfoView(BuyCemeteryInfoActivity.this);
                break;
            case 2:
                setTitle("经办人信息");
                setButton();
                mBaseInfoView=new AgentManInfoView(BuyCemeteryInfoActivity.this);
                break;
        }
        mLLContent.addView(mBaseInfoView);
        mBaseInfoView.setInfoCallBack(this);
        mBaseInfoView.setBeSpeakId(beSpeakId);
        mBaseInfoView.setOrderId(orderId);
        mBaseInfoView.setChangeState(changeState);
        mBaseInfoView.getDataStart();
    }



    private void setButton() {
        switch (inType) {
            case 0:
                mBTBack.setVisibility(View.GONE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTNext.setText("下一步");
                break;
            case 1:
                mBTBack.setVisibility(View.VISIBLE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTBack.setText("上一步");
                mBTNext.setText("下一步");
                break;
            case 2:
                mBTBack.setVisibility(View.VISIBLE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTBack.setText("上一步");
                mBTNext.setText("完成");
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTBack) {
                mLLContent.removeAllViews();
                switch (inType) {
                    case 0:
                        break;
                    case 1:
                        inType = 0;
                        initData();
                        break;
                    case 2:
                        inType = 1;
                        initData();
                        break;
                }
            } else if (view == mBTNext) {
                mBaseInfoView.saveData();
            }
        }
    };

    @Override
    public void SaveSuccess() {
        mLLContent.removeAllViews();
        CemeteryFragment.C_bOrder_isRefresh=true;
        switch (inType) {
            case 0:
                inType = 1;
                initData();
                break;
            case 1:
                inType = 2;
                initData();
                break;
            case 2:
                initData();
                finish();
                break;
        }
    }
}
