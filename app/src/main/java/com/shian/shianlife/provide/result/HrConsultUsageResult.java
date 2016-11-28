package com.shian.shianlife.provide.result;

public class HrConsultUsageResult {
	HrConsultUsageResults consultUsage;

	public HrConsultUsageResults getConsultUsage() {
		return consultUsage;
	}

	public void setConsultUsage(HrConsultUsageResults consultUsage) {
		this.consultUsage = consultUsage;
	}

	public class HrConsultUsageResults {
		/**
		 * "age":"string", "birthday":1461393773005, "consultId":11,
		 * "curAddress":"string", "curAddressArea":11, "curAddressCity":11,
		 * "curAddressProvince":11, "curAddressSuffix":"string",
		 * "dieTime":1461393773005, "height":"string", "id":11,
		 * "intimeReady":"string", "job":2, "name":"string", "note":"string",
		 * "sex":2, "shoeSize":"string", "state":2, "will":"string"
		 */
		private String age;
		private long birthday;
		private String curAddress;
		private int curAddressArea;
		private int curAddressCity;
		private int curAddressProvince;
		private String curAddressSuffix;
		private long dieTime;
		private String height;
		private long id;
		private String intimeReady;
		private int job;
		private String name;
		private String note;
		private int sex;
		private String shoeSize;
		private String state;
		private String will;

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public long getBirthday() {
			return birthday;
		}

		public void setBirthday(long birthday) {
			this.birthday = birthday;
		}

		public String getCurAddress() {
			return curAddress;
		}

		public void setCurAddress(String curAddress) {
			this.curAddress = curAddress;
		}

		public int getCurAddressArea() {
			return curAddressArea;
		}

		public void setCurAddressArea(int curAddressArea) {
			this.curAddressArea = curAddressArea;
		}

		public int getCurAddressCity() {
			return curAddressCity;
		}

		public void setCurAddressCity(int curAddressCity) {
			this.curAddressCity = curAddressCity;
		}

		public int getCurAddressProvince() {
			return curAddressProvince;
		}

		public void setCurAddressProvince(int curAddressProvince) {
			this.curAddressProvince = curAddressProvince;
		}

		public String getCurAddressSuffix() {
			return curAddressSuffix;
		}

		public void setCurAddressSuffix(String curAddressSuffix) {
			this.curAddressSuffix = curAddressSuffix;
		}

		public long getDieTime() {
			return dieTime;
		}

		public void setDieTime(long dieTime) {
			this.dieTime = dieTime;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getIntimeReady() {
			return intimeReady;
		}

		public void setIntimeReady(String intimeReady) {
			this.intimeReady = intimeReady;
		}

		public int getJob() {
			return job;
		}

		public void setJob(int job) {
			this.job = job;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getShoeSize() {
			return shoeSize;
		}

		public void setShoeSize(String shoeSize) {
			this.shoeSize = shoeSize;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getWill() {
			return will;
		}

		public void setWill(String will) {
			this.will = will;
		}
	}
}
