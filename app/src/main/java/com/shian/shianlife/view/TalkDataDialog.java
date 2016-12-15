package com.shian.shianlife.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.result.HrGetTalkFail;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TalkDataDialog extends Dialog {
    View view;

    TextView mTvName;
    TextView mTvHealth;
    TextView mTvLocation;
    TextView mTvRelation;
    TextView mTvPlanLocation;
    TextView mTvProject;
    TextView mTvRemark;
    TextView mTvResult;
    TextView mTvResultTime;
    HrGetTalkFail result;

    public TalkDataDialog(Context context, int themeResId, HrGetTalkFail result) {
        super(context, themeResId);
        this.result = result;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_talkdialog, null);
        super.setContentView(view);
        initView(view);
    }

    private void initView(View view) {
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvHealth = (TextView) view.findViewById(R.id.tv_health);
        mTvLocation = (TextView) view.findViewById(R.id.tv_location);
        mTvRelation = (TextView) view.findViewById(R.id.tv_relation);
        mTvPlanLocation = (TextView) view.findViewById(R.id.tv_planlocation);
        mTvProject = (TextView) view.findViewById(R.id.tv_project);
        mTvRemark = (TextView) view.findViewById(R.id.tv_remark);
        mTvResult = (TextView) view.findViewById(R.id.tv_result);
        mTvResultTime = (TextView) view.findViewById(R.id.tv_resulttime);

        mTvName.setText(result.getDeadName());
        mTvHealth.setText(result.getHealth());
        mTvLocation.setText(result.getLocation());
        mTvRelation.setText(result.getRelation());
        mTvPlanLocation.setText(result.getPlanLocation());
        mTvProject.setText(result.getProject());
        mTvRemark.setText(result.getRemark());
        if (result.isResult()) {
            mTvResult.setText("预约二次洽谈");
        } else {
            mTvResult.setText("未预约");
        }
        mTvResultTime.setText(result.getResultTime());
    }

    @Override
    public void show() {
        super.show();
    }
}
