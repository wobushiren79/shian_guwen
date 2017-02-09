package com.shian.shianlife.provide.result;

import com.shian.shianlife.provide.model.CemeteryStructureModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HrGetCemeteryStructure {
    List<CemeteryStructureModel> items;

    public List<CemeteryStructureModel> getItems() {
        return items;
    }

    public void setItems(List<CemeteryStructureModel> items) {
        this.items = items;
    }
}
