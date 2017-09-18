package com.shian.shianlife.mvp.goods.bean;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsBean extends BaseHttpParams {
    private Long lobel_id;
    private Integer channel_id;

    public Long getLobel_id() {
        return lobel_id;
    }

    public void setLobel_id(Long lobel_id) {
        this.lobel_id = lobel_id;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }
}
