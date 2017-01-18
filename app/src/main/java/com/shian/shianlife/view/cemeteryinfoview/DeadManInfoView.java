package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class DeadManInfoView extends LinearLayout implements CetemeryTextSelectLayoutView.onSelectedListener {
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

    List<String> stateList = new ArrayList<>();
    List<String> sexList = new ArrayList<>();

    public DeadManInfoView(Context context) {
        this(context, null);
    }

    public DeadManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_deadman_info, this);

        initData();
        initView();
    }

    private void initData() {
        sexList = Utils.stringsToList(SelectData.CEMETERY_SEX);
        stateList = Utils.stringsToList(SelectData.CEMETERY_STATE);
    }

    private void initView() {
        mETDeadManName1 = (EditText) view.findViewById(R.id.et_deadmanname_1);
        mETDeadManAge1 = (EditText) view.findViewById(R.id.et_deadmanage_1);
        mETDeadManCardId1 = (EditText) view.findViewById(R.id.et_deadmancardid_1);
        mTVDeadManTime1 = (TextView) view.findViewById(R.id.tv_deadmantime_1);
        mSelectDeadManSex1 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_1);
        mSelectDeadManState1 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_1);

        mETDeadManName2 = (EditText) view.findViewById(R.id.et_deadmanname_2);
        mETDeadManAge2 = (EditText) view.findViewById(R.id.et_deadmanage_2);
        mETDeadManCardId2 = (EditText) view.findViewById(R.id.et_deadmancardid_2);
        mTVDeadManTime2 = (TextView) view.findViewById(R.id.tv_deadmantime_2);
        mSelectDeadManSex2 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmansex_2);
        mSelectDeadManState2 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_deadmanstate_2);

        mETRemark = (EditText) view.findViewById(R.id.et_remark);

        mSelectDeadManSex1.setName("使用者1性别");
        mSelectDeadManSex2.setName("使用者2性别");
        mSelectDeadManState1.setName("使用者1现状");
        mSelectDeadManState2.setName("使用者2现状");

        mSelectDeadManSex1.setData(sexList, 0, this);
        mSelectDeadManSex2.setData(sexList, 1, this);
        mSelectDeadManState1.setData(stateList, 2, this);
        mSelectDeadManState2.setData(stateList, 3, this);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {
        switch (num) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void setStateShow() {
        mSelectDeadManSex1.setStateShow();
        mSelectDeadManState1.setStateShow();

        mETDeadManName1.setFocusable(false);
        mETDeadManAge1.setFocusable(false);
        mETDeadManCardId1.setFocusable(false);
        mTVDeadManTime1.setClickable(false);

        mSelectDeadManSex2.setStateShow();
        mSelectDeadManState2.setStateShow();

        mETDeadManName2.setFocusable(false);
        mETDeadManAge2.setFocusable(false);
        mETDeadManCardId2.setFocusable(false);
        mTVDeadManTime2.setClickable(false);

        mETRemark.setFocusable(false);
    }
}
