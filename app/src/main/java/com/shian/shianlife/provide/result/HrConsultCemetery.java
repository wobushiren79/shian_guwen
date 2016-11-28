package com.shian.shianlife.provide.result;

public class HrConsultCemetery {
	HrConsultCemeterys consultCemetery;

	public HrConsultCemeterys getConsultCemetery() {
		return consultCemetery;
	}

	public void setConsultCemetery(HrConsultCemeterys consultCemetery) {
		this.consultCemetery = consultCemetery;
	}

	public class HrConsultCemeterys {
		/**
		 * "id":153, "consultId":153, "buyFlag":1, "address":"string",
		 * "cemeteryId":153, "cemeteryName":"string", "buyIntention":"string",
		 * "graveAddress":"string", "buyAmount":1.68, "buryTime":1461395262089,
		 * "cemeteryUsageStatus":1, "note":"string"
		 */
		private long id;
		private long consultId;
		private int buyFlag;
		private String address;
		private long cemeteryId;
		private String cemeteryName;
		private String buyIntention;
		private String graveAddress;
		private float buyAmount;
		private long buryTime;
		private int cemeteryUsageStatus;
		private String note;

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

		public int getBuyFlag() {
			return buyFlag;
		}

		public void setBuyFlag(int buyFlag) {
			this.buyFlag = buyFlag;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getCemeteryId() {
			return cemeteryId;
		}

		public void setCemeteryId(long cemeteryId) {
			this.cemeteryId = cemeteryId;
		}

		public String getCemeteryName() {
			return cemeteryName;
		}

		public void setCemeteryName(String cemeteryName) {
			this.cemeteryName = cemeteryName;
		}

		public String getBuyIntention() {
			return buyIntention;
		}

		public void setBuyIntention(String buyIntention) {
			this.buyIntention = buyIntention;
		}

		public String getGraveAddress() {
			return graveAddress;
		}

		public void setGraveAddress(String graveAddress) {
			this.graveAddress = graveAddress;
		}

		public float getBuyAmount() {
			return buyAmount;
		}

		public void setBuyAmount(float buyAmount) {
			this.buyAmount = buyAmount;
		}

		public long getBuryTime() {
			return buryTime;
		}

		public void setBuryTime(long buryTime) {
			this.buryTime = buryTime;
		}

		public int getCemeteryUsageStatus() {
			return cemeteryUsageStatus;
		}

		public void setCemeteryUsageStatus(int cemeteryUsageStatus) {
			this.cemeteryUsageStatus = cemeteryUsageStatus;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}
	}
}
