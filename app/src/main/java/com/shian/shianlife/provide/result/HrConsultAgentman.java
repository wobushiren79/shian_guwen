package com.shian.shianlife.provide.result;

public class HrConsultAgentman {
    HrConsultAgentmans consultAgentman;

    public HrConsultAgentmans getConsultAgentman() {
        return consultAgentman;
    }

    public void setConsultAgentman(HrConsultAgentmans consultAgentman) {
        this.consultAgentman = consultAgentman;
    }

    public class HrConsultAgentmans {
        /**
         * "id":165, "consultId":165, "name":"string", "relation":6,
         * "linkInfo":"string", "birthday":1461394663029, "address":"string",
         * "addressArea":165, "addressCity":165, "addressProvince":165,
         * "addressSuffix":"string"
         */
        private long consultId;
        private String relation;
        private String linkInfo;
        private String birthday;
        private String address;
        private int addressArea;
        private int addressCity;
        private int addressProvince;
        private String addressSuffix;
        private long id;
        private String name;

        private String zsLocation;
        private String location;
        private String cardId;
        private String email;
        private String remark;
        private String deadmanLocation;//往生者地址，String
        private String deadLocation;// 去世地址，String

        public String getDeadmanLocation() {
            return deadmanLocation;
        }

        public void setDeadmanLocation(String deadmanLocation) {
            this.deadmanLocation = deadmanLocation;
        }

        public String getDeadLocation() {
            return deadLocation;
        }

        public void setDeadLocation(String deadLocation) {
            this.deadLocation = deadLocation;
        }

        public String getZsLocation() {
            return zsLocation;
        }

        public void setZsLocation(String zsLocation) {
            this.zsLocation = zsLocation;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getConsultId() {
            return consultId;
        }

        public void setConsultId(long consultId) {
            this.consultId = consultId;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getLinkInfo() {
            return linkInfo;
        }

        public void setLinkInfo(String linkInfo) {
            this.linkInfo = linkInfo;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(int addressArea) {
            this.addressArea = addressArea;
        }

        public int getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(int addressCity) {
            this.addressCity = addressCity;
        }

        public int getAddressProvince() {
            return addressProvince;
        }

        public void setAddressProvince(int addressProvince) {
            this.addressProvince = addressProvince;
        }

        public String getAddressSuffix() {
            return addressSuffix;
        }

        public void setAddressSuffix(String addressSuffix) {
            this.addressSuffix = addressSuffix;
        }

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
