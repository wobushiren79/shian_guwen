package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsQueryListResultBean {

    /**
     * title_img : Goods/2017-08-29/59a52bd887195.jpg
     * id : 19
     * total : 10000.00
     * sale_amount : 10
     * name : 骨灰钻石
     * goods_slogan : （不含首饰加工费）
     * price : 12000.00
     */

    private String title_img;
    private Long id;
    private Float total;
    private Integer sale_amount;
    private String name;
    private String goods_slogan;
    private Float price;
    private Integer is_package;

    public Integer getIs_package() {
        return is_package;
    }

    public void setIs_package(Integer is_package) {
        this.is_package = is_package;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

}
