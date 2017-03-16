package com.shian.shianlife.provide.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 2016/7/26.
 */
public class HrUserInfo implements Serializable{
    private int appStatus;
    private String name;
    private String headImg;
    private String mobile;
    private String serviceArea;
    private String email;
    private float avgSatis;
    private int serviceSuccessSum;
    private String introduce;
    private String jobNo;
    private List<Role> roles;
    private List<CtgItem> ctgItems;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getAvgSatis() {
        return avgSatis;
    }

    public void setAvgSatis(float avgSatis) {
        this.avgSatis = avgSatis;
    }

    public int getServiceSuccessSum() {
        return serviceSuccessSum;
    }

    public void setServiceSuccessSum(int serviceSuccessSum) {
        this.serviceSuccessSum = serviceSuccessSum;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CtgItem> getCtgItems() {
        return ctgItems;
    }

    public void setCtgItems(List<CtgItem> ctgItems) {
        this.ctgItems = ctgItems;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }

    public static class Role{
        private long id;
        private String name;
        private int statusFlag;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatusFlag() {
            return statusFlag;
        }

        public void setStatusFlag(int statusFlag) {
            this.statusFlag = statusFlag;
        }
    }

    public static class CtgItem{
        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
