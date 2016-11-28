package com.shian.shianlife.provide.model;

public class OrderListModel {
	 long assignId;//指派ID
	long orderId;// 订单ID, 当未下过订单时, 该值为null
	String orderNum;// 订单编号
	int orderStatus;// 订单状态，1：未处理，2：待服务，3：已接受，4：服务派单中，5：结束派单，6：已确认，7：服务完成
	long consultId;
	String customerName;// 客户姓名
	String promiseTime;// 洽谈时间
	String customerAddress;// 客户联系地址
	String description;// 备注
	long consultAssignId;// 咨询指派ID

	String agentmanName;// 经办人姓名
	String usageCurAddress;// 使用者当前所在地
	String performerName;// 治丧指导，即：执行顾问
	String talkerName;// 白事顾问，即：洽谈顾问
	boolean showEditOrder;// 是否显示[编辑订单]
	boolean showOrderDetail;// 是否显示[订单详情]
	boolean showFinishTalk;// 是否显示[结束洽谈]
	boolean showAcceptOrReject;// 是否显示[接单]
	boolean showStartService;// 是否显示[转待服务]

	boolean showSwitch2waitService;// 是否显示【开始执行】和【订单详情】
	boolean showAcceptAndReject;// 是否显示【接受】和【拒绝】

	String funeralAddress;// 治丧地址
	String payeeName;// 收款员

	int restMoneyStatus;// 收款状态
	long startTime;// 服务开始时间
	long endTime;// 服务结束时间
	int satTotal;// 服务满意度
	boolean auditPass;
	
	String customerMobile;
	String agentmanLinkInfo;//有误
	String agentmanMobile;
	String talkerMobile;
	String performerMobile;
	boolean hasPrepay;
	boolean hasRest;
	int consultStatus;
	String usageNote;
	int financeStatus;

	public String getAgentmanMobile() {
		return agentmanMobile;
	}

	public void setAgentmanMobile(String agentmanMobile) {
		this.agentmanMobile = agentmanMobile;
	}

	public int getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(int financeStatus) {
		this.financeStatus = financeStatus;
	}

	public String getUsageNote() {
		return usageNote;
	}

	public void setUsageNote(String usageNote) {
		this.usageNote = usageNote;
	}

	public boolean isHasRest() {
		return hasRest;
	}

	public void setHasRest(boolean hasRest) {
		this.hasRest = hasRest;
	}

	public int getConsultStatus() {
		return consultStatus;
	}

	public void setConsultStatus(int consultStatus) {
		this.consultStatus = consultStatus;
	}

	public boolean isHasPrepay() {
		return hasPrepay;
	}

	public void setHasPrepay(boolean hasPrepay) {
		this.hasPrepay = hasPrepay;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public boolean isAuditPass() {
		return auditPass;
	}

	public void setAuditPass(boolean auditPass) {
		this.auditPass = auditPass;
	}

	public String getAgentmanLinkInfo() {
		return agentmanLinkInfo;
	}

	public void setAgentmanLinkInfo(String agentmanLinkInfo) {
		this.agentmanLinkInfo = agentmanLinkInfo;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPromiseTime() {
		return promiseTime;
	}

	public void setPromiseTime(String promiseTime) {
		this.promiseTime = promiseTime;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getConsultAssignId() {
		return consultAssignId;
	}

	public void setConsultAssignId(long consultAssignId) {
		this.consultAssignId = consultAssignId;
	}

	public String getAgentmanName() {
		return agentmanName;
	}

	public void setAgentmanName(String agentmanName) {
		this.agentmanName = agentmanName;
	}

	public String getUsageCurAddress() {
		return usageCurAddress;
	}

	public void setUsageCurAddress(String usageCurAddress) {
		this.usageCurAddress = usageCurAddress;
	}

	public String getPerformerName() {
		return performerName;
	}

	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}

	public String getTalkerName() {
		return talkerName;
	}

	public void setTalkerName(String talkerName) {
		this.talkerName = talkerName;
	}

	public boolean isShowEditOrder() {
		return showEditOrder;
	}

	public void setShowEditOrder(boolean showEditOrder) {
		this.showEditOrder = showEditOrder;
	}

	public boolean isShowOrderDetail() {
		return showOrderDetail;
	}

	public void setShowOrderDetail(boolean showOrderDetail) {
		this.showOrderDetail = showOrderDetail;
	}

	public boolean isShowFinishTalk() {
		return showFinishTalk;
	}

	public void setShowFinishTalk(boolean showFinishTalk) {
		this.showFinishTalk = showFinishTalk;
	}

	public boolean isShowAcceptOrReject() {
		return showAcceptOrReject;
	}

	public void setShowAcceptOrReject(boolean showAcceptOrReject) {
		this.showAcceptOrReject = showAcceptOrReject;
	}

	public boolean isShowStartService() {
		return showStartService;
	}

	public void setShowStartService(boolean showStartService) {
		this.showStartService = showStartService;
	}

	public boolean isShowSwitch2waitService() {
		return showSwitch2waitService;
	}

	public void setShowSwitch2waitService(boolean showSwitch2waitService) {
		this.showSwitch2waitService = showSwitch2waitService;
	}

	public boolean isShowAcceptAndReject() {
		return showAcceptAndReject;
	}

	public void setShowAcceptAndReject(boolean showAcceptAndReject) {
		this.showAcceptAndReject = showAcceptAndReject;
	}

	public String getFuneralAddress() {
		return funeralAddress;
	}

	public void setFuneralAddress(String funeralAddress) {
		this.funeralAddress = funeralAddress;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public int getRestMoneyStatus() {
		return restMoneyStatus;
	}

	public void setRestMoneyStatus(int restMoneyStatus) {
		this.restMoneyStatus = restMoneyStatus;
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

	public int getSatTotal() {
		return satTotal;
	}

	public void setSatTotal(int satTotal) {
		this.satTotal = satTotal;
	}

	public long getAssignId() {
		return assignId;
	}

	public void setAssignId(long assignId) {
		this.assignId = assignId;
	}

}
