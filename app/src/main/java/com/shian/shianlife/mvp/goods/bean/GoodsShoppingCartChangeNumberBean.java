package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class GoodsShoppingCartChangeNumberBean extends BaseHttpParams {
    private Long id;
    private Integer specNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }
}
