package com.shian.shianlife.provide.result;

public class HrOrderFeedback {
	/**
	 * "payFeedback":{ "id":3, "payId":3, "satTotal":9, "satCeremony":9,
	 * "satArrange":9, "satFr":9, "satLife":9, "satSku":9, "opinion":"string",
	 * "signUrl":"string" }, "prepayAmount":1.17, "totalAmount":1.17,
	 * "restAmount":1.17
	 */
	private PayFeedback payFeedback;
	private String prepayAmount;
	private String totalAmount;
	private String restAmount;
	private float refundAmount;

	public float getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(float refundAmount) {
		this.refundAmount = refundAmount;
	}

	public PayFeedback getPayFeedback() {
		return payFeedback;
	}

	public void setPayFeedback(PayFeedback payFeedback) {
		this.payFeedback = payFeedback;
	}

	public String getPrepayAmount() {
		return prepayAmount;
	}

	public void setPrepayAmount(String prepayAmount) {
		this.prepayAmount = prepayAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRestAmount() {
		return restAmount;
	}

	public void setRestAmount(String restAmount) {
		this.restAmount = restAmount;
	}

	public class PayFeedback {
		private long id;
		private long payId;
		private int satTotal;
		private int satCeremony;
		private int satArrange;
		private int satFr;
		private int satLife;
		private int satSku;
		private String opinion;
		private String signUrl;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getPayId() {
			return payId;
		}

		public void setPayId(long payId) {
			this.payId = payId;
		}

		public int getSatTotal() {
			return satTotal;
		}

		public void setSatTotal(int satTotal) {
			this.satTotal = satTotal;
		}

		public int getSatCeremony() {
			return satCeremony;
		}

		public void setSatCeremony(int satCeremony) {
			this.satCeremony = satCeremony;
		}

		public int getSatArrange() {
			return satArrange;
		}

		public void setSatArrange(int satArrange) {
			this.satArrange = satArrange;
		}

		public int getSatFr() {
			return satFr;
		}

		public void setSatFr(int satFr) {
			this.satFr = satFr;
		}

		public int getSatLife() {
			return satLife;
		}

		public void setSatLife(int satLife) {
			this.satLife = satLife;
		}

		public int getSatSku() {
			return satSku;
		}

		public void setSatSku(int satSku) {
			this.satSku = satSku;
		}

		public String getOpinion() {
			return opinion;
		}

		public void setOpinion(String opinion) {
			this.opinion = opinion;
		}

		public String getSignUrl() {
			return signUrl;
		}

		public void setSignUrl(String signUrl) {
			this.signUrl = signUrl;
		}

	}
}
