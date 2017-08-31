package com.shian.shianlife.mvp.order.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class OrderShowResultBean {
    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public static class Item {
        private String name;
        private int picId;
        private Class<?> intentClass;
        private boolean hasPermission = false;
        private String url;


        public Class<?> getIntentClass() {
            return intentClass;
        }

        public void setIntentClass(Class<?> intentClass) {
            this.intentClass = intentClass;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }

        public boolean isHasPermission() {
            return hasPermission;
        }

        public void setHasPermission(boolean hasPermission) {
            this.hasPermission = hasPermission;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
