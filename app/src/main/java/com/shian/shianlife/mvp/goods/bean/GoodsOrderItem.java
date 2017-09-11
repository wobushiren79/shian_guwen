package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsOrderItems 实体
 * 创建人： CQ
 * 创建时间：2017-07-20
 */
public class GoodsOrderItem extends BaseEntity {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 规格商品ID
     */
    private Long goodsId;

    /**
     * 规格商品ID
     */
    private Long goodsSpecId;

    /**
     * 分类属性ID
     */
    private Long classifyAttrId;

    /**
     * 分类ID
     */
    private Long classifyId;

    /**
     * 规格商品下单单价(推荐价)
     */
    private Integer specOrderedPrice;

    /**
     * 规格商品下单数量
     */
    private Integer specOrderedNum;

    /**
     * 规格商品下单名称
     */
    private String specOrderedVolume;

    /**
     * 规格商品代名词(类型)
     */
    private String specAlias;

    /**
     * 当前折扣率
     */
    private String currentDiscount;

    /**
     * 总计
     */
    private Integer specTotal;

    /**
     * 商品分类名称
     */
    private String  specOrderedAttr;
    /**
     * 规格商品编号
     */
    private String specNumber;
    /**
     * 顾问结算价
     */
    private Integer adviserPrice;
    /**
     * 标题图
     */
    private String titleImg;
    /**
     * 商家结算价
     */
    private Integer ementPrice;
    /**
     * 单位
     */
    private String unit;
    /**
     * 规格名称
     */
    private String  specName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsSpecId() {
        return goodsSpecId;
    }

    public void setGoodsSpecId(Long goodsSpecId) {
        this.goodsSpecId = goodsSpecId;
    }

    public Long getClassifyAttrId() {
        return classifyAttrId;
    }

    public void setClassifyAttrId(Long classifyAttrId) {
        this.classifyAttrId = classifyAttrId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getSpecOrderedPrice() {
        return specOrderedPrice;
    }

    public void setSpecOrderedPrice(Integer specOrderedPrice) {
        this.specOrderedPrice = specOrderedPrice;
    }

    public Integer getSpecOrderedNum() {
        return specOrderedNum;
    }

    public void setSpecOrderedNum(Integer specOrderedNum) {
        this.specOrderedNum = specOrderedNum;
    }

    public String getSpecOrderedVolume() {
        return specOrderedVolume;
    }

    public void setSpecOrderedVolume(String specOrderedVolume) {
        this.specOrderedVolume = specOrderedVolume;
    }

    public String getSpecAlias() {
        return specAlias;
    }

    public void setSpecAlias(String specAlias) {
        this.specAlias = specAlias;
    }

    public String getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(String currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public Integer getSpecTotal() {
        return specTotal;
    }

    public void setSpecTotal(Integer specTotal) {
        this.specTotal = specTotal;
    }

    public String getSpecOrderedAttr() {
        return specOrderedAttr;
    }

    public void setSpecOrderedAttr(String specOrderedAttr) {
        this.specOrderedAttr = specOrderedAttr;
    }

    public String getSpecNumber() {
        return specNumber;
    }

    public void setSpecNumber(String specNumber) {
        this.specNumber = specNumber;
    }

    public Integer getAdviserPrice() {
        return adviserPrice;
    }

    public void setAdviserPrice(Integer adviserPrice) {
        this.adviserPrice = adviserPrice;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public Integer getEmentPrice() {
        return ementPrice;
    }

    public void setEmentPrice(Integer ementPrice) {
        this.ementPrice = ementPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
