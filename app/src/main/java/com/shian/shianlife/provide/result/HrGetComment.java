package com.shian.shianlife.provide.result;

import java.util.List;

public class HrGetComment {
/**
 * "executorName":"string",
        "executorMobile":"string",
        "executorNote":"string",
        "adviserNote":"string",
        "auditNote":"string",
        "picItems":[
            {
                "picName":"string",
                "picUrl":"string"
            }
        ]
    }
 */
	private String executorName;
	private String executorMobile;
	private String executorNote;
	private String adviserNote;
	private String auditNote;
	List<PicItem> picItems;
	
	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public String getExecutorMobile() {
		return executorMobile;
	}

	public void setExecutorMobile(String executorMobile) {
		this.executorMobile = executorMobile;
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

	public String getAuditNote() {
		return auditNote;
	}

	public void setAuditNote(String auditNote) {
		this.auditNote = auditNote;
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
}
