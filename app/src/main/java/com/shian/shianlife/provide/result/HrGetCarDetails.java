package com.shian.shianlife.provide.result;

import java.io.Serializable;

/**
 * Created by zm.
 */

public class HrGetCarDetails {
    private Long id;//订单id
    private CarOrder order;
    private Driver driver;
    private Autocar autocar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarOrder getOrder() {
        return order;
    }

    public void setOrder(CarOrder order) {
        this.order = order;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Autocar getAutocar() {
        return autocar;
    }

    public void setAutocar(Autocar autocar) {
        this.autocar = autocar;
    }

    public static class CarOrder extends CarBaseEntity {


        /**
         * 系统外订单编号
         */
        private String orderNo;

        /**
         * 系统内订单编号
         */
        private String sysOrderNo;

        /**
         *
         */
        private Long customerId;

        /**
         * 用车人
         */
        private String connecterName;

        /**
         * 用车人联系号码
         */
        private String connecterMobile;

        /**
         * 用车目的
         */
        private String purpose;

        /**
         * 乘车人数
         */
        private String seats;

        /**
         * 出发地
         */
        private String source;

        /**
         * 出发地_经度
         */
        private String sourceLongitude;

        /**
         * 出发地_维度
         */
        private String sourceLatitude;

        /**
         * 目的地
         */
        private String target;

        /**
         * 目的地
         */
        private String targetLongitude;

        /**
         * 目的地_维度
         */
        private String targetLatitude;

        /**
         * 预约用车时间
         */
        //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//	@JSONField(format = "yyyy-MM-dd hh:mm")
        private String appointmentTime;

        /**
         * 司机
         */
        private Long driverId;

        /**
         * 备注
         */
        private String remark;

        /**
         * 订单状态0待派车 1已分配/待接单 2待取车 3待出发 4去接人 5等上车 6已上车 7已送达 8成功服务 9已取消
         */
        private Integer status;

        /**
         * 当前经度
         */
        private String curLongitude;

        /**
         * 当前纬度
         */
        private String curLatitude;

        /**
         * 订单状态(待派车 已分配/待接单 待取车 待出发)0未执行  (去接人 等上车 已上车 已送达)1忙碌 (成功服务 已取消)订单结束
         */
        private Integer exStatus;

        /**
         * 申请人
         */
        private String proposerName;

        /**
         * 申请人电话
         */
        private String proposerMobile;
        /**
         * 取消订单原因
         */
        private String cancelReason;

        public String getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSysOrderNo() {
            return sysOrderNo;
        }

        public void setSysOrderNo(String sysOrderNo) {
            this.sysOrderNo = sysOrderNo;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getConnecterName() {
            return connecterName;
        }

        public void setConnecterName(String connecterName) {
            this.connecterName = connecterName;
        }

        public String getConnecterMobile() {
            return connecterMobile;
        }

        public void setConnecterMobile(String connecterMobile) {
            this.connecterMobile = connecterMobile;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getSeats() {
            return seats;
        }

        public void setSeats(String seats) {
            this.seats = seats;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSourceLongitude() {
            return sourceLongitude;
        }

        public void setSourceLongitude(String sourceLongitude) {
            this.sourceLongitude = sourceLongitude;
        }

        public String getSourceLatitude() {
            return sourceLatitude;
        }

        public void setSourceLatitude(String sourceLatitude) {
            this.sourceLatitude = sourceLatitude;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getTargetLongitude() {
            return targetLongitude;
        }

        public void setTargetLongitude(String targetLongitude) {
            this.targetLongitude = targetLongitude;
        }

        public String getTargetLatitude() {
            return targetLatitude;
        }

        public void setTargetLatitude(String targetLatitude) {
            this.targetLatitude = targetLatitude;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(Long driverId) {
            this.driverId = driverId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCurLongitude() {
            return curLongitude;
        }

        public void setCurLongitude(String curLongitude) {
            this.curLongitude = curLongitude;
        }

        public String getCurLatitude() {
            return curLatitude;
        }

        public void setCurLatitude(String curLatitude) {
            this.curLatitude = curLatitude;
        }

        public Integer getExStatus() {
            return exStatus;
        }

        public void setExStatus(Integer exStatus) {
            this.exStatus = exStatus;
        }

        public String getProposerName() {
            return proposerName;
        }

        public void setProposerName(String proposerName) {
            this.proposerName = proposerName;
        }

        public String getProposerMobile() {
            return proposerMobile;
        }

        public void setProposerMobile(String proposerMobile) {
            this.proposerMobile = proposerMobile;
        }
    }

    public static class Driver extends CarBaseEntity {

        /**
         * 编号
         */
        private String code;

        /**
         * 姓名
         */
        private String name;

        /**
         * 性别(0男 1女)
         */
        private Long sex;

        /**
         * 身份证号
         */
        private String idNo;

        /**
         * 联系方式
         */
        private String mobile;

        /**
         * 年龄
         */
        private Long age;

        /**
         * 驾龄(0 1-3年      1 3-5年      2 5-10年     3 10以上)
         */
        private Long exp;

        /**
         * 准驾车型(A1, A2, A3, B1, B2, B3, C1, C2)
         */
        private String licenceType;

        /**
         * 驾照编号
         */
        private String licenceNo;

        /**
         * 地址
         */
        private String address;

        /**
         * 服务区域
         */
        private String zone;

        /**
         * 备注
         */
        private String remark;

        /**
         * 人员状态(0在职   1离职  2休假)
         */
        private Integer status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSex() {
            return sex;
        }

        public void setSex(Long sex) {
            this.sex = sex;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }

        public Long getExp() {
            return exp;
        }

        public void setExp(Long exp) {
            this.exp = exp;
        }

        public String getLicenceType() {
            return licenceType;
        }

        public void setLicenceType(String licenceType) {
            this.licenceType = licenceType;
        }

        public String getLicenceNo() {
            return licenceNo;
        }

        public void setLicenceNo(String licenceNo) {
            this.licenceNo = licenceNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    public static class Autocar extends CarBaseEntity {

        /**
         * 车辆品牌
         */
        private String carBrand;

        /**
         * 编号
         */
        private String code;

        /**
         * 车牌号
         */
        private String number;

        /**
         * 车辆类型(0商务面包 1高级商务 2轿车)
         */
        private Long style;

        /**
         * 油耗
         */
        private String oil;

        /**
         * 颜色
         */
        private String color;

        /**
         * 座位数(2 2座, 5 5座, 7 7座, 11座, 12座, 13座)
         */
        private Long seats;

        /**
         * 最近位置
         */
        private String lastPosition;

        /**
         * 位置经度
         */
        private String lastLongitude;

        /**
         * 位置纬度
         */
        private String lastLatitude;

        /**
         * 车辆使用状态 (0维修中 1年检中 2可使用 3已报废)
         */
        private Long status;

        /**
         * 服务范围
         */
        private String zone;

        /**
         * 备注
         */
        private String remark;

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Long getStyle() {
            return style;
        }

        public void setStyle(Long style) {
            this.style = style;
        }

        public String getOil() {
            return oil;
        }

        public void setOil(String oil) {
            this.oil = oil;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Long getSeats() {
            return seats;
        }

        public void setSeats(Long seats) {
            this.seats = seats;
        }

        public String getLastPosition() {
            return lastPosition;
        }

        public void setLastPosition(String lastPosition) {
            this.lastPosition = lastPosition;
        }

        public String getLastLongitude() {
            return lastLongitude;
        }

        public void setLastLongitude(String lastLongitude) {
            this.lastLongitude = lastLongitude;
        }

        public String getLastLatitude() {
            return lastLatitude;
        }

        public void setLastLatitude(String lastLatitude) {
            this.lastLatitude = lastLatitude;
        }

        public Long getStatus() {
            return status;
        }

        public void setStatus(Long status) {
            this.status = status;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }


    public static class CarBaseEntity implements Serializable {
        /**
         *
         */
        private Long id;
        /**
         * 创建时间
         */
        private String createdAt;
        /**
         * 更新时间
         */
        private String updatedAt;
        /**
         * 创建人员
         */
        private Long createdBy;
        /**
         * 更新人员
         */
        private Long updatedBy;
        /**
         * 是否有效，值：1有效(默认)、0失效
         */
        private Integer valid;

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
    }
}
