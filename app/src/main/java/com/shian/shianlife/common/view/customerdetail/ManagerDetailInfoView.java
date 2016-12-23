package com.shian.shianlife.common.view.customerdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrConsultAgentman.HrConsultAgentmans;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ManagerDetailInfoView extends FrameLayout {

    @InjectView(R.id.tv_name)
    TextView tv_name;

    @InjectView(R.id.tv_linkInfo)
    TextView tv_linkInfo;

    @InjectView(R.id.tv_relation)
    TextView tv_relation;

    @InjectView(R.id.tv_address)
    TextView tv_address;

    @InjectView(R.id.tv_birthday)
    TextView tv_birthday;

    @InjectView(R.id.tv_cardid)
    TextView tv_cardid;
    @InjectView(R.id.tv_email)
    TextView tv_email;
    @InjectView(R.id.tv_remark)
    TextView tv_remark;

    public ManagerDetailInfoView(Context context) {
        super(context);
        init();
    }

    public ManagerDetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ManagerDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_managerdetailinfo, null);
        addView(view);
        ButterKnife.inject(this);
    }

    public void setData(HrConsultAgentman result) {
        HrConsultAgentmans agentman = result.getConsultAgentman();
        tv_address.setText(agentman.getLocation());
        tv_birthday.setText(TimeUtils.formatTime(agentman.getBirthday()));
        tv_linkInfo.setText(agentman.getLinkInfo());
        tv_name.setText(agentman.getName());
        tv_relation.setText(agentman.getRelation() + "");
        tv_cardid.setText(agentman.getCardId());
        tv_email.setText(agentman.getEmail());
        tv_remark.setText(agentman.getRemark());
    }

}
