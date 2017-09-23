package com.shian.shianlife.mvp.userinfo.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class UserInfoIntegralListBean extends BaseHttpParams{
    private Integer pageNumber;//	int	页号
    private Integer pageSize;//int	分页大小

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
