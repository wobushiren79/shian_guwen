package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartDeleteView extends BaseMVPView {
    void deleteGoodsShoppingCartSuccess(GoodsShoppingCartDeleteResultBean resultBean);

    void deleteGoodsShoppingCartFail(String msg);
}
