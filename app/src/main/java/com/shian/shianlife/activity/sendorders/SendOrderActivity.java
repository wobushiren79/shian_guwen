package com.shian.shianlife.activity.sendorders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.order.ListFwpdzView;

import java.util.ArrayList;
import java.util.List;

public class SendOrderActivity extends BaseActivity {

    LinearLayout mLLHeadLayout;

    int step = 0;
    String[] headData1 = {"去世时间", "去世地点"};
    String[] headData2 = {"治丧地址"};
    String[] headData3 = {"殡仪馆地址", "手续办理方式", "交通方式", "约定见面时间", "约定见面地点", "手续证件", "其它"};
    String[] headData4 = {"治丧地址"};
    String[] headData5 = {"治丧地址"};
    String[] headData6 = {"殡仪馆地址", "火化炉", "遗体停放", "火化时间", "遗体告别仪式时间", "出殡时间"};
    String[] headData7 = {};

    List<String[]> listHeadData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);

        step = getIntent().getIntExtra("step", 0);
        String title = getIntent().getStringExtra("TitleName");
        setTitle(title);

        initView();
        initData();
    }

    private void initView() {
        mLLHeadLayout = (LinearLayout) findViewById(R.id.ll_head_content);
    }

    private void initData() {
        listHeadData.add(headData1);
        listHeadData.add(headData2);
        listHeadData.add(headData3);
        listHeadData.add(headData4);
        listHeadData.add(headData5);
        listHeadData.add(headData6);
        listHeadData.add(headData7);

        for (int i = 0; i < listHeadData.get(step).length; i++) {
            String firstName = listHeadData.get(step)[i];
            View view = LayoutInflater.from(SendOrderActivity.this).inflate(R.layout.layout_sendorder_head_item, null);
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(firstName + "：");
            mLLHeadLayout.addView(view);
        }
    }
}
