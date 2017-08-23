package com.shian.shianlife.provide.phpparams;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class PHPHpHotIssuseParams extends BaseHttpParams {
    private int type;
    private int number;
    private int pagerNumber;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(int pagerNumber) {
        this.pagerNumber = pagerNumber;
    }
}
