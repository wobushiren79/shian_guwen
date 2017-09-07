package com.shian.shianlife.bean;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListGroupBean {
    private String className;
    private boolean isCheck;
    private boolean isEditMode;


    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isEditMode() {
        return isEditMode;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
