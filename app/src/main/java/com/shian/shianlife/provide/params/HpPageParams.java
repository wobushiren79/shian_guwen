package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

public class HpPageParams extends BaseHttpParams {
	
	int pageNum;
	int pageSize;
	long userId;
	int ctgId;
	
	public int getCtgId() {
		return ctgId;
	}

	public void setCtgId(int ctgId) {
		this.ctgId = ctgId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
