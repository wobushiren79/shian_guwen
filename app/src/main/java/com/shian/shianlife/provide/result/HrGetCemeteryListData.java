package com.shian.shianlife.provide.result;

import com.shian.shianlife.provide.model.CemeteryOrderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class HrGetCemeteryListData {
    List<CemeteryOrderModel> list;
    private int total;
    private int pageNum;
    private int pageSize;
    private int pages;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<CemeteryOrderModel> getList() {
        return list;
    }

    public void setList(List<CemeteryOrderModel> list) {
        this.list = list;
    }
}
