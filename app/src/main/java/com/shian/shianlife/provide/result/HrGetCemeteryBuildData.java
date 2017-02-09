package com.shian.shianlife.provide.result;

import com.shian.shianlife.provide.model.CemeteryNameModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class HrGetCemeteryBuildData {
    private List<CemeteryNameModel> cemeteryLocationList;

    public List<CemeteryNameModel> getCemeteryLocationList() {
        return cemeteryLocationList;
    }

    public void setCemeteryLocationList(List<CemeteryNameModel> cemeteryLocationList) {
        this.cemeteryLocationList = cemeteryLocationList;
    }
}
