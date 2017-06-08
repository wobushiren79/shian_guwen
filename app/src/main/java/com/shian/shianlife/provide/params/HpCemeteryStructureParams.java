package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HpCemeteryStructureParams extends BaseHttpParams {
    private int itemType;//公墓结构项类型，值：0,公墓。1,苑，2,区，3排，4 号
    private long itemId;//
    private long parkIdTemp;//园区id，注：仅类型为4墓位号时，才启用园区id
    private String rowTemp;//排  注：仅类型为4墓位号时，才启用排

    public String getRowTemp() {
        return rowTemp;
    }

    public void setRowTemp(String rowTemp) {
        this.rowTemp = rowTemp;
    }

    /**
     * 登录通行key
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getParkIdTemp() {
        return parkIdTemp;
    }

    public void setParkIdTemp(long parkIdTemp) {
        this.parkIdTemp = parkIdTemp;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
