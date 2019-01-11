package com.jt.idea.common.po;

import java.io.Serializable;
import java.util.Date;

//pojo基类，完成2个任务，2个日期，实现序列化
public class BasePojo implements Serializable {
    private Date created;
    private Date updated;
    private Integer offset;
    private Integer limit;
 /**
     * 0为不更新
     * 1为更新
     */
    private Boolean isUpdate;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    @Override
    public String toString() {
        return "BasePojo{" +
                "created=" + created +
                ", updated=" + updated +
                ", offset=" + offset +
                ", limit=" + limit +
                ", isUpdate=" + isUpdate +
                '}';
    }
}
