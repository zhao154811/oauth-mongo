package com.enlinkmob.ucenterapi.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaowy on 15/5/19.
 */
public class BaseLongEntity implements Serializable {
    private static final long serialVersionUID = 4960622808020244402L;
    @Id
    protected int _id;
    protected int status;
    protected Date createTime;
    protected Date modifyTime;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
