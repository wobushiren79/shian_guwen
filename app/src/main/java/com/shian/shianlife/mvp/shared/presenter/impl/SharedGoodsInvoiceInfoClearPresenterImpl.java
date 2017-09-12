package com.shian.shianlife.mvp.shared.presenter.impl;

import com.shian.shianlife.mvp.shared.model.ISharedGoodsInvoiceInfoModel;
import com.shian.shianlife.mvp.shared.model.impl.SharedGoodsInvoiceInfoModelImpl;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsInvoiceInfoClearPresenter;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsInvoiceInfoClearView;

/**
 * Created by zm.
 */

public class SharedGoodsInvoiceInfoClearPresenterImpl implements ISharedGoodsInvoiceInfoClearPresenter {
    private ISharedGoodsInvoiceInfoClearView sharedGoodsInvoiceInfoView;
    private ISharedGoodsInvoiceInfoModel sharedGoodsInvoiceInfoModel;

    public SharedGoodsInvoiceInfoClearPresenterImpl(ISharedGoodsInvoiceInfoClearView sharedGoodsInvoiceInfoView) {
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
}
