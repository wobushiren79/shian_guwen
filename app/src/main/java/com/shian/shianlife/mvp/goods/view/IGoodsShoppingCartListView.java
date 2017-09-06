package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartListView extends BaseMVPView {
    void getShoppingCartListDataSuccess(GoodsShoppingCartListResultBean resultBean);

    void getShoppingCartListDataFail(String msg);

    Integer getShoppingCartPageSize();

    Integer getShoppingCartPageNumber();
}
