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
}
