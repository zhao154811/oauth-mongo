package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.UserDao;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.PageBean;
import com.enlinkmob.ucenterapi.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public ObjectId addUser(MongoUser user) {
        return userDao.addUser(user);
    }

    public MongoUser getUser(String name) {
        return this.userDao.getUser(name);
    }

    @Override
    public MongoUser getUserByLoginName(String loginName, String field) {
        return this.userDao.getUserByLoginName(loginName);
    }

    /** (非 Javadoc)
     * <p>Title: updatePassword</p>
     * <p>Description: </p>
     * @param userName
     * @param password
     * @return
     * @see com.enlinkmob.ucenterapi.service.UserService#updatePassword(java.lang.String, java.lang.String)
     */


    /**
     * (非 Javadoc)
     * <p>Title: getUserPage</p>
     * <p>Description: </p>
     *
     * @param pageModel
     * @param conditions
     * @return
     */
    @Override
    public List<MongoUser> getUserPage(PageBean<MongoUser> pageModel,
                                       Map<String, Object> conditions) {
        return this.userDao.getUserPage(pageModel, conditions);
    }

    @Override
    public int updateUserProp(String prop, String field, String userName) {
        return this.userDao.updateUserProp(prop, field, userName);
    }

    @Override
    public MongoUser getUserById(String objId) {
        return this.userDao.getUserById(objId);
    }

    @Override
    public int updateUserStatus(ObjectId objid, boolean flag) {
        return this.userDao.updateUserStatus(objid, flag);
    }

    @Override
    public int updateUserProperty(Object prop, String field, ObjectId objId) {
        return this.userDao.updateUserProperty(prop, field, objId);
    }

    @Override
    public MongoUser getUserByBind(String objId) {
        return this.userDao.getUserByBind(objId);
    }

    @Override
    public int updateUserHead(MongoUser mongoUser) {

        return this.userDao.updateUserHead(mongoUser);
    }


    @Override
    public int deleteUser(String objId) {
        return userDao.deleteUser(objId);
    }

    @Override
    public int updateUserInfo(String objId, Map<String, Object> updatemap) {
        Query query = new Query();
        Update update = new Update();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(objId)));
        for (Map.Entry<String, Object> entry : updatemap.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }

        boolean flag = this.userDao.update(query, update);
        return flag ? 1 : 0;
    }


    @Override
    public int deleteUsers(List<MongoUser> mongoUsers) {
        return this.userDao.deleteUsers(mongoUsers);
    }
}
