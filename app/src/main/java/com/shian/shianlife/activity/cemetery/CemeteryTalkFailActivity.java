package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;

import java.util.ArrayList;
import java.util.List;

public class CemeteryTalkFailActivity extends BaseActivity {
    int inType = -1;

    EditText mETPlanToMoney;
    CetemeryTextSelectLayoutView mSelectPlanToBuy;

    String[] mPlanToBuy={"中式双位","西式双位","单穴","壁葬","草坪葬","树葬","其它葬式"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cemetery_talk_fail);

        initData();
        initView();
    }

    private void initView() {
        mETPlanToMoney = (EditText) findViewById(R.id.et_plantomoney);
        mSelectPlanToBuy= (CetemeryTextSelectLayoutView) findViewById(R.id.select_plantobuy);

        mSelectPlanToBuy.setName("计划购买墓型");
    }

    private void initData() {
        inType = getIntent().getIntExtra(CemeteryQTView.BUILD_NEW_ORDER, -1);
        setTitle("洽谈信息");
        Log.v("this", "inType:" + inType);
        List<String> planToBuyList=new ArrayList<>();
        for (int i = 0; i < mPlanToBuy.length; i++) {
            planToBuyList.add(mPlanToBuy[i]);
        }
    }
}
