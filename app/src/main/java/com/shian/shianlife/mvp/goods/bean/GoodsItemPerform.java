package com.shian.shianlife.mvp.goods.bean;


/**
 * Created by liufl on 2017/8/10.
 */

public class GoodsItemPerform extends GoodsOrderItem {
    private GoodsPerform goodsPerform;
    private GoodsExpress goodsExpress;

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
