package com.shian.shianlife.provide.result;

import java.util.List;

import com.shian.shianlife.provide.model.ConsultPrereadyModel;

public class HrGetCustomerPreready {
	/**
	 * "consultPrereadies":{ "consultPrereadies":[ { "id":150, "consultId":150,
	 * "fileName":"string", "fileUrl":"string", "statusFlag":2 } ] } }
	 */
	private List<ConsultPrereadyModel> consultPrereadies;
	private String completeResult;

	public String getCompleteResult() {
		return completeResult;
	}

	public void setCompleteResult(String completeResult) {
		this.completeResult = completeResult;
	}

	public List<ConsultPrereadyModel> getConsultPrereadies() {
		return consultPrereadies;
	}

	public void setConsultPrereadies(List<ConsultPrereadyModel> consultPrereadies) {
		this.consultPrereadies = consultPrereadies;
	}

}
