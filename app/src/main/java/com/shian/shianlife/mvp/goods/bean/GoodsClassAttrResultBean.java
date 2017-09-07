package com.shian.shianlife.mvp.goods.bean;

/**
 * Created by zm.
 */

public class GoodsClassAttrResultBean {
    private Long id;
    private String name;//:名字
    private Long apec_id;//:分类ID
    private String title_img;//:分类属性标题图

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

    public Long getApec_id() {
        return apec_id;
    }

    public void setApec_id(Long apec_id) {
        this.apec_id = apec_id;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }
}
