package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsPerform 实体
 * 创建人： CQ
 * 创建时间：2017-07-20
 */

public class GoodsPerform extends BaseEntity {
    /**
     * 执行商品ID
     */
    private Long goodsItemId;
    /**
     * orderId
     */
    private Long orderId;
    /**
     * 执行状态:0待派单 1已派单(待接单) 2待执行(已接单) 3执行中  4审核中  5成功服务
     */
    private Integer performStatus;

    /**
     * 执行单编号
     */
    private String performSheetName;

    /**
     * 商家id
     */
    private Long performUserId;

    /**
     * 接单时间
     */
    private String acceptTime;

    /**
     * 开始服务时间
     */
    private String startTime;
    /**
     * 结束服务时间
     */
    private String endTime;

    /**
     * 执行方式:0同城送达 1上门服务 2快递物流
     */
    private Integer performWay;

    /**
     * 执行人姓名
     */
    private String performUserName;

    /**
     * 执行人电话
     */
    private String performUserPhone;

    /**
     * 执行备注
     */
    private String performComment;

    public Long getGoodsItemId() {
        return goodsItemId;
    }

    public void setGoodsItemId(Long goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getPerformStatus() {
        return performStatus;
    }

    public void setPerformStatus(Integer performStatus) {
        this.performStatus = performStatus;
    }

    public String getPerformSheetName() {
        return performSheetName;
    }

    public void setPerformSheetName(String performSheetName) {
        this.performSheetName = performSheetName;
    }

    public Long getPerformUserId() {
        return performUserId;
    }

    public void setPerformUserId(Long performUserId) {
        this.performUserId = performUserId;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPerformWay() {
        return performWay;
    }

    public void setPerformWay(Integer performWay) {
        this.performWay = performWay;
    }

    public String getPerformUserName() {
        return performUserName;
    }

    public void setPerformUserName(String performUserName) {
        this.performUserName = performUserName;
    }

    public String getPerformUserPhone() {
        return performUserPhone;
    }

    public void setPerformUserPhone(String performUserPhone) {
        this.performUserPhone = performUserPhone;
    }

    public String getPerformComment() {
        return performComment;
    }

    public void setPerformComment(String performComment) {
        this.performComment = performComment;
    }
}
