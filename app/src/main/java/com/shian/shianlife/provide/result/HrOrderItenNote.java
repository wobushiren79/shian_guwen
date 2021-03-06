package com.shian.shianlife.provide.result;

import java.util.List;


public class HrOrderItenNote {
	private String orderNum;
	private String agentmanName;
	private String funeralAddress;
	private String skuName;
	private String executorName;
	private String acceptTime;
	private String startTime;
	private String endTime;
	private String executorNote;
	private String adviserNote;
	private List<PicItem> picItems;
	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getAgentmanName() {
		return agentmanName;
	}

	public void setAgentmanName(String agentmanName) {
		this.agentmanName = agentmanName;
	}

	public String getFuneralAddress() {
		return funeralAddress;
	}

	public void setFuneralAddress(String funeralAddress) {
		this.funeralAddress = funeralAddress;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
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

	public String getExecutorNote() {
		return executorNote;
	}

	public void setExecutorNote(String executorNote) {
		this.executorNote = executorNote;
	}

	public String getAdviserNote() {
		return adviserNote;
	}

	public void setAdviserNote(String adviserNote) {
		this.adviserNote = adviserNote;
	}

	public List<PicItem> getPicItems() {
		return picItems;
	}

	public void setPicItems(List<PicItem> picItems) {
		this.picItems = picItems;
	}

	public static class PicItem{
		private String picName;
		private String picUrl;
		public String getPicName() {
			return picName;
		}
		public void setPicName(String picName) {
			this.picName = picName;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		
	}
	
	/**
        "executorNote":"string",
        "adviserNote":"string",
        "auditNote":"string",
        "picItems":[
            {
                "picName":"string",
                "picUrl":"string"
            }
        ]
	 */
}
