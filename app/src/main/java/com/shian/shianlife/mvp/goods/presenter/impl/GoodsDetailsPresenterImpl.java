package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsDetailsModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsDetailsModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class GoodsDetailsPresenterImpl implements IGoodsDetailsPresenter {
    private IGoodsDetailsView goodsDetailsView;
    private IGoodsDetailsModel goodsDetailsModel;

    public GoodsDetailsPresenterImpl(IGoodsDetailsView goodsDetailsView) {
        this.goodsDetailsView = goodsDetailsView;
        goodsDetailsModel = new GoodsDetailsModelImpl();
    }

    @Override
    public void getGoodsDetails() {
        if (goodsDetailsView.getContext() == null) {
            goodsDetailsView.showToast("数据错误");
            return;
        }
        if (AppContansts.goodsChannelId == null) {
            goodsDetailsView.showToast("数据错误");
            return;
        }
        if (goodsDetailsView.getGoodsId() == null || goodsDetailsView.getGoodsId() == -1) {
            goodsDetailsView.showToast("数据错误");
            return;
        }
        GoodsDetailsBean params = new GoodsDetailsBean();
        params.setChannel_id(AppContansts.goodsChannelId);
        params.setGoods_id(goodsDetailsView.getGoodsId());
        goodsDetailsModel.getGoodsDetails(goodsDetailsView.getContext(), params, new OnGetDataListener<GoodsDetailsResultBean>() {
            @Override
            public void getDataSuccess(GoodsDetailsResultBean result) {
                goodsDetailsView.getGoodsDetailsSuccess(result);

                //設置轮播图片
                List<GoodsDetailsResultBean.ImgsBean> carouselPic = result.getImgs();
                if (carouselPic != null) {
                    List<String> picUrl = new ArrayList<>();
                    for (GoodsDetailsResultBean.ImgsBean item : carouselPic) {
                        picUrl.add(AppContansts.Goods_PicUrl + "/" + item.getPic_add());
                    }
                    goodsDetailsView.setCarouselPic(picUrl);
                }

                StringBuffer goodsName = new StringBuffer();
                //設置商品名稱
                if (result.getName() != null)
                    goodsName.append(result.getName() + " ");
                //設置商品描述
                if (result.getGoods_slogan() != null)
                    goodsName.append(result.getGoods_slogan());
                goodsDetailsView.setGoodsName(goodsName.toString());
            }

            @Override
            public void getDataFail(String msg) {
                goodsDetailsView.getGoodsDetailsFail(msg);
            }
        });
    }
}
