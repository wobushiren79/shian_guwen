package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoice;
import com.shian.shianlife.mvp.goods.bean.GoodsOrder;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderItem;
import com.shian.shianlife.mvp.goods.bean.GoodsServiceInfo;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsOrderCreateView extends BaseMVPView {

    void createGoodsOrderSuccess(GoodsOrderCreateResultBean resultBean);

    void createGoodsOrderFail(String msg);

    /**
     * 获取订单数据
     * @return
     */
    GoodsOrder getGoodsOrder();

    /**
     * 获取发票数据
     * @return
     */
    GoodsInvoice getGoodsInvoice();

    /**
     * 获取服务信息·
     * @return
     */
    GoodsServiceInfo getGoodsServiceInfo();

    /**
     * 获取商品
     * @return
     */
    List<GoodsOrderItem> getGoodsOrderItem();

}
