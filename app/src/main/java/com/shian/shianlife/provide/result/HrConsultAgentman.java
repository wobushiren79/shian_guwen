package com.shian.shianlife.provide.result;

public class HrConsultAgentman {
	HrConsultAgentmans consultAgentman;

	public HrConsultAgentmans getConsultAgentman() {
		return consultAgentman;
	}

	public void setConsultAgentman(HrConsultAgentmans consultAgentman) {
		this.consultAgentman = consultAgentman;
	}

	public class HrConsultAgentmans {
		/**
		 * "id":165, "consultId":165, "name":"string", "relation":6,
		 * "linkInfo":"string", "birthday":1461394663029, "address":"string",
		 * "addressArea":165, "addressCity":165, "addressProvince":165,
		 * "addressSuffix":"string"
		 */
		private long consultId;
		private String relation;
		private String linkInfo;
		private long birthday;
		private String address;
		private int addressArea;
		private int addressCity;
		private int addressProvince;
		private String addressSuffix;
		private long id;
		private String name;

		public long getConsultId() {
			return consultId;
		}

		public void setConsultId(long consultId) {
			this.consultId = consultId;
		}

		public String getRelation() {
			return relation;
		}

		public void setRelation(String relation) {
			this.relation = relation;
		}

		public String getLinkInfo() {
			return linkInfo;
		}

		public void setLinkInfo(String linkInfo) {
			this.linkInfo = linkInfo;
		}

		public long getBirthday() {
			return birthday;
		}

		public void setBirthday(long birthday) {
			this.birthday = birthday;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getAddressArea() {
			return addressArea;
		}

		public void setAddressArea(int addressArea) {
			this.addressArea = addressArea;
		}

		public int getAddressCity() {
			return addressCity;
		}

		public void setAddressCity(int addressCity) {
			this.addressCity = addressCity;
		}

		public int getAddressProvince() {
			return addressProvince;
		}

		public void setAddressProvince(int addressProvince) {
			this.addressProvince = addressProvince;
		}

		public String getAddressSuffix() {
			return addressSuffix;
		}

		public void setAddressSuffix(String addressSuffix) {
			this.addressSuffix = addressSuffix;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
