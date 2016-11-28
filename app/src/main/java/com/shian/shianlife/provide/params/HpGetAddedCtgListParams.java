package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * 获取增值服务产品分类
 * 
 * @author w9433
 *
 */
public class HpGetAddedCtgListParams extends BaseHttpParams {

	long projectId;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

}
