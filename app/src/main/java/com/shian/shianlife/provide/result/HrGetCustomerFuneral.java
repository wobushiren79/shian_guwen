package com.shian.shianlife.provide.result;

public class HrGetCustomerFuneral {
	/**
	 * "orderFuneral":{ "id":18, "orderId":18 "cityId":18, "funeralParlorId":18,
	 * "funeralParlorAddress":"string", "funeralTime":1461396727435,
	 * "cremationTime":18, "dieTime":1461396727435, "dieAddress":"string",
	 * "dieAddressProvince":18, "dieAddressCity":18, "dieAddressArea":18,
	 * "dieAddressSuffix":"string", "funeralAddress":"string",
	 * "funeralAddressProvince":18, "funeralAddressCity":18,
	 * "funeralAddressArea":18, "funeralAddressSuffix":"string", "note":"string"
	 * } }
	 */
	OrderFuneral orderFuneral;

	public OrderFuneral getOrderFuneral() {
		return orderFuneral;
	}

	public void setOrderFuneral(OrderFuneral orderFuneral) {
		this.orderFuneral = orderFuneral;
	}

	public class OrderFuneral {
		private long id;
		private long orderId;
		private long cityId;
		private long funeralParlorId;
		private long funeralTime;
		private long cremationTime;
		private long dieTime;
		private String dieAddress;
		private int dieAddressProvince;
		private int dieAddressCity;
		private int dieAddressArea;
		private String dieAddressSuffix;
		private String funeralAddress;
		private int funeralAddressProvince;
		private int funeralAddressCity;
		private int funeralAddressArea;
		private String funeralAddressSuffix;
		private String note;
		private String funeralParlorAddress;
		private String parlorAddress;
		private int parlorAddressProvince;
		private int parlorAddressCity;
		private int parlorAddressArea;
		private String parlorAddressSuffix;

		
		public String getParlorAddress() {
			return parlorAddress;
		}

		public void setParlorAddress(String parlorAddress) {
			this.parlorAddress = parlorAddress;
		}

		

		public int getParlorAddressProvince() {
			return parlorAddressProvince;
		}

		public void setParlorAddressProvince(int parlorAddressProvince) {
			this.parlorAddressProvince = parlorAddressProvince;
		}

		public int getParlorAddressCity() {
			return parlorAddressCity;
		}

		public void setParlorAddressCity(int parlorAddressCity) {
			this.parlorAddressCity = parlorAddressCity;
		}

		public int getParlorAddressArea() {
			return parlorAddressArea;
		}

		public void setParlorAddressArea(int parlorAddressArea) {
			this.parlorAddressArea = parlorAddressArea;
		}

		public String getParlorAddressSuffix() {
			return parlorAddressSuffix;
		}

		public void setParlorAddressSuffix(String parlorAddressSuffix) {
			this.parlorAddressSuffix = parlorAddressSuffix;
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

		public long getCityId() {
			return cityId;
		}

		public void setCityId(long cityId) {
			this.cityId = cityId;
		}

		public long getFuneralParlorId() {
			return funeralParlorId;
		}

		public void setFuneralParlorId(long funeralParlorId) {
			this.funeralParlorId = funeralParlorId;
		}

		public long getFuneralTime() {
			return funeralTime;
		}

		public void setFuneralTime(long funeralTime) {
			this.funeralTime = funeralTime;
		}

		public long getCremationTime() {
			return cremationTime;
		}

		public void setCremationTime(long cremationTime) {
			this.cremationTime = cremationTime;
		}

		public long getDieTime() {
			return dieTime;
		}

		public void setDieTime(long dieTime) {
			this.dieTime = dieTime;
		}

		public String getDieAddress() {
			return dieAddress;
		}

		public void setDieAddress(String dieAddress) {
			this.dieAddress = dieAddress;
		}

		public int getDieAddressProvince() {
			return dieAddressProvince;
		}

		public void setDieAddressProvince(int dieAddressProvince) {
			this.dieAddressProvince = dieAddressProvince;
		}

		public int getDieAddressCity() {
			return dieAddressCity;
		}

		public void setDieAddressCity(int dieAddressCity) {
			this.dieAddressCity = dieAddressCity;
		}

		public int getDieAddressArea() {
			return dieAddressArea;
		}

		public void setDieAddressArea(int dieAddressArea) {
			this.dieAddressArea = dieAddressArea;
		}

		public String getDieAddressSuffix() {
			return dieAddressSuffix;
		}

		public void setDieAddressSuffix(String dieAddressSuffix) {
			this.dieAddressSuffix = dieAddressSuffix;
		}

		public String getFuneralAddress() {
			return funeralAddress;
		}

		public void setFuneralAddress(String funeralAddress) {
			this.funeralAddress = funeralAddress;
		}

		public int getFuneralAddressProvince() {
			return funeralAddressProvince;
		}

		public void setFuneralAddressProvince(int funeralAddressProvince) {
			this.funeralAddressProvince = funeralAddressProvince;
		}

		public int getFuneralAddressCity() {
			return funeralAddressCity;
		}

		public void setFuneralAddressCity(int funeralAddressCity) {
			this.funeralAddressCity = funeralAddressCity;
		}

		public int getFuneralAddressArea() {
			return funeralAddressArea;
		}

		public void setFuneralAddressArea(int funeralAddressArea) {
			this.funeralAddressArea = funeralAddressArea;
		}

		public String getFuneralAddressSuffix() {
			return funeralAddressSuffix;
		}

		public void setFuneralAddressSuffix(String funeralAddressSuffix) {
			this.funeralAddressSuffix = funeralAddressSuffix;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public String getFuneralParlorAddress() {
			return funeralParlorAddress;
		}

		public void setFuneralParlorAddress(String funeralParlorAddress) {
			this.funeralParlorAddress = funeralParlorAddress;
		}

	}
}
