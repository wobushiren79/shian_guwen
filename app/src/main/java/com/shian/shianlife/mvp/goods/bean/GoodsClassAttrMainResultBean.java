package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainResultBean {

    /**
     * id : 1
     * name : 骨灰盒
     * spec_use_id : 2
     * pre_perform : 0
     * channel_id : 1,2
     */

    private Long id;
    private String name;
    private String spec_use_id;
    private String pre_perform;
    private String channel_id;

    private Integer picId;


    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

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

    public String getPre_perform() {
        return pre_perform;
    }

    public void setPre_perform(String pre_perform) {
        this.pre_perform = pre_perform;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

}
