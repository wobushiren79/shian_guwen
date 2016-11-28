package com.shian.shianlife.provide.result;

public class HrOrderInvoic {
	private Invoic invoice;

	

	public Invoic getInvoice() {
		return invoice;
	}



	public void setInvoice(Invoic invoice) {
		this.invoice = invoice;
	}



	public class Invoic {
		/**
		 * "id":121, "orderId":121, "type":1, "content":"string",
		 * "header":"string", "recName":"string", "recPhone":"string",
		 * "recAddressProvince":121, "recAddressCity":121, "recAddressArea":121,
		 * "recAddressSuffix":"string", "recAddress":"string"
		 */
		private long id;
		private long orderId;
		private int type;
		private String content;
		private String header;
		private String recName;
		private String recPhone;
		private int recAddressProvince;
		private int recAddressCity;
		private int recAddressArea;
		private String recAddressSuffix;
		private String recAddress;

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

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public String getRecName() {
			return recName;
		}

		public void setRecName(String recName) {
			this.recName = recName;
		}

		public String getRecPhone() {
			return recPhone;
		}

		public void setRecPhone(String recPhone) {
			this.recPhone = recPhone;
		}

		public int getRecAddressProvince() {
			return recAddressProvince;
		}

		public void setRecAddressProvince(int recAddressProvince) {
			this.recAddressProvince = recAddressProvince;
		}

		public int getRecAddressCity() {
			return recAddressCity;
		}

		public void setRecAddressCity(int recAddressCity) {
			this.recAddressCity = recAddressCity;
		}

		public int getRecAddressArea() {
			return recAddressArea;
		}

		public void setRecAddressArea(int recAddressArea) {
			this.recAddressArea = recAddressArea;
		}

		public String getRecAddressSuffix() {
			return recAddressSuffix;
		}

		public void setRecAddressSuffix(String recAddressSuffix) {
			this.recAddressSuffix = recAddressSuffix;
		}

		public String getRecAddress() {
			return recAddress;
		}

		public void setRecAddress(String recAddress) {
			this.recAddress = recAddress;
		}

	}
}
