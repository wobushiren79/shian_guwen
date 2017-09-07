package com.shian.shianlife.mvp.goods.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateResultBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartCreateView extends BaseMVPView {
    void createGoodsShoppingCartSuccess(GoodsShoppingCartCreateResultBean resultBean);

    void createGoodsShoppingCartFail(String msg);

    Long getGoodsId();

    Integer getSpecNum();

    Integer getChannelId();

    Long getClassifyAttrId();

    Long getClassifyId();


    Long getGoodsSpecId();


}
