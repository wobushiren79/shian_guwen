package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class GoodsDetailsBean extends BaseHttpParams {
    private Long goods_id;
    private int channel_id;
    private Long package_id;

    public Long getPackage_id() {
        return package_id;
    }

    public void setPackage_id(Long package_id) {
        this.package_id = package_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }
}
