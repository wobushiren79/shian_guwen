package com.shian.shianlife.mvp.shared.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfo;
import com.shian.shianlife.mvp.shared.model.ISharedGoodsServiceInfoModel;
import com.shian.shianlife.mvp.shared.model.impl.SharedGoodsServiceInfoModelImpl;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsServiceInfoPresenter;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsServiceInfoView;
import com.shian.shianlife.thisenum.GoodsServiceWayEnum;

/**
 * Created by zm.
 */

public class SharedGoodsServiceInfoPresenterImpl implements ISharedGoodsServiceInfoPresenter {
    private ISharedGoodsServiceInfoModel goodsServiceInfoModel;
    private ISharedGoodsServiceInfoView goodsServiceInfoView;

    public SharedGoodsServiceInfoPresenterImpl(ISharedGoodsServiceInfoView goodsServiceInfoView) {
        this.goodsServiceInfoView = goodsServiceInfoView;
        goodsServiceInfoModel = new SharedGoodsServiceInfoModelImpl();
    }

    @Override
    public void clearData() {
        if (goodsServiceInfoView.getContext() == null) {
            goodsServiceInfoView.showToast("数据错误");
            return;
        }
        goodsServiceInfoModel.chearSharedGoodsServiceInfo(goodsServiceInfoView.getContext());
    }

    @Override
    public void getServiceInfoData() {
        if (goodsServiceInfoView.getContext() == null) {
            goodsServiceInfoView.showToast("数据错误");
            return;
        }
        goodsServiceInfoModel.getSharedGoodsServiceInfo(goodsServiceInfoView.getContext(), new OnGetDataListener<SharedGoodsServiceInfo>() {
            @Override
            public void getDataSuccess(SharedGoodsServiceInfo result) {
                goodsServiceInfoView.getSharedGoodsServiceInfoSuccess(result);

                goodsServiceInfoView.setServiceInfoCustomerName(result.getCustomerName());
                goodsServiceInfoView.setServiceInfoCustomerPhone(result.getCustomerPhone());
                goodsServiceInfoView.setServiceInfoServiceWay(result.getServiceWay());
                goodsServiceInfoView.setServiceInfoServiceLocation(result.getServiceLocation());
                goodsServiceInfoView.setServiceInfoServiceDetailsLocation(result.getServiceDetailsLocation());
                goodsServiceInfoView.setServiceInfoServiceTime(result.getServiceTime());

            }

            @Override
            public void getDataFail(String msg) {
                goodsServiceInfoView.getSharedGoodsServiceInfoFail(msg);
            }
        });
    }

    @Override
    public void setServiceInfoData() {
        if (goodsServiceInfoView.getContext() == null) {
            goodsServiceInfoView.showToast("数据错误");
            return;
        }
        if (goodsServiceInfoView.getServiceInfoCustomerName() == null || goodsServiceInfoView.getServiceInfoCustomerName().isEmpty()) {
            goodsServiceInfoView.showToast("联系人不能为空");
            return;
        }
        if (goodsServiceInfoView.getServiceInfoCustomerPhone() == null || goodsServiceInfoView.getServiceInfoCustomerPhone().isEmpty()) {
            goodsServiceInfoView.showToast("电话不能为空");
            return;
        }
        if (goodsServiceInfoView.getServiceInfoServiceWay() == null || goodsServiceInfoView.getServiceInfoServiceWay() == -1) {
            goodsServiceInfoView.showToast("服务方式还未选择");
            return;
        }

        if (goodsServiceInfoView.getServiceInfoServiceLocation() == null || goodsServiceInfoView.getServiceInfoServiceLocation().isEmpty()) {
            goodsServiceInfoView.showToast("服务区域还未选择");
            return;
        }

        if (goodsServiceInfoView.getServiceInfoServiceDetailsLocation() == null || goodsServiceInfoView.getServiceInfoServiceDetailsLocation().isEmpty()) {
            goodsServiceInfoView.showToast("详细地址不能为空");
            return;
        }
        if (goodsServiceInfoView.getServiceInfoServiceWay() == GoodsServiceWayEnum.plan_service.getCode()) {
            if (goodsServiceInfoView.getServiceInfoServiceTime() == null || goodsServiceInfoView.getServiceInfoServiceTime().isEmpty()) {
                goodsServiceInfoView.showToast("预约时间还未选择");
                return;
            }
        }
        SharedGoodsServiceInfo params = new SharedGoodsServiceInfo();
        params.setCustomerName(goodsServiceInfoView.getServiceInfoCustomerName());
        params.setCustomerPhone(goodsServiceInfoView.getServiceInfoCustomerPhone());
        params.setServiceWay(goodsServiceInfoView.getServiceInfoServiceWay());
        params.setServiceLocation(goodsServiceInfoView.getServiceInfoServiceLocation());
        params.setServiceDetailsLocation(goodsServiceInfoView.getServiceInfoServiceDetailsLocation());
        params.setServiceTime(goodsServiceInfoView.getServiceInfoServiceTime());

        goodsServiceInfoModel.setSharedGoodsServiceInfo(goodsServiceInfoView.getContext(), params, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                goodsServiceInfoView.setSharedGoodsServiceInfoSuccess("保存成功");
            }

            @Override
            public void getDataFail(String msg) {
                goodsServiceInfoView.setSharedGoodsServiceInfoSuccess("保存失败");
            }
        });

    }
}
