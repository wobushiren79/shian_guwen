package com.shian.shianlife.mvp.goods.presenter;

import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartChangeNumberPresenter {
    void changeGoodsShoppingCartNumber(GoodsDetailsListResultBean data,Integer changeNumber);
}
