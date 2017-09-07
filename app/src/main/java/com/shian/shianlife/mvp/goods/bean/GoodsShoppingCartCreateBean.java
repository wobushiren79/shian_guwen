package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsShoppingCartCreateBean extends BaseHttpParams {
    private List<Content> list;

    public List<Content> getList() {
        return list;
    }

    public void setList(List<Content> list) {
        this.list = list;
    }

    public static class Content {
        /**
         * 商品ID
         */
        private Long goodsId;

        /**
         * 规格商品ID
         */
        private Long goodsSpecId;

        /**
         * 分类属性ID
         */
        private Long classifyAttrId;

        /**
         * 分类ID
         */
        private Long classifyId;

        /**
         * 商品数量
         */
        private Integer specNum;

        /**
         * 商品渠道ID
         */
        private Integer channelId;

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
