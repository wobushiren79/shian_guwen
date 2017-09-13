package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderInfoView extends BaseMVPView {

    void getGoodsOrderInfoSuccess(GoodsOrderInfoResultBean resultBean);

    void getGoodsOrderInfoFail(String msg);

    Long getOrderId();

    void setOrderNumber(String orderNumber);

    void setAdviserPrice(Integer price);

    /**
     * 积分
     * @param price
     */
    void setCoupon(Integer price);

    /**
     * 运费
     */
    void setFreight(Integer price);


    /**
     * 积分
     */
    void setIntegral(Integer price);


    /**
     * 实际金额
     * @param price
     */
    void setPayPrice(Integer price);



}
