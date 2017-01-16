package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class AgentManInfoView extends LinearLayout implements CetemeryTextSelectLayoutView.onSelectedListener {
    private View view;

    CetemeryTextSelectLayoutView mSelectRelation;

    MapSelectLayoutView mMapAgentManLocation;

    EditText mETAgentManName;
    EditText mETAgentManPhone;
    EditText mETAgentManCardId;
    EditText mETAgentManEmail;
    EditText mETAgentManRemark;

    List<String> relationList = new ArrayList<>();

    public AgentManInfoView(Context context) {
        this(context, null);
    }

    public AgentManInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_agentman_info, this);

        initData();
        initView();

    }

    private void initData() {
        relationList = Utils.stringsToList(SelectData.CEMETERY_RELATION);
    }

    private void initView() {
        mETAgentManName = (EditText) view.findViewById(R.id.et_agentmanname);
        mETAgentManPhone = (EditText) view.findViewById(R.id.et_agentmanphone);
        mSelectRelation = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_relation);
        mMapAgentManLocation = (MapSelectLayoutView) view.findViewById(R.id.mapselect);
        mETAgentManCardId = (EditText) view.findViewById(R.id.et_agentmancardid);
        mETAgentManEmail = (EditText) view.findViewById(R.id.et_agentemail);
        mETAgentManRemark = (EditText) view.findViewById(R.id.et_remark);

        mSelectRelation.setName("经办人是使用者的：");
        mSelectRelation.setData(relationList, 0, this);

        mMapAgentManLocation.setData(0, new ArrayList<String>());
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {

    }

    public void setStateShow() {
        mSelectRelation.setStateShow();
        mMapAgentManLocation.setStateShow();
        mETAgentManName.setFocusable(false);
        mETAgentManPhone.setFocusable(false);
        mETAgentManCardId.setFocusable(false);
        mETAgentManEmail.setFocusable(false);
        mETAgentManRemark.setFocusable(false);
    }
}
