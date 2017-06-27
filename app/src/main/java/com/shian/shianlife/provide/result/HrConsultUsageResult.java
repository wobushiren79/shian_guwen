package com.shian.shianlife.provide.result;

public class HrConsultUsageResult {
    HrConsultUsageResults consultUsage;

    public HrConsultUsageResults getConsultUsage() {
        return consultUsage;
    }

    public void setConsultUsage(HrConsultUsageResults consultUsage) {
        this.consultUsage = consultUsage;
    }

    public class HrConsultUsageResults {
        /**
         * "age":"string", "birthday":1461393773005, "consultId":11,
         * "curAddress":"string", "curAddressArea":11, "curAddressCity":11,
         * "curAddressProvince":11, "curAddressSuffix":"string",
         * "dieTime":1461393773005, "height":"string", "id":11,
         * "intimeReady":"string", "job":2, "name":"string", "note":"string",
         * "sex":2, "shoeSize":"string", "state":2, "will":"string"
         */
        private String age;
        private String birthday;
        private String curAddress;
        private int curAddressArea;
        private int curAddressCity;
        private int curAddressProvince;
        private String curAddressSuffix;
        private String dieTime;
        private String height;
        private long id;
        private String intimeReady;
        private int job;
        private String name;
        private String note;
        private int sex;
        private String shoeSize;
        private String state;
        private String will;

        private String cardId;//往生者身份证号码
        private String otherHealth;//另一半信息
        private String clothesData;//寿衣信息
        private String location;//往生者地址

        private String deadLocation; //去世地址，String
        private String agentmanLocation; //经办人地点，String
        private String zsLocation; //治丧地址，String

        public String getDeadLocation() {
            return deadLocation;
        }

        public void setDeadLocation(String deadLocation) {
            this.deadLocation = deadLocation;
        }

        public String getAgentmanLocation() {
            return agentmanLocation;
        }

        public void setAgentmanLocation(String agentmanLocation) {
            this.agentmanLocation = agentmanLocation;
        }

        public String getZsLocation() {
            return zsLocation;
        }

        public void setZsLocation(String zsLocation) {
            this.zsLocation = zsLocation;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getOtherHealth() {
            return otherHealth;
        }

        public void setOtherHealth(String otherHealth) {
            this.otherHealth = otherHealth;
        }

        public String getClothesData() {
            return clothesData;
        }

        public void setClothesData(String clothesData) {
            this.clothesData = clothesData;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }


        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }



        public String getCurAddress() {
            return curAddress;
        }

        public void setCurAddress(String curAddress) {
            this.curAddress = curAddress;
        }

        public int getCurAddressArea() {
            return curAddressArea;
        }

        public void setCurAddressArea(int curAddressArea) {
            this.curAddressArea = curAddressArea;
        }

        public int getCurAddressCity() {
            return curAddressCity;
        }

        public void setCurAddressCity(int curAddressCity) {
            this.curAddressCity = curAddressCity;
        }

        public int getCurAddressProvince() {
            return curAddressProvince;
        }

        public void setCurAddressProvince(int curAddressProvince) {
            this.curAddressProvince = curAddressProvince;
        }

        public String getCurAddressSuffix() {
            return curAddressSuffix;
        }

        public void setCurAddressSuffix(String curAddressSuffix) {
            this.curAddressSuffix = curAddressSuffix;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getDieTime() {
            return dieTime;
        }

        public void setDieTime(String dieTime) {
            this.dieTime = dieTime;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIntimeReady() {
            return intimeReady;
        }

        public void setIntimeReady(String intimeReady) {
            this.intimeReady = intimeReady;
        }

        public int getJob() {
            return job;
        }

        public void setJob(int job) {
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getShoeSize() {
            return shoeSize;
        }

        public void setShoeSize(String shoeSize) {
            this.shoeSize = shoeSize;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getWill() {
            return will;
        }

        public void setWill(String will) {
            this.will = will;
        }
    }
}
