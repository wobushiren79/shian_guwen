package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsExpress 实体
 * 创建人： CQ
 * 创建时间：2017-07-31
 */

public class GoodsExpress extends BaseEntity {


	/**
	 * 执行单ID
	 */
    private Long performId;

	/**
	 * 快递公司
	 */
    private String expressName;

	/**
	 * 快递单号
	 */
    private String deliveryNumber;


	public Long getPerformId() {
		return performId;
	}

	public void setPerformId(Long performId) {
		this.performId = performId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
}
