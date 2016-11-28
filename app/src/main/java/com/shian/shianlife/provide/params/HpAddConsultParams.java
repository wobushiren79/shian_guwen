package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpAddConsultParams extends BaseHttpParams {
	private String customerName;
	private String customerMobile;
	private int businessType;
	String description;
	private TalkAddress customerAddress;

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

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TalkAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(TalkAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public static class TalkAddress {
		private int provinceId;
		private int cityId;
		private int areaId;
		private String suffix;
		private String address;

		public int getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(int provinceId) {
			this.provinceId = provinceId;
		}

		public int getCityId() {
			return cityId;
		}

		public void setCityId(int cityId) {
			this.cityId = cityId;
		}

		public int getAreaId() {
			return areaId;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}

}
