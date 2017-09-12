package com.shian.shianlife.mvp.goods.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsDetailsResultBean {


    /**
     * id : 19
     * goods_cate_id : 5 //分类
     * goods_slogan : （骨灰钻石属于订制品具体规格型号颜色请咨询客服后下单此价格仅作参考） //一句话介绍
     * title_img : Goods/2017-08-30/59a67451c1501.jpg   //分类属性图片
     * total : 10000.00  //原价
     * creat_time : 1504248379 //商品创建时间
     * sale_amount : 10  //销售个数
     * total_stock : 100
     * unit : 个
     * apply_area_id : 2
     * apply_age : 通用
     * apply_custom : 通用
     * apply_user : 通用
     * apply_stage : 通用
     * descrip_detail : <p><img src="https://goodsmgr.e-funeral.cn/ueditor/php/upload/image/20170830/1504079192933587.jpg" title="1504079192933587.jpg" alt="骨灰钻石.jpg"/></p>
     * keywords : 骨灰钻石
     * comment : 其他
     * spec_attr_id : 10
     * collection : 10
     * name : 骨灰钻石  //商品名称
     * imgs : [{"pic_add":"GoodsImg/2017-08-30/59a674dda3d43.jpg"}]   轮播图
     * apply_area : [{"name":"全国"}]
     * spec_alias : 名称
     * specprice : [{"spec_price":"10000.00","adviser_price":"1.00","spec_alias":"名称","spec_name":"骨灰钻石","spec_stock":"100","goods_spec_id":"17","channel_id":"1","goods_id":"19","ement_price":"1.00","goods_number":"YXA-GHZS-01"}]
     * price : 10000.00~10000.00
     */

    private Long id;
    private Long goods_cate_id;//分类ID
    private String goods_slogan; //一句话介绍
    private String title_img; //分类属性图片
    private Float total;//原价
    private String creat_time;//商品创建时间
    private Integer sale_amount; //销售个数
    private Integer total_stock; //总库存
    private String unit;//单位
    private String apply_area_id;
    private String apply_age;//适用年龄
    private String apply_custom; //适用习俗
    private String apply_user; //适用人群
    private String apply_stage;//使用阶段
    private String descrip_detail;//商品详情HTML5
    private String keywords;//关键词
    private String comment;//商品备注
    private Long spec_attr_id;//商品分类属性ID
    private String collection; //收藏数量
    private String name;//商品名称
    private String spec_alias;//规格代名词
    private String price;//区间价
    private List<ImgsBean> imgs; //轮播图
    private List<ApplyAreaBean> apply_area;  //适用地区
    private List<SpecpriceBean> specprice;   //规格商品
    /**
     * 分类名称
     */
    private String goods_class_name;

    public String getGoods_class_name() {
        return goods_class_name;
    }

    public void setGoods_class_name(String goods_class_name) {
        this.goods_class_name = goods_class_name;
    }

    public Long getGoods_cate_id() {
        return goods_cate_id;
    }

    public void setGoods_cate_id(Long goods_cate_id) {
        this.goods_cate_id = goods_cate_id;
    }

    public String getGoods_slogan() {
        return goods_slogan;
    }

    public void setGoods_slogan(String goods_slogan) {
        this.goods_slogan = goods_slogan;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }


    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getApply_area_id() {
        return apply_area_id;
    }

    public void setApply_area_id(String apply_area_id) {
        this.apply_area_id = apply_area_id;
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

    public String getDescrip_detail() {
        return descrip_detail;
    }

    public void setDescrip_detail(String descrip_detail) {
        this.descrip_detail = descrip_detail;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec_alias() {
        return spec_alias;
    }

    public void setSpec_alias(String spec_alias) {
        this.spec_alias = spec_alias;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<ApplyAreaBean> getApply_area() {
        return apply_area;
    }

    public void setApply_area(List<ApplyAreaBean> apply_area) {
        this.apply_area = apply_area;
    }

    public List<SpecpriceBean> getSpecprice() {
        return specprice;
    }

    public void setSpecprice(List<SpecpriceBean> specprice) {
        this.specprice = specprice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(Integer sale_amount) {
        this.sale_amount = sale_amount;
    }

    public Integer getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(Integer total_stock) {
        this.total_stock = total_stock;
    }

    public Long getSpec_attr_id() {
        return spec_attr_id;
    }

    public void setSpec_attr_id(Long spec_attr_id) {
        this.spec_attr_id = spec_attr_id;
    }

    public static class ImgsBean {
        /**
         * pic_add : GoodsImg/2017-08-30/59a674dda3d43.jpg
         */

        private String pic_add;

        public String getPic_add() {
            return pic_add;
        }

        public void setPic_add(String pic_add) {
            this.pic_add = pic_add;
        }
    }

    public static class ApplyAreaBean {
        /**
         * name : 全国
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SpecpriceBean {
        /**
         * spec_price : 10000.00
         * adviser_price : 1.00
         * spec_alias : 名称
         * spec_name : 骨灰钻石
         * spec_stock : 100
         * goods_spec_id : 17
         * channel_id : 1
         * goods_id : 19
         * ement_price : 1.00
         */

        private Float spec_price;    //客户结算价
        private Float adviser_price;//顾问结算价
        private String spec_alias;  //规格代名词
        private String spec_name;//规格名称 --前
        private Integer spec_stock;//规格库存
        private Long goods_spec_id;//规格商品ID
        private Integer channel_id;
        private Long goods_id;//商品ID
        private Float ement_price;//商家结算价
        private String goods_number;//商品编号

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
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

        public String getSpec_alias() {
            return spec_alias;
        }

        public void setSpec_alias(String spec_alias) {
            this.spec_alias = spec_alias;
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

        public Long getGoods_spec_id() {
            return goods_spec_id;
        }

        public void setGoods_spec_id(Long goods_spec_id) {
            this.goods_spec_id = goods_spec_id;
        }

        public Integer getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(Integer channel_id) {
            this.channel_id = channel_id;
        }

        public Long getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(Long goods_id) {
            this.goods_id = goods_id;
        }

        public Float getEment_price() {
            return ement_price;
        }

        public void setEment_price(Float ement_price) {
            this.ement_price = ement_price;
        }
    }
}
