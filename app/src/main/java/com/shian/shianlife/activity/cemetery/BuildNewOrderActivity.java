package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;

import java.util.ArrayList;
import java.util.List;

public class BuildNewOrderActivity extends BaseActivity {

    CetemeryTextSelectLayoutView mCetemeryNameSelectLayout;
    CetemeryTextSelectLayoutView mTrafficeSelectLayout;

    MapSelectLayoutView selectLayoutView;

    TextView mTVTime;
    EditText mETName;
    EditText mETPhone;
    EditText mETPersonNum;

    String[] mCetemeryName = {"龙潭寺院山公墓", "青白江罗汉寺公墓", "温江大郎福寿园"
            , "成华区石岭南苑", "金牛区皇恩寺公墓", "龙泉驿区长松寺公墓", "龙泉驿区金龙山公墓"
            , "锦江区祝望山公墓", "蒲江红枫艺术陵园", "锦江区金莎陵园", "都江堰味江陵园"
            , "龙泉驿区燃灯寺公墓", "龙泉驿区真武山公墓", "龙泉驿区金龙山公墓", "崇州白塔山公墓"};
    String[] mTrafficeWay = {"自行", "需要派车"};

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
        List<String> ctemeryNameList = new ArrayList<>();
        List<String> trafficeWayList = new ArrayList<>();
        for (int i = 0; i < mCetemeryName.length; i++) {
            ctemeryNameList.add(mCetemeryName[i]);
        }
        for (int i = 0; i < mTrafficeWay.length; i++) {
            trafficeWayList.add(mTrafficeWay[i]);
        }

        selectLayoutView.setData(0, new ArrayList<String>());
        mCetemeryNameSelectLayout.setData(ctemeryNameList);
        mTrafficeSelectLayout.setData(trafficeWayList);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                Utils.timeSelect(BuildNewOrderActivity.this, mTVTime);
            }
        }
    };
}
