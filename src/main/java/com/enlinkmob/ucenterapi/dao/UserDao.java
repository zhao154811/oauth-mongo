package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.PageBean;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;


public interface UserDao extends BaseDao<MongoUser> {
    public ObjectId addUser(MongoUser user);

    MongoUser getUser(String username);

    public List<MongoUser> getUserPage(PageBean<MongoUser> pageModel, Map<String, Object> conditions);

    MongoUser getUserByLoginName(String loginName);

    public int updateUserProp(String prop, String field, String userName);

    public com.enlinkmob.ucenterapi.model.MongoUser getUserById(String objId);

    public int updateUserStatus(ObjectId objid, boolean flag);

    public int updateUserProperty(Object prop, String field, org.bson.types.ObjectId objId);


    public MongoUser getUserByBind(String objId);

    public int updateUserHead(MongoUser mongoUser);


    public int deleteUser(String objId);


    public int deleteUsers(List<MongoUser> mongoUsers);


}
