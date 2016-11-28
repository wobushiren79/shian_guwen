package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;
/**
 * 获取地址
 * @author w9433
 *
 */
public class HpLoadAddressParams extends BaseHttpParams {

	private int type;
	private int relId;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRelId() {
		return relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

}
