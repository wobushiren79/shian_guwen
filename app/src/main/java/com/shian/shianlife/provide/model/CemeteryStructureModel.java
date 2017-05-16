package com.shian.shianlife.provide.model;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CemeteryStructureModel {
    private String name;//名字
    private long id;//ID
    private long tombSalePrice;//墓位挂牌价，注：仅类型为4墓位号时，才启用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTombSalePrice() {
        return tombSalePrice;
    }

    public void setTombSalePrice(long tombSalePrice) {
        this.tombSalePrice = tombSalePrice;
    }
}
