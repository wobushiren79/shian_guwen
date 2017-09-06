package com.shian.shianlife.mvp.goods.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListResultBean {
    private int pageSize; //每页显示记录数
    private int pageNumber; //当前页
    private int total;  //总记录数
    private List<Content> content;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public static class Content {
        /**
         * id : 17
         * createdAt : 2017-08-30 14:11:41
         * updatedAt : 2017-08-30 14:11:41
         * createdBy : 3
         * updatedBy : 3
         * valid : 1
         * remove : null
         * accountId : 3
         * goodsId : 17
         * goodsSpecId : 15
         * classifyAttrId : 6
         * classifyId : 3
         * specNum : 1
         * channelId : 1
         */

        private Long id;
        private String createdAt;
        private String updatedAt;
        private Long createdBy;
        private Long updatedBy;
        private Integer valid;
        private Object remove;
        private Long accountId;
        private Long goodsId;
        private Long goodsSpecId;
        private Long classifyAttrId;
        private Long classifyId;
        private Integer specNum;
        private Integer channelId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Long getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
        }

        public Long getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Long updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getValid() {
            return valid;
        }

        public void setValid(Integer valid) {
            this.valid = valid;
        }

        public Object getRemove() {
            return remove;
        }

        public void setRemove(Object remove) {
            this.remove = remove;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public Long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
        }

        public Long getGoodsSpecId() {
            return goodsSpecId;
        }

        public void setGoodsSpecId(Long goodsSpecId) {
            this.goodsSpecId = goodsSpecId;
        }

        public Long getClassifyAttrId() {
            return classifyAttrId;
        }

        public void setClassifyAttrId(Long classifyAttrId) {
            this.classifyAttrId = classifyAttrId;
        }

        public Long getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(Long classifyId) {
            this.classifyId = classifyId;
        }

        public Integer getSpecNum() {
            return specNum;
        }

        public void setSpecNum(Integer specNum) {
            this.specNum = specNum;
        }

        public Integer getChannelId() {
            return channelId;
        }

        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }
    }

}
