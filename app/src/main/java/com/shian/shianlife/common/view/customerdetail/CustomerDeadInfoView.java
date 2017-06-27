package com.shian.shianlife.common.view.customerdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.shian.shianlife.provide.result.HrConsultUsageResult.HrConsultUsageResults;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CustomerDeadInfoView extends FrameLayout {

    @InjectView(R.id.tv_name)
    TextView tv_name;

    @InjectView(R.id.tv_sex)
    TextView tv_sex;

    @InjectView(R.id.tv_age)
    TextView tv_age;

    @InjectView(R.id.tv_height)
    TextView tv_height;

    @InjectView(R.id.tv_shoeSize)
    TextView tv_shoeSize;

    @InjectView(R.id.tv_state)
    TextView tv_state;

    @InjectView(R.id.tv_birthday)
    TextView tv_birthday;

    @InjectView(R.id.tv_curAddress)
    TextView tv_curAddress;

    @InjectView(R.id.tv_intimeReady)
    TextView tv_intimeReady;

    @InjectView(R.id.tv_note)
    TextView tv_note;

    @InjectView(R.id.tv_cardid)
    TextView tv_cardid;

    @InjectView(R.id.tv_other)
    TextView tv_other;

    @InjectView(R.id.tv_choes)
    TextView tv_choes;

    public CustomerDeadInfoView(Context context) {
        super(context);
        init();
    }

    public CustomerDeadInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomerDeadInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_customer_deadinfo, null);
        addView(view);
        ButterKnife.inject(this);
    }

    public void setData(HrConsultUsageResult result) {
        HrConsultUsageResults usage = result.getConsultUsage();
        tv_age.setText(usage.getAge());
        tv_birthday.setText(usage.getBirthday());
        tv_curAddress.setText(usage.getLocation());
        tv_height.setText(usage.getHeight());
        tv_intimeReady.setText(usage.getIntimeReady());
        tv_name.setText(usage.getName());
        tv_note.setText(usage.getNote());
        if (usage.getSex() == 1) {
            tv_sex.setText("未知");
        } else if (usage.getSex() == 2) {
            tv_sex.setText("男");
        } else if (usage.getSex() == 3) {
            tv_sex.setText("女");
        } else {
            tv_sex.setText("未知");
        }
        tv_shoeSize.setText(usage.getShoeSize());
        tv_state.setText(usage.getState());
        tv_cardid.setText(usage.getCardId());
        tv_other.setText(usage.getOtherHealth());
        tv_choes.setText(usage.getClothesData());
    }

}
