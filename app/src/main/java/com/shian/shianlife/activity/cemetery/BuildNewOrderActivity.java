package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;



public class BuildNewOrderActivity extends BaseActivity implements CetemeryTextSelectLayoutView.onSelectedListener {

    CetemeryTextSelectLayoutView mCetemeryNameSelectLayout;
    CetemeryTextSelectLayoutView mTrafficeSelectLayout;

    MapSelectLayoutView selectLayoutView;

    TextView mTVTime;
    EditText mETName;
    EditText mETPhone;
    EditText mETPersonNum;

    List<String> ctemeryNameList = new ArrayList<>();
    List<String> trafficeWayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_order);

        initView();
        initData();
    }

    private void initView() {
        mCetemeryNameSelectLayout = (CetemeryTextSelectLayoutView) findViewById(R.id.select_cemeteryname);
        mTrafficeSelectLayout = (CetemeryTextSelectLayoutView) findViewById(R.id.select_traffic);
        selectLayoutView = (MapSelectLayoutView) findViewById(R.id.mapselect);

        mTVTime = (TextView) findViewById(R.id.tv_time);
        mETName = (EditText) findViewById(R.id.et_name);
        mETPhone = (EditText) findViewById(R.id.et_phone);
        mETPersonNum = (EditText) findViewById(R.id.et_num);

        mCetemeryNameSelectLayout.setName("预约参观公墓：");
        mTrafficeSelectLayout.setName("交通方式：");

        mTVTime.setOnClickListener(onClickListener);
    }

    private void initData() {
        setTitle("新建预约单");

//        for (int i = 0; i < mCetemeryName.length; i++) {
//            ctemeryNameList.add(mCetemeryName[i]);
//        }
        trafficeWayList=Utils.stringsToList(SelectData.CEMETERY_TRAFFICEWAY);

        selectLayoutView.setData(0, new ArrayList<String>());
        mCetemeryNameSelectLayout.setData(ctemeryNameList, 0, this);
        mTrafficeSelectLayout.setData(trafficeWayList, 1, this);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                Utils.timeSelect(BuildNewOrderActivity.this, mTVTime);
            }
        }
    };

    @Override
    public void onItemSelected(View view, int i, long l, int num) {
        switch (num) {
            case 0:
                break;
            case 1:
                break;
        }
    }
}
