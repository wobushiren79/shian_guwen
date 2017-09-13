package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsOrderListBean extends BaseHttpParams {


    private int pageSize; //每页显示记录数
    private int pageNumber; //当前页
    private Content content;//内容


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public static class Content {
        private Integer payStatus;
        private List<Integer> orderStatus;

        public Integer getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(Integer payStatus) {
            this.payStatus = payStatus;
        }

        public List<Integer> getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(List<Integer> orderStatus) {
            this.orderStatus = orderStatus;
        }
    }
}
