package com.shian.shianlife.mvp.goods.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class StoreOrderDetailsResultBean {

    //订单数据
    private GoodsOrder goodsOrder;

    //财务状态
    private GoodsFinance goodsFinance;

    //订单变化时间
    private List<GoodsOrderChange> goodsOrderChanges;

    //商品信息
    private List<GoodsOrderItem> goodsOrderItem;

    //服务信息
    private GoodsServiceInfo goodsServiceInfo;

    //发票信息
    private GoodsInvoice goodsInvoice;

    //商品及执行单
    private List<GoodsItemPerform> goodsItemPerforms;

    public List<GoodsItemPerform> getGoodsItemPerforms() {
        return goodsItemPerforms;
    }

    public void setGoodsItemPerforms(List<GoodsItemPerform> goodsItemPerforms) {
        this.goodsItemPerforms = goodsItemPerforms;
    }

    public GoodsOrder getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(GoodsOrder goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public GoodsFinance getGoodsFinance() {
        return goodsFinance;
    }

    public void setGoodsFinance(GoodsFinance goodsFinance) {
        this.goodsFinance = goodsFinance;
    }

    public List<GoodsOrderChange> getGoodsOrderChanges() {
        return goodsOrderChanges;
    }

    public void setGoodsOrderChanges(List<GoodsOrderChange> goodsOrderChanges) {
        this.goodsOrderChanges = goodsOrderChanges;
    }

    public List<GoodsOrderItem> getGoodsOrderItem() {
        return goodsOrderItem;
    }

    public void setGoodsOrderItem(List<GoodsOrderItem> goodsOrderItem) {
        this.goodsOrderItem = goodsOrderItem;
    }

    public GoodsServiceInfo getGoodsServiceInfo() {
        return goodsServiceInfo;
    }

    public void setGoodsServiceInfo(GoodsServiceInfo goodsServiceInfo) {
        this.goodsServiceInfo = goodsServiceInfo;
    }

    public GoodsInvoice getGoodsInvoice() {
        return goodsInvoice;
    }

    public void setGoodsInvoice(GoodsInvoice goodsInvoice) {
        this.goodsInvoice = goodsInvoice;
    }
}
