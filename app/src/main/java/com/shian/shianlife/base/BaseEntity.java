package com.shian.shianlife.base;


import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */
public class BaseEntity implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 更新时间
     */
    private String updatedAt;
    /**
     * 创建人员
     */
    private Long createdBy;
    /**
     * 更新人员
     */
    private Long updatedBy;
    /**
     * 是否有效，值：1有效(默认)、0失效
     */
    private Integer valid;

    /**
     * 软删除, 值: 0不删除(默认) 1删除
     */
    private Integer remove;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getRemove() {
        return remove;
    }

    public void setRemove(Integer remove) {
        this.remove = remove;
    }
}
