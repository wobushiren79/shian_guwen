package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpOrderInvoic extends BaseHttpParams{
	/**
	 *  "orderId":23,
        "type":1,
        "content":"string",
        "header":"string",
        "payAddress":"string",
        "recName":"string",
        "recPhone":"string",
        "recAddress":{
            "provinceId":23,
            "cityId":23,
            "areaId":23,
            "suffix":"string",
            "address":"string"
        }
	 */
	private long orderId;
	private int type;
	private String content;
	private String header;
	private String payAddress;
	private String recName;
	private String recPhone;
	private HpAddConsultParams.TalkAddress recAddress;
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
	public String getPayAddress() {
		return payAddress;
	}
	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
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
	public HpAddConsultParams.TalkAddress getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(HpAddConsultParams.TalkAddress recAddress) {
		this.recAddress = recAddress;
	}
	
}
