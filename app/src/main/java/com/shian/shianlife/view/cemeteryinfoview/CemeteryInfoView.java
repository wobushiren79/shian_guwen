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
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/11.
 */

public class CemeteryInfoView extends LinearLayout implements CetemeryTextSelectLayoutView.onSelectedListener {
    private View view;

    EditText mETId;
    EditText mETPlanToMoney;
    EditText mETSaleToMoney;
    EditText mETMoney;
    EditText mETCemeteryMan;
    EditText mETFreeService;
    EditText mETMyService;
    EditText mETRemark;

    CetemeryTextSelectLayoutView mSelectCemeteryName;
    CetemeryTextSelectLayoutView mSelectLocation1;
    CetemeryTextSelectLayoutView mSelectLocation2;
    CetemeryTextSelectLayoutView mSelectLocation3;
    CetemeryTextSelectLayoutView mSelectLocation4;
    CetemeryTextSelectLayoutView mSelectCemeteryType;
    CetemeryTextSelectLayoutView mSelectCemeteryAttribute;
    CetemeryTextSelectLayoutView mSelectPayState;

    List<String> cemeteryNameList = new ArrayList<>();
    List<String> location1List = new ArrayList<>();
    List<String> location2List = new ArrayList<>();
    List<String> location3List = new ArrayList<>();
    List<String> location4List = new ArrayList<>();
    List<String> cemeteryTypeList = new ArrayList<>();
    List<String> cemeteryAttributeList = new ArrayList<>();
    List<String> payStateList = new ArrayList<>();

    public CemeteryInfoView(Context context) {
        this(context, null);
    }

    public CemeteryInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
        initView();
    }

    private void initData() {
        cemeteryTypeList = Utils.stringsToList(SelectData.CEMETERY_TYPE);
        cemeteryAttributeList = Utils.stringsToList(SelectData.CEMETERY_ATTRIBUTE);
        payStateList = Utils.stringsToList(SelectData.CEMETERY_PAYSTATE);
    }

    private void initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_info, this);

        mETId = (EditText) view.findViewById(R.id.et_id);
        mETPlanToMoney = (EditText) view.findViewById(R.id.et_plantomoney);
        mETSaleToMoney = (EditText) view.findViewById(R.id.et_saletomoney);
        mETMoney = (EditText) view.findViewById(R.id.et_money);
        mETCemeteryMan = (EditText) view.findViewById(R.id.et_cemeteryperson);
        mETFreeService= (EditText) view.findViewById(R.id.et_freeservice);
        mETMyService= (EditText) view.findViewById(R.id.et_myservice);
        mETRemark= (EditText) view.findViewById(R.id.et_remark);

        mSelectCemeteryName = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_cemeteryname);
        mSelectLocation1 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_location_1);
        mSelectLocation2 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_location_2);
        mSelectLocation3 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_location_3);
        mSelectLocation4 = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_location_4);
        mSelectCemeteryType = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_cemeterytype);
        mSelectCemeteryAttribute = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_cemeteryattribute);
        mSelectPayState = (CetemeryTextSelectLayoutView) view.findViewById(R.id.select_paystate);

        mSelectCemeteryName.setName("公墓名称：");
        mSelectCemeteryName.setData(cemeteryNameList, 0, this);
        mSelectLocation1.setName("苑 ");
        mSelectLocation2.setName("区 ");
        mSelectLocation3.setName("排 ");
        mSelectLocation4.setName("号 ");
        mSelectLocation1.setData(location1List, 1, this);
        mSelectLocation2.setData(location2List, 2, this);
        mSelectLocation3.setData(location3List, 3, this);
        mSelectLocation4.setData(location4List, 4, this);
        mSelectCemeteryType.setName("墓型：");
        mSelectCemeteryType.setData(cemeteryTypeList, 5, this);
        mSelectCemeteryAttribute.setName("墓穴属性：");
        mSelectCemeteryAttribute.setData(cemeteryAttributeList, 6, this);
        mSelectPayState.setName("支付情况");
        mSelectPayState.setData(payStateList, 7, this);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {

    }
}
