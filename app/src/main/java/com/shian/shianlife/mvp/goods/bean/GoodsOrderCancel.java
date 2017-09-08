package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;



/** 
 * 类名称：GoodsOrderCancel 实体
 * 创建人： CQ
 * 创建时间：2017-08-22
 */

public class GoodsOrderCancel extends BaseEntity {


	/**
	 * 订单ID
	 */
    private Long orderId;

	/**
	 * 取消订单时间
	 */
    private String cancelTime;

	/**
	 * 取消订单（交易关闭）原因
	 */
    private String cancelReason;

	/**
	 * 取消订单类型:0退货、1退款、2退货退款 3其他
	 */
    private Integer cancelResult;

	/**
	 * 订单取消处理备注
	 */
    private String cancelRemark;

	/**
	 * 取消执行单的状态 值: 1取消中 2拒绝取消 3取消成功
	 */
    private Integer cancelStatus;

	/**
	 * 处理意见
	 */
    private String handleIdea;
	/**
	 * 申请人
	 */
	private String applyer;

	/**
	 * 申请人电话
	 */
	private String applyerPhone;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Integer getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(Integer cancelResult) {
		this.cancelResult = cancelResult;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public Integer getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public String getHandleIdea() {
		return handleIdea;
	}

	public void setHandleIdea(String handleIdea) {
		this.handleIdea = handleIdea;
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	public String getApplyerPhone() {
		return applyerPhone;
	}

	public void setApplyerPhone(String applyerPhone) {
		this.applyerPhone = applyerPhone;
	}
}
