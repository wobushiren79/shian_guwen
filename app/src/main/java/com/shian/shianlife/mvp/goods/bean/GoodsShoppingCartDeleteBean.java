package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsShoppingCartDeleteBean extends BaseHttpParams {
    private List<Long> shoppingCartIds; //购物车ID

    public List<Long> getShoppingCartIds() {
        return shoppingCartIds;
    }

    public void setShoppingCartIds(List<Long> shoppingCartIds) {
        this.shoppingCartIds = shoppingCartIds;
    }
}
