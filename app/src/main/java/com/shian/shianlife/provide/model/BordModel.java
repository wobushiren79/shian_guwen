package com.shian.shianlife.provide.model;

/**
 * 订单详情 详细信息
 * 
 * @author 95
 *
 */
public class BordModel {

	String visitNum="";// 客户编号
	String orderNum="";// 订单编号
	long orderTime;// 订单时间
	String agentmanName="";// 经办人姓名
	String agentmanlinkInfo="";// 经办人电话
	String contractNo="";// 合同编号
	String contractAmount="";// 合同金额
	String usageName="";// 逝者姓名
	long funeralTime;// 出殡时间
	long startTime;// 开始时间
	long endTime;// 结束时间
	long talkerId;// 白事顾问ID
	String talkerName="";// 白事顾问
	long performerId;// 治丧指导ID
	String performerName="";// 治丧指导
	String agentmanAddress="";// 经办人地址
	String funeralAddress="";// 治丧地址
	String dieAddress="";// 去世地址
	String funeralParlorAddress="";// 殡仪馆地址
	long setmealMainId;// 主套餐ID
	String setmealMainName="";// 主套餐名称
	long setmealFuneralId;// 殡仪馆级别ID
	String setmealFuneralName="";// 殡仪馆级别名称
	long setmealCemeteryId;// 公墓套餐ID
	String setmealCemeteryName="";// 公墓套餐名称
	String customerName;
	String customerMobile;
	String talkerMobile;
	String performerMobile;
	String totalAmount;
	String prepayAmount;
	float refundAmount;
	float restAmount;

	public float getRestAmount() {
		return restAmount;
	}

	public void setRestAmount(float restAmount) {
		this.restAmount = restAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPrepayAmount() {
		return prepayAmount;
	}

	public void setPrepayAmount(String prepayAmount) {
		this.prepayAmount = prepayAmount;
	}

	public float getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(float refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getTalkerMobile() {
		return talkerMobile;
	}

	public void setTalkerMobile(String talkerMobile) {
		this.talkerMobile = talkerMobile;
	}

	public String getPerformerMobile() {
		return performerMobile;
	}

	public void setPerformerMobile(String performerMobile) {
		this.performerMobile = performerMobile;
	}

	public String getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(String visitNum) {
		this.visitNum = visitNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getAgentmanName() {
		return agentmanName;
	}

	public void setAgentmanName(String agentmanName) {
		this.agentmanName = agentmanName;
	}

	public String getAgentmanlinkInfo() {
		return agentmanlinkInfo;
	}

	public void setAgentmanlinkInfo(String agentmanlinkInfo) {
		this.agentmanlinkInfo = agentmanlinkInfo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getUsageName() {
		return usageName;
	}

	public void setUsageName(String usageName) {
		this.usageName = usageName;
	}

	public long getFuneralTime() {
		return funeralTime;
	}

	public void setFuneralTime(long funeralTime) {
		this.funeralTime = funeralTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getTalkerId() {
		return talkerId;
	}

	public void setTalkerId(long talkerId) {
		this.talkerId = talkerId;
	}

	public String getTalkerName() {
		return talkerName;
	}

	public void setTalkerName(String talkerName) {
		this.talkerName = talkerName;
	}

	public long getPerformerId() {
		return performerId;
	}

	public void setPerformerId(long performerId) {
		this.performerId = performerId;
	}

	public String getPerformerName() {
		return performerName;
	}

	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}

	public String getAgentmanAddress() {
		return agentmanAddress;
	}

	public void setAgentmanAddress(String agentmanAddress) {
		this.agentmanAddress = agentmanAddress;
	}

	public String getFuneralAddress() {
		return funeralAddress;
	}

	public void setFuneralAddress(String funeralAddress) {
		this.funeralAddress = funeralAddress;
	}

	public String getDieAddress() {
		return dieAddress;
	}

	public void setDieAddress(String dieAddress) {
		this.dieAddress = dieAddress;
	}

	public String getFuneralParlorAddress() {
		return funeralParlorAddress;
	}

	public void setFuneralParlorAddress(String funeralParlorAddress) {
		this.funeralParlorAddress = funeralParlorAddress;
	}

	public long getSetmealMainId() {
		return setmealMainId;
	}

	public void setSetmealMainId(long setmealMainId) {
		this.setmealMainId = setmealMainId;
	}

	public String getSetmealMainName() {
		return setmealMainName;
	}

	public void setSetmealMainName(String setmealMainName) {
		this.setmealMainName = setmealMainName;
	}

	public long getSetmealFuneralId() {
		return setmealFuneralId;
	}

	public void setSetmealFuneralId(long setmealFuneralId) {
		this.setmealFuneralId = setmealFuneralId;
	}

	public String getSetmealFuneralName() {
		return setmealFuneralName;
	}

	public void setSetmealFuneralName(String setmealFuneralName) {
		this.setmealFuneralName = setmealFuneralName;
	}

	public long getSetmealCemeteryId() {
		return setmealCemeteryId;
	}

	public void setSetmealCemeteryId(long setmealCemeteryId) {
		this.setmealCemeteryId = setmealCemeteryId;
	}

	public String getSetmealCemeteryName() {
		return setmealCemeteryName;
	}

	public void setSetmealCemeteryName(String setmealCemeteryName) {
		this.setmealCemeteryName = setmealCemeteryName;
	}

}
