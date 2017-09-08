package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class StoreOrderGetPerformResultBean {
    private GoodsPerform goodsPerform;
    private GoodsExpress goodsExpress;//快递单号


    public GoodsPerform getGoodsPerform() {
        return goodsPerform;
    }

    public void setGoodsPerform(GoodsPerform goodsPerform) {
        this.goodsPerform = goodsPerform;
    }

    public GoodsExpress getGoodsExpress() {
        return goodsExpress;
    }

    public void setGoodsExpress(GoodsExpress goodsExpress) {
        this.goodsExpress = goodsExpress;
    }

    public static class GoodsPerform {
        /**
         * 执行单id
         */
        private Long id;
        /**
         * 执行商品ID
         */
        private Long goodsItemId;
        /**
         * orderId
         */
        private Long orderId;

        /**
         * 执行方式:0同城送达 1上门服务 2快递物流
         */
        private Integer performWay;

        /**
         * 执行人姓名
         */
        private String performUserName;

        /**
         * 执行人电话
         */
        private String performUserPhone;

        /**
         * 执行备注
         */
        private String performComment;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getGoodsItemId() {
            return goodsItemId;
        }

        public void setGoodsItemId(Long goodsItemId) {
            this.goodsItemId = goodsItemId;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }


        public Integer getPerformWay() {
            return performWay;
        }

        public void setPerformWay(Integer performWay) {
            this.performWay = performWay;
        }

        public String getPerformUserName() {
            return performUserName;
        }

        public void setPerformUserName(String performUserName) {
            this.performUserName = performUserName;
        }

        public String getPerformUserPhone() {
            return performUserPhone;
        }

        public void setPerformUserPhone(String performUserPhone) {
            this.performUserPhone = performUserPhone;
        }

        public String getPerformComment() {
            return performComment;
        }

        public void setPerformComment(String performComment) {
            this.performComment = performComment;
        }
    }

    public static class GoodsExpress {
        /**
         * 快递公司
         */
        private String expressName;

        /**
         * 快递单号
         */
        private String deliveryNumber;


        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getDeliveryNumber() {
            return deliveryNumber;
        }

        public void setDeliveryNumber(String deliveryNumber) {
            this.deliveryNumber = deliveryNumber;
        }
    }
}
