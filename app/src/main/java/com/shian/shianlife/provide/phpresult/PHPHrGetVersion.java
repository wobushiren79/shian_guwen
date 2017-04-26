package com.shian.shianlife.provide.phpresult;

import java.util.List;

/**
 * Created by zm.
 */

public class PHPHrGetVersion {

    //        String versionName;//版本名字
//        String versionNum;  //版本号
//        int isImportant;  //是否必须更新(0非必须   1必须)
//        String updataTime; //更新时间
//        String updataTitle;   //更新标题
//        String updataContent;//更新说明
//        String remark;//     备注
//        String appDownLoadUrl;  //app下载地址
    private List<ItemsBean> items;
    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }
    public static class ItemsBean{
        /**
         * id : 7
         * appId : 1
         * versionName : test
         * versionNum : 1.0
         * isImportant : 0
         * updataTime : 2017-04-26 17:00:59
         * updataTitle : test
         * updataContent :   testset
         * remark : setset
         * appDownLoadUrl : Edition/2017-04-26/590061cbba746.apk
         * banner : /Public/Uploads/Edition/2017-04-26/590061cbba746.apk
         */

        private String id;
        private String appId;
        private String versionName;
        private String versionNum;
        private String isImportant;
        private String updataTime;
        private String updataTitle;
        private String updataContent;
        private String remark;
        private String appDownLoadUrl;
        private String banner;





        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(String versionNum) {
            this.versionNum = versionNum;
        }

        public String getIsImportant() {
            return isImportant;
        }

        public void setIsImportant(String isImportant) {
            this.isImportant = isImportant;
        }

        public String getUpdataTime() {
            return updataTime;
        }

        public void setUpdataTime(String updataTime) {
            this.updataTime = updataTime;
        }

        public String getUpdataTitle() {
            return updataTitle;
        }

        public void setUpdataTitle(String updataTitle) {
            this.updataTitle = updataTitle;
        }

        public String getUpdataContent() {
            return updataContent;
        }

        public void setUpdataContent(String updataContent) {
            this.updataContent = updataContent;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAppDownLoadUrl() {
            return appDownLoadUrl;
        }

        public void setAppDownLoadUrl(String appDownLoadUrl) {
            this.appDownLoadUrl = appDownLoadUrl;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }

}
