package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.PageBean;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface UserService {
    public ObjectId addUser(MongoUser user);

    public MongoUser getUser(String name);

    public MongoUser getUserByLoginName(String loginName, String field);

    public List<MongoUser> getUserPage(PageBean<MongoUser> pageModel, Map<String, Object> conditions);

    public int updateUserProp(String prop, String field, String userName);

    public MongoUser getUserById(String objId);

    public int updateUserStatus(ObjectId objid, boolean flag);

    public int updateUserProperty(Object prop, String field, org.bson.types.ObjectId objId);

    public MongoUser getUserByBind(String objId);

    public int updateUserHead(MongoUser user);


    public int deleteUser(String objId);

    public int updateUserInfo(String objId, Map<String, Object> updatemap);

    public int deleteUsers(List<MongoUser> users);


}
