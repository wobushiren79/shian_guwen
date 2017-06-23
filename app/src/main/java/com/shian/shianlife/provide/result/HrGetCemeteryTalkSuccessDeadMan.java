package com.shian.shianlife.provide.result;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HrGetCemeteryTalkSuccessDeadMan extends BaseHttpParams{
    private List<OrderDeath> list;

    public List<OrderDeath> getList() {
        return list;
    }

    public void setList(List<OrderDeath> list) {
        this.list = list;
    }

    public static class OrderDeath {
        private Long deadId;
        /**
         * 订单id
         */
        private Long orderId;
        /**
         * 名字
         */
        private String name;
        /**
         * 年龄
         */
        private String age;
        /**
         * 性别
         */
        private String sex;
        /**
         * 现状
         */
        private String status;
        /**
         * 身份证
         */
        private String idCardNo;
        /**
         * 去世时间
         */
        private String deathTime;
        /**
         * 备注
         */
        private String remark;

        public Long getDeadId() {
            return deadId;
        }

        public void setDeadId(Long deadId) {
            this.deadId = deadId;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getDeathTime() {
            return deathTime;
        }

        public void setDeathTime(String deathTime) {
            this.deathTime = deathTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}
