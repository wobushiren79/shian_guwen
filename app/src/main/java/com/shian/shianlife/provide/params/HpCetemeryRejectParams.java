package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HpCetemeryRejectParams extends BaseHttpParams {
    private long bespeakAssignId;//咨询指派ID
    private long bespeakId;//咨询ID

    public long getBespeakAssignId() {
        return bespeakAssignId;
    }

    public void setBespeakAssignId(long bespeakAssignId) {
        this.bespeakAssignId = bespeakAssignId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }
}
