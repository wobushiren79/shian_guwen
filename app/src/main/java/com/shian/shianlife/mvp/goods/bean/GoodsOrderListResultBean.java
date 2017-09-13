package com.shian.shianlife.mvp.goods.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsOrderListResultBean {
    private int pageSize; //每页显示记录数
    private int pageNumber;    //当前页
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

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public static class Content {


        /**
         * order_status : 0
         * service_way : 0
         * service_location : 四川 阿坝藏族羌族自治区asdf
         * total_price : 30020000
         * check_order : 0
         * payment_status : 0
         * order_number : DX1709120010255
         * customer_phone : 15198088403
         * id : 402
         * customer_name : zhang3
         * created_by : 6
         * show_total_price : 208000000
         */

        private Integer order_status;
        private Integer service_way;
        private String service_location;
        private Integer total_price;
        private Integer check_order;
        private Integer payment_status;
        private String order_number;
        private String customer_phone;
        private Long id;
        private String customer_name;
        private Long created_by;
        private Integer show_total_price;
        private String book_time;

        public String getBook_time() {
            return book_time;
        }

        public void setBook_time(String book_time) {
            this.book_time = book_time;
        }

        public Integer getOrder_status() {
            return order_status;
        }

        public void setOrder_status(Integer order_status) {
            this.order_status = order_status;
        }

        public Integer getService_way() {
            return service_way;
        }

        public void setService_way(Integer service_way) {
            this.service_way = service_way;
        }

        public String getService_location() {
            return service_location;
        }

        public void setService_location(String service_location) {
            this.service_location = service_location;
        }

        public Integer getTotal_price() {
            return total_price;
        }

        public void setTotal_price(Integer total_price) {
            this.total_price = total_price;
        }

        public Integer getCheck_order() {
            return check_order;
        }

        public void setCheck_order(Integer check_order) {
            this.check_order = check_order;
        }

        public Integer getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(Integer payment_status) {
            this.payment_status = payment_status;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public Long getCreated_by() {
            return created_by;
        }

        public void setCreated_by(Long created_by) {
            this.created_by = created_by;
        }

        public Integer getShow_total_price() {
            return show_total_price;
        }

        public void setShow_total_price(Integer show_total_price) {
            this.show_total_price = show_total_price;
        }
    }
}
