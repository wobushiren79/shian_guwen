package com.shian.shianlife.provide.params;

import java.util.List;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveCustomerContract extends BaseHttpParams {
	/**
	 * "orderId":88, "contractNo":"string", "contractAmount":1.41,
	 * "addAdditions":[ { "fileName":"string", "fileUrl":"string" } ],
	 * "removeAdditions":[ { "id":88 } ]
	 */
	long orderId;
	long consultId;
	String contractNo;
	String contractAmount;
	long orderItemId;
	String note;
	List<RemoveAddition> removeAdditions;
	List<AddAddition> addAdditions;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
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

	public List<RemoveAddition> getRemoveAdditions() {
		return removeAdditions;
	}

	public void setRemoveAdditions(List<RemoveAddition> removeAdditions) {
		this.removeAdditions = removeAdditions;
	}

	public List<AddAddition> getAddAdditions() {
		return addAdditions;
	}

	public void setAddAdditions(List<AddAddition> addAdditions) {
		this.addAdditions = addAdditions;
	}

	public static class RemoveAddition {
		private long id;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}

	public static class AddAddition {
		private String fileName;
		private String fileUrl;

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

	}
}
