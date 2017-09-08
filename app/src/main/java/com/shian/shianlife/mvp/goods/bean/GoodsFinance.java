package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsFinance 实体
 * 创建人： CQ
 * 创建时间：2017-07-19
 */
public class GoodsFinance extends BaseEntity {
	/**
	 * 订单ID
	 */
    private Long orderId;

	/**
	 * 支付状态:0未支付 1已付款
	 */
    private Integer paymentStatus;

	/**
	 * 发票状态 :0未开票 1已开票
	 */
    private Integer deliveryStatus;

	/**
	 * 优惠券信息
	 */
    private String couponInfo;

	/**
	 * 运费
	 */
    private String freight;

	/**
	 * 支付记录id
	 */
    private String paymentRecordsId;

	/**
	 * 付款流水号 付款时生成的流水号
	 */
    private String paymentNumber;

	/**
	 * 支付方式:0当前支付、1后续支付
	 */
    private Integer paymentTimePoint;

	/**
	 * 实际付款金额
	 */
    private Integer actualPayment;

	/**
	 * 付款时间
	 */
    private String paymentTime;

	/**
	 * 付款方式:0微信支付、1支付宝支付 2现金支付、3刷卡支付、
	 */
    private Integer paymentWay;

	/**
	 * 是否确认款项:0未确认 1已确认
	 */
    private Integer checkBalance;

	/**
	 * 确认人员ID
	 */
    private Long checkOpId;

	/**
	 * 确认款项时间
	 */
    private String checkBalanceTime;
	/**
	 * 财务款项意见
	 */
	private String paymentAdvice;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCouponInfo() {
		return couponInfo;
	}

	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getPaymentRecordsId() {
		return paymentRecordsId;
	}

	public void setPaymentRecordsId(String paymentRecordsId) {
		this.paymentRecordsId = paymentRecordsId;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Integer getPaymentTimePoint() {
		return paymentTimePoint;
	}

	public void setPaymentTimePoint(Integer paymentTimePoint) {
		this.paymentTimePoint = paymentTimePoint;
	}

	public Integer getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(Integer actualPayment) {
		this.actualPayment = actualPayment;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Integer getCheckBalance() {
		return checkBalance;
	}

	public void setCheckBalance(Integer checkBalance) {
		this.checkBalance = checkBalance;
	}

	public Long getCheckOpId() {
		return checkOpId;
	}

	public void setCheckOpId(Long checkOpId) {
		this.checkOpId = checkOpId;
	}

	public String getCheckBalanceTime() {
		return checkBalanceTime;
	}

	public void setCheckBalanceTime(String checkBalanceTime) {
		this.checkBalanceTime = checkBalanceTime;
	}

	public String getPaymentAdvice() {
		return paymentAdvice;
	}

	public void setPaymentAdvice(String paymentAdvice) {
		this.paymentAdvice = paymentAdvice;
	}
}
