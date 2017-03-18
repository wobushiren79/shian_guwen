package com.shian.shianlife.provide.phpmodel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SiftListData implements Serializable{
    private int id;//  精选Id
    private String img;//   图片地址
    private String title;//   标题
    private String time;//  时间
    private int praiseNum;//  点赞数量
    private int collectionNum;//  收藏数量
    private int isPraise;//  是否点赞
    private int isCollection;//    是否收藏

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }



    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }
}
