package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsOrderChange 实体
 * 创建人： CQ
 * 创建时间：2017-07-21
 */

public class GoodsOrderChange extends BaseEntity {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单变化时间
     */
    private String updateTime;

    /**
     * 变化状态:0订单创建  1订单开始服务时间  2订单服务完成  3订单成功服务 4订单取消时间
     */
    private Integer updataStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdataStatus() {
        return updataStatus;
    }

    public void setUpdataStatus(Integer updataStatus) {
        this.updataStatus = updataStatus;
    }
}
