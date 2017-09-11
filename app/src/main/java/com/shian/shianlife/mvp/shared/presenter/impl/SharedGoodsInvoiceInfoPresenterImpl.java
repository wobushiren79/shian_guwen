package com.shian.shianlife.mvp.shared.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.mvp.shared.model.ISharedGoodsInvoiceInfoModel;
import com.shian.shianlife.mvp.shared.model.impl.SharedGoodsInvoiceInfoModelImpl;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsInvoiceInfoPresenter;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsInvoiceInfoView;
import com.shian.shianlife.thisenum.GoodsFinanceDeliveryEnum;
import com.shian.shianlife.thisenum.GoodsInvoiceTitleTypeEnum;

/**
 * Created by zm.
 */

public class SharedGoodsInvoiceInfoPresenterImpl implements ISharedGoodsInvoiceInfoPresenter {
    private ISharedGoodsInvoiceInfoView sharedGoodsInvoiceInfoView;
    private ISharedGoodsInvoiceInfoModel sharedGoodsInvoiceInfoModel;

    public SharedGoodsInvoiceInfoPresenterImpl(ISharedGoodsInvoiceInfoView sharedGoodsInvoiceInfoView) {
        this.sharedGoodsInvoiceInfoView = sharedGoodsInvoiceInfoView;
        sharedGoodsInvoiceInfoModel = new SharedGoodsInvoiceInfoModelImpl();
    }

    @Override
    public void clearData() {
        if (sharedGoodsInvoiceInfoView.getContext() == null) {
            sharedGoodsInvoiceInfoView.showToast("数据错误");
            return;
        }
        sharedGoodsInvoiceInfoModel.chearSharedGoodsInvoiceInfo(sharedGoodsInvoiceInfoView.getContext());
    }

    @Override
    public void getServiceInfoData() {
        if (sharedGoodsInvoiceInfoView.getContext() == null) {
            sharedGoodsInvoiceInfoView.showToast("数据错误");
            return;
        }
        sharedGoodsInvoiceInfoModel.getSharedGoodsInvoiceInfo(sharedGoodsInvoiceInfoView.getContext(), new OnGetDataListener<SharedGoodsInvoiceInfoBean>() {
            @Override
            public void getDataSuccess(SharedGoodsInvoiceInfoBean result) {
                sharedGoodsInvoiceInfoView.getSharedGoodsInvoiceInfoSuccess(result);

                sharedGoodsInvoiceInfoView.setInvoiceType(result.getInvoiceType());
                sharedGoodsInvoiceInfoView.setIsNeedInvoice(result.getIsNeedInvoice());
                sharedGoodsInvoiceInfoView.setInvoiceCompanyName(result.getCompanyName());
                sharedGoodsInvoiceInfoView.setInvoiceCompanyTaxNumber(result.getCompanyTaxNumber());
                sharedGoodsInvoiceInfoView.setInvoiceCompanyRemark(result.getCompanyRemark());
                sharedGoodsInvoiceInfoView.setInvoiceReceiverName(result.getReceiverName());
                sharedGoodsInvoiceInfoView.setInvoiceReceiverPhone(result.getReceiverPhone());
                sharedGoodsInvoiceInfoView.setInvoiceReceiverDetailsLocation(result.getReceiverDetailsLocation());
                sharedGoodsInvoiceInfoView.setInvoiceReceiverLocation(result.getReceiverLocation());
            }

            @Override
            public void getDataFail(String msg) {
                sharedGoodsInvoiceInfoView.getSharedGoodsInvoiceInfoFail(msg);
            }
        });
    }

