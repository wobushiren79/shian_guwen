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
                //设置价格范围
                if (result.getPrice() == null)
                    goodsDetailsView.setPriceRange("￥：" + "暂无");
                else
                    goodsDetailsView.setPriceRange("￥：" + result.getPrice());

                //设置原价
                if (result.getTotal() != null)
                    goodsDetailsView.setPriceOriginal("" + result.getTotal());

                //设置销售数量
                if (result.getSale_amount() != null)
                    goodsDetailsView.setSaleNumber("已销售：" + result.getSale_amount());

                //适用地区
                if (result.getApply_area() != null) {
                    StringBuffer location = new StringBuffer();
                    for (int i = 0; i < result.getApply_area().size(); i++) {
                        GoodsDetailsResultBean.ApplyAreaBean item = result.getApply_area().get(i);
                        if (i != 0)
                            location.append(",");
                        location.append(item.getName());
                    }
                    goodsDetailsView.setLocation("适用地区：" + location.toString());
                }

                //设置
                if (result.getSpecprice() != null)
                    goodsDetailsView.setGoodsSpecSelectData(result.getSpecprice());


            }

            @Override
            public void getDataFail(String msg) {
                goodsDetailsView.getGoodsDetailsFail(msg);
            }
        });
    }
}
