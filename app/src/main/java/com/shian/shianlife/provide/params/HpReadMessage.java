package com.shian.shianlife.provide.params;

import java.util.List;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpReadMessage extends BaseHttpParams{
	private List<Long> msgIds;

	public List<Long> getMsgIds() {
		return msgIds;
	}

	public void setMsgIds(List<Long> msgIds) {
		this.msgIds = msgIds;
	}
	
}
