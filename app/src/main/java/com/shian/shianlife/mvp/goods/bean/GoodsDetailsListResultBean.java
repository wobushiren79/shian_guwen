package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsDetailsListResultBean {
    /**
     * spec_price : 888.00
     * adviser_price : 508.00
     * channel_id : 1
     * spec_alias : 墓型鲜花装饰
     * spec_total_cate : 0
     * spec_name : 墓型鲜花装饰
     * spec_stock : 100
     * ement_price : 400.00
     * goods_number : YXA-XH-01
     * goods_class_id : 3
     * class_attr_id : 7
     * spec_id : 8
     * goods_id : 8
     * class_name : 公墓附属品
     * goods_name : 墓型鲜花装饰
     * title_img : Goods/2017-08-26/59a0e0009648d.JPG
     * unit : 套
     * goods_slogan : 祭奠亲人，寄托哀思。
     * total : 1200.00
     * apply_age : 通用
     * apply_custom : 通用
     * apply_user : 通用
     * apply_stage : 通用
     * keywords : 鲜花装饰
     * collection : 100
     */

    private Float spec_price;  //推荐销售价格
    private Float adviser_price; //顾问结算价
    private Integer channel_id;    //商品渠道ID
    private String spec_alias;    //规格代名词
    private Integer spec_total_cate;  //规格商品总分类
    private String spec_name;     //规格名称
    private Integer spec_stock;   //规格商品库存
    private Float ement_price;  //商家结算价
    private String goods_number;  //商品编号
    private Long goods_class_id; //商品分类ID
    private Long class_attr_id;  //分类属性ID
    private Long spec_id;    //规格ID
    private Long goods_id; //商品ID
    private String class_name;  //分类名称
    private String goods_name;  //商品名称
    private String title_img;   //图片地址
    private String unit;   //单位
    private String goods_slogan;  //一句话介绍
    private Float total;   //原价
    private String apply_age;   //适用年龄
    private String apply_custom;//适用习俗
    private String apply_user;//适用人群
    private String apply_stage;//使用范围
    private String keywords;//关键词
    private Integer collection;//收藏数量

    private Integer shoppingCartNumber;//購物車數量
    private Long shoppingCartId;//购物车ID


    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getShoppingCartNumber() {
        return shoppingCartNumber;
    }

    public void setShoppingCartNumber(Integer shoppingCartNumber) {
        this.shoppingCartNumber = shoppingCartNumber;
    }

    public Float getSpec_price() {
        return spec_price;
    }

    public void setSpec_price(Float spec_price) {
        this.spec_price = spec_price;
    }

    public Float getAdviser_price() {
        return adviser_price;
    }

    public void setAdviser_price(Float adviser_price) {
        this.adviser_price = adviser_price;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public String getSpec_alias() {
        return spec_alias;
    }

    public void setSpec_alias(String spec_alias) {
        this.spec_alias = spec_alias;
    }

    public Integer getSpec_total_cate() {
        return spec_total_cate;
    }

    public void setSpec_total_cate(Integer spec_total_cate) {
        this.spec_total_cate = spec_total_cate;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public Integer getSpec_stock() {
        return spec_stock;
    }

    public void setSpec_stock(Integer spec_stock) {
        this.spec_stock = spec_stock;
    }

    public Float getEment_price() {
        return ement_price;
    }

    public void setEment_price(Float ement_price) {
        this.ement_price = ement_price;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public Long getGoods_class_id() {
        return goods_class_id;
    }

    public void setGoods_class_id(Long goods_class_id) {
        this.goods_class_id = goods_class_id;
    }

    public Long getClass_attr_id() {
        return class_attr_id;
    }

    public void setClass_attr_id(Long class_attr_id) {
        this.class_attr_id = class_attr_id;
    }

    public Long getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(Long spec_id) {
        this.spec_id = spec_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGoods_slogan() {
        return goods_slogan;
    }

    public void setGoods_slogan(String goods_slogan) {
        this.goods_slogan = goods_slogan;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getApply_age() {
        return apply_age;
    }

    public void setApply_age(String apply_age) {
        this.apply_age = apply_age;
    }

    public String getApply_custom() {
        return apply_custom;
    }

    public void setApply_custom(String apply_custom) {
        this.apply_custom = apply_custom;
    }

    public String getApply_user() {
        return apply_user;
    }

    public void setApply_user(String apply_user) {
        this.apply_user = apply_user;
    }

    public String getApply_stage() {
        return apply_stage;
    }

    public void setApply_stage(String apply_stage) {
        this.apply_stage = apply_stage;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }
}
