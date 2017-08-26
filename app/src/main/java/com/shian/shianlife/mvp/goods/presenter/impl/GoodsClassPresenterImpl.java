package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsClassModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsClassView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassPresenterImpl implements IGoodsClassPresenter {
    private IGoodsClassModel goodsClassModel;
    private IGoodsClassView goodsClassView;

    public GoodsClassPresenterImpl(IGoodsClassView goodsClassView) {
        this.goodsClassView = goodsClassView;
        goodsClassModel = new GoodsClassModelImpl();
    }

    @Override
    public void getGoodsClassData() {
        if (goodsClassView.getContext() == null || goodsClassView.getChannelId() == null) {
            goodsClassView.showToast("数据错误");
            return;
        }
//        if (AppContansts.systemLoginInfo == null || AppContansts.systemLoginInfo.getUserId() == null) {
//            goodsClassView.showToast("未登录账号，请退出重新登陆");
//            return;
//        }
        //        Long userId = AppContansts.systemLoginInfo.getUserId();
        Integer channelId = goodsClassView.getChannelId();


        GoodsClassBean goodsClassBean = new GoodsClassBean();
        goodsClassBean.setChannel_id(channelId);
//        goodsClassBean.setUser_id(userId);
        goodsClassBean.setUser_id(12L);

        goodsClassModel.getGoodsClassData(goodsClassView.getContext(), goodsClassBean, new OnGetDataListener<List<GoodsClassResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsClassResultBean> result) {
                goodsClassView.getGoodsClassDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsClassView.getGoodsClassDataFail(msg);
            }
        });
    }
}
