package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpSaveCustomerUsageParams extends BaseHttpParams{
	/**
	 * "consultId":22, "name":"string", "age":2, "sex":2, "job":2, "state":2,
	 * "birthday":1461394124310, "curAddress":{ "address":"string", "areaId":22,
	 * "cityId":22, "provinceId":22, "suffix":"string" },
	 * "intimeReady":"string", "shoeSize":"string", "height":"string",
	 * "note":"string"
	 */

	private long consultId;
	private String name;
	private String age;
	private int sex=1;
	private int job;
	private String state;
	private long birthday;
	private String intimeReady;
	private String shoeSize;
	private String height;
	private String note;
	private String cardId;
	private String otherHealth;
	private String clothesData;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getOtherHealth() {
		return otherHealth;
	}

	public void setOtherHealth(String otherHealth) {
		this.otherHealth = otherHealth;
	}

	public String getClothesData() {
		return clothesData;
	}

	public void setClothesData(String clothesData) {
		this.clothesData = clothesData;
	}

	private HpAddConsultParams.TalkAddress curAddress;



	public long getConsultId() {
		return consultId;
	}

	public void setConsultId(long consultId) {
		this.consultId = consultId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getIntimeReady() {
		return intimeReady;
	}

	public void setIntimeReady(String intimeReady) {
		this.intimeReady = intimeReady;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public HpAddConsultParams.TalkAddress getCurAddress() {
		return curAddress;
	}

	public void setCurAddress(HpAddConsultParams.TalkAddress curAddress) {
		this.curAddress = curAddress;
	}


	public class CurAddress {
		/**
		 * "address":"string", "areaId":22, "cityId":22, "provinceId":22,
		 * "suffix":"string"
		 */
		private String address;
		private int areaId;
		private int cityId;
		private int provinceId;
		private String suffix;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getAreaId() {
			return areaId;
		}

		public void setAreaId(int areaId) {
			this.areaId = areaId;
		}

		public int getCityId() {
			return cityId;
		}

		public void setCityId(int cityId) {
			this.cityId = cityId;
		}

		public int getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(int provinceId) {
			this.provinceId = provinceId;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

	}
}
