package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitResultBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderSubmitView extends BaseMVPView {

    Long getOrderId();

    void submitGoodsOrderSuccess(GoodsOrderSubmitResultBean resultBean);

    void submitGoodsOrderFail(String msg);
}
