package com.shian.shianlife.bean;

import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;

import java.io.Serializable;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListChildBean implements Serializable{
    private GoodsDetailsListResultBean resultBean;


    private boolean isCheckGoods;//是否選中商品

    public GoodsDetailsListResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(GoodsDetailsListResultBean resultBean) {
        this.resultBean = resultBean;
    }


    public boolean isCheckGoods() {
        return isCheckGoods;
    }

    public void setCheckGoods(boolean checkGoods) {
        isCheckGoods = checkGoods;
    }
}
