package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.UserDao;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.PageBean;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl<MongoUser> implements UserDao {


    public ObjectId addUser(MongoUser user) {
        this.saveObject(user);
//		this.getRedisTemplate().opsForHash().put("UC_USER", user.getUserName(), JSON.toJSONString(user));
        return user.get_id();
    }

    public MongoUser getUser(final String username) {
        MongoUser user = null;
//		String userString =(String) this.getRedisTemplate().opsForHash().get("UC_USER", username);
        if (username != null) {
            user = this.getObjectByCondition(new Query(Criteria.where("userName").is(username)));
        }
//			String s=JSON.toJSONString(user);
//			this.getRedisTemplate().opsForHash().put("UC_USER", username, s);
        return user;
    }


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
//		this.getPageBean()
//		Query<MongoUser> query=this.createQuery();
//		for (Entry<String,Object> condition : conditions.entrySet()) {
//			query.criteria(condition.getKey()).equal(condition.getValue());
//
//		}
//		this.createQuery();
        return null;
    }

    @Override
    public MongoUser getUserByLoginName(String loginName) {
        return this.getObjectByCondition(new Query(Criteria.where("userName").is(loginName)));
    }

    @Override
    public int updateUserProp(String prop, String field, String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        Update opts = new Update();
        opts.set(field, prop);
        WriteResult wr = this.mongoTemplate.updateFirst(query, opts, clazz);
        return wr.getN();
    }

    @Override
    public MongoUser getUserById(String objId) {
        return this.getObjectByCondition(new Query(Criteria.where("_id").is(new ObjectId(objId))));
    }

    @Override
    public int updateUserStatus(ObjectId objid, boolean flag) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objid));
        Update opts = new Update();
        opts.set("status", flag);
        WriteResult wr = this.mongoTemplate.updateFirst(query, opts, clazz);
        return wr.getN();
    }

    @Override
    public int updateUserProperty(Object prop, String field, ObjectId objId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objId));
        Update opts = new Update();
        opts.set(field, prop);
        opts.set("modifyDate", new Date());
        WriteResult wr = this.mongoTemplate.updateFirst(query, opts, clazz);
        return wr.getN();
    }


    @Override
    public MongoUser getUserByBind(String objId) {
        return this.getObjectByCondition(new Query(Criteria.where("bindAccounts").elemMatch(new Criteria().in(objId))));
    }

    @Override
    public int updateUserHead(MongoUser mongoUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoUser.get_id()));
        Update opts = new Update();
        opts.set("headIcon", mongoUser.getHeadIcon());
        opts.set("modifyDate", new Date());
        WriteResult wr = this.mongoTemplate.updateFirst(query, opts, clazz);
        return wr.getN();
    }


    @Override
    public int deleteUser(String objId) {
        WriteResult wr = this.mongoTemplate.remove(new Query(Criteria.where("_id").is(new ObjectId(objId))), clazz);
        return wr.getN();
    }


    @Override
    public int deleteUsers(List<MongoUser> mongoUsers) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user").in(mongoUsers));
        WriteResult wr = this.mongoTemplate.remove(query, clazz);
        return wr.getN();
    }
}
