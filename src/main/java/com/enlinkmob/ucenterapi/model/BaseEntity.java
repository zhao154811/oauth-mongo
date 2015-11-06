package com.enlinkmob.ucenterapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Zhaowy on 2015/4/27.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 7222978254461666865L;
    @Id
    protected ObjectId _id;
    protected int status;
    protected Date createTime;
    protected Date modifyTime;
    @Transient
    protected String objId;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
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

    public String getObjId() {
        if (_id != null) {
            return _id.toString();
        } else {
            return "";
        }

    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
