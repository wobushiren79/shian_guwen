package com.shian.shianlife.provide.result;

import java.util.List;

public class ConsultPrereadies {
	List<ConsultPrereadie> consultPrereadies;

	public List<ConsultPrereadie> getConsultPrereadies() {
		return consultPrereadies;
	}

	public void setConsultPrereadies(List<ConsultPrereadie> consultPrereadies) {
		this.consultPrereadies = consultPrereadies;
	}

	public static class ConsultPrereadie {
		private long id;
		private long consultId;
		private String fileName;
		private String fileUrl;
		private int statusFlag;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getConsultId() {
			return consultId;
		}

		public void setConsultId(long consultId) {
			this.consultId = consultId;
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

	}
}