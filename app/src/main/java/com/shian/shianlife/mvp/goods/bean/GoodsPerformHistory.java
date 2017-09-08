package com.shian.shianlife.mvp.goods.bean;


import com.shian.shianlife.base.BaseEntity;

/**
 * 类名称：GoodsPerformHistory 实体
 * 创建人： CQ
 * 创建时间：2017-07-20
 */
public class GoodsPerformHistory extends BaseEntity {
	/**
	 * 订单执行信息表ID
	 */
    private Long performId;

	/**
	 * 执行完成提交文字（多次提交服务完成时的文字）
	 */
    private String completeInfo;

	/**
	 * 执行完成提交图片（单次不超过3张，分列多次提交服务完成的照片，用，隔开）
	 */
    private String completePic;

	/**
	 * 审核时间
	 */
    private String auditTime;

	/**
	 * 审核文字
	 */
    private String auditInfo;

	/**
	 * 审核结果：0未审核  1审核通过 2审核未通过
	 */
    private Integer auditResult;

	/**
	 * 审核人员id
	 */
    private Long auditorId;

	public Long getPerformId() {
		return performId;
	}

	public void setPerformId(Long performId) {
		this.performId = performId;
	}

	public String getCompleteInfo() {
		return completeInfo;
	}

	public void setCompleteInfo(String completeInfo) {
		this.completeInfo = completeInfo;
	}

	public String getCompletePic() {
		return completePic;
	}

	public void setCompletePic(String completePic) {
		this.completePic = completePic;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}
}
