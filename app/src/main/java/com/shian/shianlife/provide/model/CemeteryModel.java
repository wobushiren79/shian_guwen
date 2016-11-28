package com.shian.shianlife.provide.model;

import java.util.List;

/**
 * 公墓
 * 
 * @author w9433
 *
 */
public class CemeteryModel {
	String cemeteryName;// 公墓名字
	int cemeteryId;// 公墓ID

	List<CtgItemModel> ctgItems;// 套餐明细

	public String getCemeteryName() {
		return cemeteryName;
	}

	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}

	public int getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(int cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	public List<CtgItemModel> getCtgItems() {
		return ctgItems;
	}

	public void setCtgItems(List<CtgItemModel> ctgItems) {
		this.ctgItems = ctgItems;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return cemeteryName;
	}

}
