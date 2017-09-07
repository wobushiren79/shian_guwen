package com.shian.shianlife.mvp.goods.presenter;

import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;

import java.util.List;


/**
 * Created by zm.
 */

public interface IGoodsShoppingCartDeletePresenter {
    void deleteGoodsShoppingCart(GoodsShoppingCartListChildBean itemAllData, List<GoodsShoppingCartListChildBean> listDat);
}
