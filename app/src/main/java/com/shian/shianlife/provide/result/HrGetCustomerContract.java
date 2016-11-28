package com.shian.shianlife.provide.result;

import java.util.List;

public class HrGetCustomerContract {

	/**
	 * "orderContract":{ "id":8, "orderId":8, "contractNo":"string",
	 * "contractAmount":0.69, "statusFlag":5, "serverCreateTime":1461396096733
	 * }, "orderContractAdditions":[ { "id":8, "contractId":8,
	 * "fileName":"string", "fileUrl":"string", "statusFlag":5,
	 * "serverCreateTime":1461396096733 } ]
	 */
	List<OrderContractAddition> orderContractAdditions;
	OrderContract orderContract;

	public List<OrderContractAddition> getOrderContractAdditions() {
		return orderContractAdditions;
	}

	public void setOrderContractAdditions(
			List<OrderContractAddition> orderContractAdditions) {
		this.orderContractAdditions = orderContractAdditions;
	}

	public OrderContract getOrderContract() {
		return orderContract;
	}

	public void setOrderContract(OrderContract orderContract) {
		this.orderContract = orderContract;
	}

	public static class OrderContractAddition {
		private long id;
		private long contractId;
		private String fileName;
		private String fileUrl;
		private int statusFlag;
		private long serverCreateTime;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getContractId() {
			return contractId;
		}

		public void setContractId(long contractId) {
			this.contractId = contractId;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}

		public int getStatusFlag() {
			return statusFlag;
		}

		public void setStatusFlag(int statusFlag) {
			this.statusFlag = statusFlag;
		}

		public long getServerCreateTime() {
			return serverCreateTime;
		}

		public void setServerCreateTime(long serverCreateTime) {
			this.serverCreateTime = serverCreateTime;
		}

	}

	public static class OrderContract {
		private long id;
		private long orderId;
		private String contractNo;
		private String contractAmount;
		private int statusFlag;
		private long serverCreateTime;
		private String note;

		
		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getOrderId() {
			return orderId;
		}

		public void setOrderId(long orderId) {
			this.orderId = orderId;
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

		public int getStatusFlag() {
			return statusFlag;
		}

		public void setStatusFlag(int statusFlag) {
			this.statusFlag = statusFlag;
		}

		public long getServerCreateTime() {
			return serverCreateTime;
		}

		public void setServerCreateTime(long serverCreateTime) {
			this.serverCreateTime = serverCreateTime;
		}

	}
}
