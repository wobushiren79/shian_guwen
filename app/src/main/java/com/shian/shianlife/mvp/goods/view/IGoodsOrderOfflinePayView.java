package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayResultBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderOfflinePayView extends BaseMVPView {
    void payOfflineSuccess(GoodsOrderOfflinePayResultBean resultBean);

    void payOfflineFail(String msg);

    Long getOrderId();

    Integer getPayPrice();
}