    @Override
    public void setServiceInfoData() {
        if (sharedGoodsInvoiceInfoView.getContext() == null) {
            sharedGoodsInvoiceInfoView.showToast("数据错误");
            return;
        }

        SharedGoodsInvoiceInfoBean params = new SharedGoodsInvoiceInfoBean();

        if (sharedGoodsInvoiceInfoView.getIsNeedInvoice() == null || sharedGoodsInvoiceInfoView.getIsNeedInvoice() == -1) {
            sharedGoodsInvoiceInfoView.showToast("还未选择是否需要发票");
            return;
        }
        params.setIsNeedInvoice(sharedGoodsInvoiceInfoView.getIsNeedInvoice());
        if (sharedGoodsInvoiceInfoView.getIsNeedInvoice() == GoodsFinanceDeliveryEnum.hasinvoicement.getCode()) {
            if (sharedGoodsInvoiceInfoView.getInvoiceType() == null || sharedGoodsInvoiceInfoView.getInvoiceType() == -1) {
                sharedGoodsInvoiceInfoView.showToast("还未选择发票类型");
                return;
            }
            params.setInvoiceType(sharedGoodsInvoiceInfoView.getInvoiceType());
            if (sharedGoodsInvoiceInfoView.getInvoiceType() == GoodsInvoiceTitleTypeEnum.company.getCode()) {
                if (sharedGoodsInvoiceInfoView.getInvoiceCompanyName() == null || sharedGoodsInvoiceInfoView.getInvoiceCompanyName().isEmpty()) {
                    sharedGoodsInvoiceInfoView.showToast("还未填写单位全称");
                    return;
                }
                if (sharedGoodsInvoiceInfoView.getInvoiceCompanyTaxNumber() == null || sharedGoodsInvoiceInfoView.getInvoiceCompanyTaxNumber().isEmpty()) {
                    sharedGoodsInvoiceInfoView.showToast("还未填写税号");
                    return;
                }
//            if(sharedGoodsInvoiceInfoView.getInvoiceCompanyRemark()==null||sharedGoodsInvoiceInfoView.getInvoiceCompanyRemark().isEmpty()){
//                sharedGoodsInvoiceInfoView.showToast("还未填写备注");
//                return;
//            }

                params.setCompanyName(sharedGoodsInvoiceInfoView.getInvoiceCompanyName());
                params.setCompanyRemark(sharedGoodsInvoiceInfoView.getInvoiceCompanyRemark());
                params.setCompanyTaxNumber(sharedGoodsInvoiceInfoView.getInvoiceCompanyTaxNumber());
            }
            if (sharedGoodsInvoiceInfoView.getInvoiceReceiverName() == null || sharedGoodsInvoiceInfoView.getInvoiceReceiverName().isEmpty()) {
                sharedGoodsInvoiceInfoView.showToast("还未填写收件人姓名");
                return;
            }
            if (sharedGoodsInvoiceInfoView.getInvoiceReceiverPhone() == null || sharedGoodsInvoiceInfoView.getInvoiceReceiverPhone().isEmpty()) {
                sharedGoodsInvoiceInfoView.showToast("还未填写收件人电话");
                return;
            }
            if (sharedGoodsInvoiceInfoView.getInvoiceReceiverLocation() == null || sharedGoodsInvoiceInfoView.getInvoiceReceiverLocation().isEmpty()) {
                sharedGoodsInvoiceInfoView.showToast("还未填写收件人地址");
                return;
            }
            if (sharedGoodsInvoiceInfoView.getInvoiceReceiverDetailsLocation() == null || sharedGoodsInvoiceInfoView.getInvoiceReceiverDetailsLocation().isEmpty()) {
                sharedGoodsInvoiceInfoView.showToast("还未填写详细地址");
                return;
            }

            params.setReceiverName(sharedGoodsInvoiceInfoView.getInvoiceReceiverName());
            params.setReceiverPhone(sharedGoodsInvoiceInfoView.getInvoiceReceiverPhone());
            params.setReceiverLocation(sharedGoodsInvoiceInfoView.getInvoiceReceiverLocation());
            params.setReceiverDetailsLocation(sharedGoodsInvoiceInfoView.getInvoiceReceiverDetailsLocation());
        }
        sharedGoodsInvoiceInfoModel.setSharedGoodsInvoiceInfo(sharedGoodsInvoiceInfoView.getContext(), params, new OnGetDataListener<SharedGoodsInvoiceInfoBean>() {
            @Override
            public void getDataSuccess(SharedGoodsInvoiceInfoBean result) {
                sharedGoodsInvoiceInfoView.setSharedGoodsInvoiceInfoSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                sharedGoodsInvoiceInfoView.setSharedGoodsInvoiceInfoFail(msg);
            }
        });
    }
}
