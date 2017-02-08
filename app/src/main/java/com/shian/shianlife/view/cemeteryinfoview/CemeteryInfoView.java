package com.shian.shianlife.view.cemeteryinfoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessOne;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessOne;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/11.
 */

public class CemeteryInfoView extends BaseInfoView implements CetemeryTextSelectLayoutView.onSelectedListener {
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
        getData();
    }

    private void getData() {
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkSuccessOne(getContext(), params, new HttpResponseHandler<HrGetCemeteryTalkSuccessOne>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkSuccessOne result) {
                Utils.LogVPrint("getOrderNum:"+  result.getOrderNum());
                Utils.LogVPrint("isSaveCan:"+  result.isSaveCan());
                Utils.LogVPrint("getCemeteryName:"+  result.getCemeteryName());
                Utils.LogVPrint("getGarden:"+  result.getGarden());
                Utils.LogVPrint("getDistrict:"+  result.getDistrict());
                Utils.LogVPrint("getPlatoon:"+  result.getPlatoon());
                Utils.LogVPrint("getMark:"+  result.getMark());
                Utils.LogVPrint("getCemeteryType:"+  result.getCemeteryType());
                Utils.LogVPrint("getCemeteryProperties:"+  result.getCemeteryProperties());
                Utils.LogVPrint("getPlanSale:"+  result.getPlanSale());
                Utils.LogVPrint("getSaleMoney:"+  result.getSaleMoney());
                Utils.LogVPrint("getPayState:"+  result.getPayState());
                Utils.LogVPrint("getMoneyPay:"+  result.getMoneyPay());
                Utils.LogVPrint("getCemeteryReceive:"+  result.getCemeteryReceive());
                Utils.LogVPrint("getFreeService:"+  result.getFreeService());
                Utils.LogVPrint("getChoiceService:"+  result.getChoiceService());
                Utils.LogVPrint("getRemark:"+  result.getRemark());

                mETId.setText(result.getOrderNum());
                mSelectCemeteryName.setString(result.getCemeteryName());
                mSelectLocation1.setString(result.getGarden());
                mSelectLocation2.setString(result.getDistrict());
                mSelectLocation3.setString(result.getPlatoon());
                mSelectLocation4.setString(result.getMark());
                mSelectCemeteryType.setString(result.getCemeteryType());
                mSelectCemeteryAttribute.setString(result.getCemeteryProperties());
                mETPlanToMoney.setText(result.getPlanSale());
                mETSaleToMoney.setText(result.getSaleMoney());
                mSelectPayState.setString(result.getPayState());
                mETMoney.setText(result.getMoneyPay());
                mETCemeteryMan.setText(result.getCemeteryReceive());
                mETFreeService.setText(result.getFreeService());
                mETMyService.setText(result.getChoiceService());
                mETRemark.setText(result.getRemark());
            }

            @Override
            public void onError(String message) {

            }
        });
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
        mETFreeService = (EditText) view.findViewById(R.id.et_freeservice);
        mETMyService = (EditText) view.findViewById(R.id.et_myservice);
        mETRemark = (EditText) view.findViewById(R.id.et_remark);

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
        mSelectPayState.setName("支付情况：");
        mSelectPayState.setData(payStateList, 7, this);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {

    }

    public void setStateShow() {
        mETId.setFocusable(false);
        mETPlanToMoney.setFocusable(false);
        mETSaleToMoney.setFocusable(false);
        mETMoney.setFocusable(false);
        mETCemeteryMan.setFocusable(false);
        mETFreeService.setFocusable(false);
        mETMyService.setFocusable(false);
        mETRemark.setFocusable(false);

        mSelectCemeteryName.setStateShow();
        mSelectLocation1.setStateShow();
        mSelectLocation2.setStateShow();
        mSelectLocation3.setStateShow();
        mSelectLocation4.setStateShow();
        mSelectCemeteryType.setStateShow();
        mSelectCemeteryAttribute.setStateShow();
        mSelectPayState.setStateShow();
    }

    @Override
    public void saveData() {
        HpSaveCemeteryTalkSuccessOne params=new HpSaveCemeteryTalkSuccessOne();
        params.setBespeakId(beSpeakId);
        params.setSaveType(changeState);
        params.setOrderNum(mETId.getText().toString());
        params.setCemeteryName(mSelectCemeteryName.getSelectedData());
        params.setGarden(mSelectLocation1.getSelectedData());
        params.setDistrict(mSelectLocation2.getSelectedData());
        params.setPlatoon(mSelectLocation3.getSelectedData());
        params.setMark(mSelectLocation4.getSelectedData());
        params.setCemeteryType(mSelectCemeteryType.getSelectedData());
        params.setCemeteryProperties(mSelectCemeteryAttribute.getSelectedData());
        params.setPlanSale(mETPlanToMoney.getText().toString());
        params.setSaleMoney(mETSaleToMoney.getText().toString());
        params.setPayState(mSelectPayState.getSelectedData());
        params.setMoneyPay(mETMoney.getText().toString());
        params.setCemeteryReceive(mETCemeteryMan.getText().toString());
        params.setFreeService(mETFreeService.getText().toString());
        params.setChoiceService(mETMyService.getText().toString());
        params.setRemark(mETRemark.getText().toString());

        if(params.getBespeakId()==-1){
            ToastUtils.show(getContext(),"订单数据异常，请退出重新加载");
            return;
        }
        if(params.getOrderNum().isEmpty()){
            ToastUtils.show(getContext(),"订单编号不能为空");
            return;
        }
        if(params.getCemeteryName().isEmpty()){
            ToastUtils.show(getContext(),"公墓名称不能为空");
            return;
        }
        if(params.getGarden().isEmpty()){
            ToastUtils.show(getContext(),"苑不能为空");
            return;
        }
        if(params.getDistrict().isEmpty()){
            ToastUtils.show(getContext(),"区不能为空");
            return;
        }
        if(params.getPlatoon().isEmpty()){
            ToastUtils.show(getContext(),"排不能为空");
            return;
        }
        if(params.getMark().isEmpty()){
            ToastUtils.show(getContext(),"号不能为空");
            return;
        }
        if(params.getCemeteryType().isEmpty()){
            ToastUtils.show(getContext(),"墓型不能为空");
            return;
        }
        if(params.getCemeteryProperties().isEmpty()){
            ToastUtils.show(getContext(),"墓穴属性不能为空");
            return;
        }
        if(params.getPlanSale().isEmpty()){
            ToastUtils.show(getContext(),"挂牌价不能为空");
            return;
        }
        if(params.getSaleMoney().isEmpty()){
            ToastUtils.show(getContext(),"成交价不能为空");
            return;
        }
        if(params.getPayState().isEmpty()){
            ToastUtils.show(getContext(),"支付情况");
            return;
        }
        if(params.getMoneyPay().isEmpty()){
            ToastUtils.show(getContext(),"金额不能为空");
            return;
        }
        if(params.getCemeteryName().isEmpty()){
            ToastUtils.show(getContext(),"公墓接待不能为空");
            return;
        }
        if(params.getFreeService().isEmpty()){
            ToastUtils.show(getContext(),"赠送服务不能为空");
            return;
        }
        if(params.getChoiceService().isEmpty()){
            ToastUtils.show(getContext(),"自选附属服务不能为空");
            return;
        }
        MHttpManagerFactory.getAccountManager().saveCemeteryTalkSuccessOne(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }
            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(),"数据提交成功");
            }
            @Override
            public void onError(String message) {

            }
        });

    }
}
