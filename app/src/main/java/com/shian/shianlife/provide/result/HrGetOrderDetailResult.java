package com.shian.shianlife.provide.result;

import java.util.List;

import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.model.PayInfoModel;
import com.shian.shianlife.provide.model.ProjectItemModel;

public class HrGetOrderDetailResult {

	List<ProjectItemModel> projectItems;

	BordModel board;

	PayInfoModel payInfo;

	public List<ProjectItemModel> getProjectItems() {
		return projectItems;
	}

	public void setProjectItems(List<ProjectItemModel> projectItems) {
		this.projectItems = projectItems;
	}

	public BordModel getBoard() {
		return board;
	}

	public void setBoard(BordModel board) {
		this.board = board;
	}

	public PayInfoModel getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(PayInfoModel payInfo) {
		this.payInfo = payInfo;
	}

}
