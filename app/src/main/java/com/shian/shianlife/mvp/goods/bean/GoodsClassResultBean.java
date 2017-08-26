package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsClassResultBean {
    private Long id;
    private String name;//名字
    private String spec_use_id;//分类用途ID     string
    private Integer channel_id;//渠道ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec_use_id() {
        return spec_use_id;
    }

    public void setSpec_use_id(String spec_use_id) {
        this.spec_use_id = spec_use_id;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }
}
