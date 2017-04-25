package com.shian.shianlife.provide.phpresult;

/**
 * Created by zm.
 */

public class PHPHrGetVersion {
    String versionName;//版本名字
    String versionNum;  //版本号
    int isImportant;  //是否必须更新(0非必须   1必须)
    String updataTime; //更新时间
    String updataTitle;   //更新标题
    String updataContent;//更新说明
    String remark;//     备注
    String appDownLoadUrl;  //app下载地址

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

    public int getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(int isImportant) {
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
}
