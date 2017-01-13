package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DeadManInfoView extends LinearLayout {
    private View view;

    CetemeryTextSelectLayoutView mSelectDeadManSex1;
    CetemeryTextSelectLayoutView mSelectDeadManState1;
    EditText mETDeadManName1;
    EditText mETDeadManAge1;
    EditText mETDeadManCardId1;
    TextView mTVDeadManTime1;

    CetemeryTextSelectLayoutView mSelectDeadManSex2;
    CetemeryTextSelectLayoutView mSelectDeadManState2;
    EditText mETDeadManName2;
    EditText mETDeadManAge2;
    EditText mETDeadManCardId2;
    TextView mTVDeadManTime2;

    EditText mETRemark;

    public DeadManInfoView(Context context) {
        this(context, null);
    }

    public DeadManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_deadman_info, this);
        initView();
    }

    private void initView() {
        mETDeadManName1 = (EditText) view.findViewById(R.id.et_deadmanname_1);
        mETDeadManAge1 = (EditText) view.findViewById(R.id.et_deadmanage_1);
        mETDeadManCardId1= (EditText) view.findViewById(R.id.et_deadmancardid_1);
        mTVDeadManTime1= (TextView) view.findViewById(R.id.tv_deadmantime_1);
        mSelectDeadManSex1= (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_1);
        mSelectDeadManState1= (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_1);

        mETDeadManName2 = (EditText) view.findViewById(R.id.et_deadmanname_2);
        mETDeadManAge2 = (EditText) view.findViewById(R.id.et_deadmanage_2);
        mETDeadManCardId2= (EditText) view.findViewById(R.id.et_deadmancardid_2);
        mTVDeadManTime2= (TextView) view.findViewById(R.id.tv_deadmantime_2);
        mSelectDeadManSex2= (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_2);
        mSelectDeadManState2= (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_2);

        mETRemark= (EditText) view.findViewById(R.id.et_remark);
    }
}
