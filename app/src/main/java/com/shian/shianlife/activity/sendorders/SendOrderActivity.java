package com.shian.shianlife.activity.sendorders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.order.ListFwpdzView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;

import java.util.ArrayList;
import java.util.List;

public class SendOrderActivity extends BaseActivity {
    public static String UPDATA_ACTION = "SendOrderUpData";

    LinearLayout mLLHeadLayout;
    LinearLayout mLLCheckBoxLayout;
    LinearLayout mLLCotent;
    Button mBTSubmit;

    long orderId;
    long consultId;

    CheckBox mCBCheckAll;
    int step = 0;
    String[] headData1 = {"去世时间", "去世地点"};
    String[] headData2 = {"治丧地址"};
    String[] headData3 = {"殡仪馆地址", "手续办理方式", "交通方式", "约定见面时间", "约定见面地点", "手续证件", "其它"};
    String[] headData4 = {"治丧地址"};
    String[] headData5 = {"治丧地址"};
    String[] headData6 = {"殡仪馆地址", "火化炉", "遗体停放", "火化时间", "遗体告别仪式时间", "出殡时间"};
    String[] headData7 = {};

    String[] checkData1 = {"焚烧倒头纸，冷却后装入灰袋",
            "确定是否需要入殓师",
            "确定逝者相片做遗像",
            "开具死亡证明",
            "净身更衣",
            "殡仪馆接运遗体"};

    String[] checkData2 = {"确定棚架、灵堂装饰、消耗用品、回礼、仪式等执行人员",
            "确定灵堂搭设的地点，入口方向，电源",
            "家属准备灵堂贡品、用品",
            "家属佩戴手孝，挽联、礼单、讣告制作，花圈摆放",
            "协调、监督灵堂搭建、商品供应、仪式举行",
            "联系殡仪馆，预约火化时间"};

    String[] checkData3 = {"陪同办理】联系家属确认见面时间、地点/\n【代办手续】确认相关证件（“三证一书）准确齐全",
            "联系殡仪馆对接人，告知遗体转单间及单间价位",
            "确定骨灰盒（东郊殡仪馆办理火化手续需要提前携带骨灰盒）",
            "前往殡仪馆办理火化手续，领取火化票据"};

    String[] checkData4 = {"返回治丧现场，查看灵堂消耗用品是否够用、回礼是否增加；",
            "提醒家属收拾逝者遗物并打包",
            "与家属清点需要清退的花圈",
            "协调现场服务",
            "确定次日出殡事宜：出殡车辆、花圈车、出殡路线，准备红包、大白花、小白花"};

    String[] checkData5 = {"出殡前1小时到达治丧现场",
            "联系拆棚人员，确定拆棚时间"};

    String[] checkData6 = {"确定出殡车辆到位，车头系上大白花",
            "将逝者遗物、花圈装饰花圈车",
            "灵堂搭建处贴红纸块",
            "家里留人，以便拆除灵堂",
            "红包派发给出殡司机，红包上系上红丝带",
            "举行出殡仪式",
            "携带倒头灰、火化手续、骨灰盒"};

    String[] checkData7 = {"车辆到达殡仪馆",
            "殡仪馆前台报到",
            "举行遗体告别仪式",
            "遗体火化，等候期间领取火化证",
            "领取骨灰"};
    List<String[]> listHeadData = new ArrayList<>();
    List<String[]> listCheckData = new ArrayList<>();

    List<View> listHeadView = new ArrayList<>();
    List<CheckBox> listCheckBox = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);

        step = getIntent().getIntExtra("step", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        consultId = getIntent().getLongExtra("consultId", 0);

        String title = getIntent().getStringExtra("TitleName");
        setTitle(title);

        initView();
        initData();

        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATA_ACTION);
        registerReceiver(getDataUp, filter);
    }

    private void initView() {
        mLLHeadLayout = (LinearLayout) findViewById(R.id.ll_head_content);
        mLLCheckBoxLayout = (LinearLayout) findViewById(R.id.ll_checkbox);
        mCBCheckAll = (CheckBox) findViewById(R.id.cb_allcheck);
        mBTSubmit = (Button) findViewById(R.id.bt_submit);
        mLLCotent = (LinearLayout) findViewById(R.id.ll_content);

        mCBCheckAll.setOnClickListener(onClickListener);
        mBTSubmit.setOnClickListener(onClickListener);
    }


    private void initData() {
        listHeadData.add(headData1);
        listHeadData.add(headData2);
        listHeadData.add(headData3);
        listHeadData.add(headData4);
        listHeadData.add(headData5);
        listHeadData.add(headData6);
        listHeadData.add(headData7);

        listCheckData.add(checkData1);
        listCheckData.add(checkData2);
        listCheckData.add(checkData3);
        listCheckData.add(checkData4);
        listCheckData.add(checkData5);
        listCheckData.add(checkData6);
        listCheckData.add(checkData7);

        for (int i = 0; i < listHeadData.get(step).length; i++) {
            String firstName = listHeadData.get(step)[i];
            View view = LayoutInflater.from(SendOrderActivity.this).inflate(R.layout.layout_sendorder_head_item, null);
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(firstName + "：");
            listHeadView.add(view);
            mLLHeadLayout.addView(view);
        }
        for (int i = 0; i < listCheckData.get(step).length; i++) {
            String checkText = listCheckData.get(step)[i];
            CheckBox checkBox = new CheckBox(SendOrderActivity.this);
            checkBox.setOnCheckedChangeListener(onCheckedChangeListListener);
            checkBox.setText(checkText);
            listCheckBox.add(checkBox);
            mLLCheckBoxLayout.addView(checkBox);
        }

        switch (step) {
            case 0:
                SendOrderStep0 sendOrderStep0 = new SendOrderStep0(SendOrderActivity.this, consultId);
                mLLCotent.addView(sendOrderStep0);
                break;
            case 1:
                SendOrderStep1 sendOrderStep1=new SendOrderStep1(SendOrderActivity.this,consultId);
                mLLCotent.addView(sendOrderStep1);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTSubmit) {
                finish();
            } else if (view == mCBCheckAll) {
                setCheckBoxAll();
            }
        }
    };

    private void setCheckBoxAll() {
        if (mCBCheckAll.isChecked()) {

            for (CheckBox box : listCheckBox) {
                box.setChecked(true);
            }
            mBTSubmit.setVisibility(View.VISIBLE);
        } else {

            for (CheckBox box : listCheckBox) {
                box.setChecked(false);
            }
            mBTSubmit.setVisibility(View.GONE);
        }
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                boolean isAllCheck = true;
                for (CheckBox box : listCheckBox) {
                    if (!box.isChecked()) {
                        isAllCheck = false;
                        return;
                    }
                }
                if (isAllCheck) {
                    mBTSubmit.setVisibility(View.VISIBLE);
                    mCBCheckAll.setChecked(true);
                }
            } else {
                mBTSubmit.setVisibility(View.GONE);
                mCBCheckAll.setChecked(false);
            }

        }
    };


    BroadcastReceiver getDataUp = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().containsKey("data")) {
                ArrayList<String> data = intent.getStringArrayListExtra("data");
                for (int i = 0; i < listHeadView.size(); i++) {
                    View view = listHeadView.get(i);
                    TextView content = (TextView) view.findViewById(R.id.tv_text);
                    content.setText(data.get(i));
                }

            }
        }
    };
}
