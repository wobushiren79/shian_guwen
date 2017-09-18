package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsResultBean {
    /**
     * title_img : Goods/2017-09-08/59b238037ca71.jpg
     * id : 51
     * total : 3600.00
     * sale_amount : 20
     * name : 【思君·瑞丽】（现代）
     * goods_slogan : 思君如流水，但无有穷时
     * price : 2499.00
     * adviser_price : 999.00
     */

    private String title_img;
    private Long id;
    private Float total;
    private Integer sale_amount;
    private String name;
    private String goods_slogan;
    private Float price;
    private Float adviser_price;

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(Integer sale_amount) {
        this.sale_amount = sale_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoods_slogan() {
        return goods_slogan;
    }

    public void setGoods_slogan(String goods_slogan) {
        this.goods_slogan = goods_slogan;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getAdviser_price() {
        return adviser_price;
    }

    public void setAdviser_price(Float adviser_price) {
        this.adviser_price = adviser_price;
    }
}
