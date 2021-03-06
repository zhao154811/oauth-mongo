package com.enlinkmob.ucenterapi.model;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * 第三方用户资料表
 * Created by Zhaowy on 2014/6/11.
 */
@Document(collection = "customerUserInfo")
public class CustomerUserInfo extends BaseEntity {

    private static final long serialVersionUID = 7807367296403910889L;
    @Indexed(name = "cui", unique = true, dropDups = true, background = true, direction = IndexDirection.ASCENDING)
    private String appUniqueId;  //第三方平台用户唯一id 微信使用unionId

    private Map<String, String> openIds;//openId为微信使用  因为微信用户对各个appid的openid都是不同的所以流一个字段记录所有的openId  key为权限系统提供的resourceId,value为openid
    private String infoJson;  //第三方平台用户资料json
    private String sourceApp;//来源app简称
    @DBRef
    private MongoUser user;  //关联用户资料
//	public ObjectId get_id() {
//		return _id;
//	}
//
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}


    public Map<String, String> getOpenIds() {
        return openIds;
    }

    public void setOpenIds(Map<String, String> openIds) {
        this.openIds = openIds;
    }

    public String getAppUniqueId() {
        return appUniqueId;
    }

    public void setAppUniqueId(String appUniqueId) {
        this.appUniqueId = appUniqueId;
    }

    public String getInfoJson() {
        return infoJson;
    }

    public void setInfoJson(String infoJson) {
        this.infoJson = infoJson;
    }

    public MongoUser getUser() {
        return user;
    }

    public void setUser(MongoUser user) {
        this.user = user;
    }

    public String getSourceApp() {
        return sourceApp;
    }

    public void setSourceApp(String sourceApp) {
        this.sourceApp = sourceApp;
    }
}
