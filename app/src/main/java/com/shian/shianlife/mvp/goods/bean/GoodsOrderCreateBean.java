package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsOrderCreateBean extends BaseHttpParams {
    private GoodsOrder goodsOrder;
    private GoodsInvoice goodsInvoice;
    private GoodsServiceInfo goodsServiceInfo;
    private List<GoodsOrderItem> goodsOrderItems;
    private List<GoodsOrderItem> goodsPackages;

    public GoodsOrder getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(GoodsOrder goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public GoodsInvoice getGoodsInvoice() {
        return goodsInvoice;
    }

    public void setGoodsInvoice(GoodsInvoice goodsInvoice) {
        this.goodsInvoice = goodsInvoice;
    }

    public GoodsServiceInfo getGoodsServiceInfo() {
        return goodsServiceInfo;
    }

    public void setGoodsServiceInfo(GoodsServiceInfo goodsServiceInfo) {
        this.goodsServiceInfo = goodsServiceInfo;
    }

    public List<GoodsOrderItem> getGoodsOrderItems() {
        return goodsOrderItems;
    }

    public void setGoodsOrderItems(List<GoodsOrderItem> goodsOrderItems) {
        this.goodsOrderItems = goodsOrderItems;
    }

    public List<GoodsOrderItem> getGoodsPackages() {
        return goodsPackages;
    }

    public void setGoodsPackages(List<GoodsOrderItem> goodsPackages) {
        this.goodsPackages = goodsPackages;
    }
}
