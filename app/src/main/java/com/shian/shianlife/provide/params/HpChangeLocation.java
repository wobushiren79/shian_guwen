package com.shian.shianlife.provide.params;

        import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/27.
 */

public class HpChangeLocation extends BaseHttpParams {
    private long consultId;//咨询id
    private String addressDetail;// 详细地址
    private int operationType;//类型，值：1经办人地址、2治丧地址、3往生者地址、4去世地址、5殡仪馆地址、6治丧约见地址、7出殡前地址、8出殡当天地址、9客户当前地址


    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
}
