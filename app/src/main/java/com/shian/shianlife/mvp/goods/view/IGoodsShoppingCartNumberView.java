package com.shian.shianlife.mvp.goods.view;

import android.content.Context;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartNumberView extends BaseMVPView {
    void getShoppingCartNumberSuccess(GoodsShoppingCartNumberResultBean resultBean);

    void getShoppingCartNumberFail(String msg);

    void setShoppingCartNumber(String number);

}
