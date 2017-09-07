package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartChangeNumberView extends BaseMVPView {

    void changeShoppingCartNumberSuccess(GoodsShoppingCartChangeNumberResultBean resultBean);

    void changeShoppingCartNumberFail(String msg);


}
