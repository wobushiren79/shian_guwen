package com.shian.shianlife.mvp.goods.bean;


import java.util.List;

/**
 * Created by liufl on 2017/8/10.
 */

public class GoodsItemPerform extends GoodsOrderItem {
    private GoodsPerform goodsPerform;
    private GoodsExpress goodsExpress;

    /**
     * 套餐信息
     */
    private List<GoodsItemPerform> goodsItemPerforms;

    public List<GoodsItemPerform> getGoodsItemPerforms() {
        return goodsItemPerforms;
    }

    public void setGoodsItemPerforms(List<GoodsItemPerform> goodsItemPerforms) {
        this.goodsItemPerforms = goodsItemPerforms;
    }

    public GoodsExpress getGoodsExpress() {
        return goodsExpress;
    }

    public void setGoodsExpress(GoodsExpress goodsExpress) {
        this.goodsExpress = goodsExpress;
    }

    public GoodsPerform getGoodsPerform() {
        return goodsPerform;
    }

    public void setGoodsPerform(GoodsPerform goodsPerform) {
        this.goodsPerform = goodsPerform;
    }


}
