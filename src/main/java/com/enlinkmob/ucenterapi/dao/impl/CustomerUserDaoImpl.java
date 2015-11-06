package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.CustomerUserDao;
import com.enlinkmob.ucenterapi.model.CustomerUserInfo;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhaowy on 2014/6/12.
 */
@Repository("customerUserDaoImpl")
public class CustomerUserDaoImpl extends BaseDaoImpl<CustomerUserInfo> implements CustomerUserDao {


    @Override
    public CustomerUserInfo addCustomerUserInfo(CustomerUserInfo customerUserInfo) {
        return this.saveObject(customerUserInfo);
    }

    @Override
    public int updateCustomerUserInfo(CustomerUserInfo customerUserInfo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("appUniqueId").is(customerUserInfo.getAppUniqueId()).and("sourceApp").is(customerUserInfo.getSourceApp()));
        Update up = new Update();
        up.set("infoJson", customerUserInfo.getInfoJson()).set("openIds", customerUserInfo.getOpenIds()).set("modifyDate", new Date());
        WriteResult wr = mongoTemplate.updateFirst(query, up, clazz);
        return wr.getN();
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoById(String customerId) {
        if (customerId != null && customerId.length() != 0) {
            return this.getObjectByCondition(new Query(Criteria.where("appUniqueId").is(customerId)));
        } else {
            return null;
        }
    }


    @Override
    public int updateCustomerBindUser(CustomerUserInfo customerUserInfo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(customerUserInfo.get_id()));
        Update ups = new Update();
        ups.set("user", customerUserInfo.getUser());
        ups.set("modifyDate", new Date());
        WriteResult wr = mongoTemplate.updateFirst(query, ups, clazz);
        return wr.getN();
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoByUser(MongoUser user, String sourceApp) {

        return this.getObjectByCondition(new Query(Criteria.where("user").is(user).and("sourceApp").is(sourceApp)));
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoById(String appUniqueId, String sourceApp) {
        return this.getObjectByCondition(new Query(Criteria.where("appUniqueId").is(appUniqueId).and("sourceApp").is(sourceApp)));
    }

    @Override
    public CustomerUserInfo getInfoByOpenId(String openId) {
        return this.getObjectByCondition(new Query(Criteria.where("openIds." + openId).exists(true)));
    }

    @Override
    public int cancelSubcribe(String openId) {
        Query query = new Query(Criteria.where("openIds." + openId).exists(true));
        Update ups = new Update();
        ups.set("openIds." + openId, "0");
        ups.set("modifyDate", new Date());
        WriteResult wr = mongoTemplate.updateFirst(query, ups, clazz);
        return wr.getN();
    }

    @Override
    public void deleteUsers(List<MongoUser> mongoUsers) {
        this.mongoTemplate.remove(new Query(Criteria.where("user").in(mongoUsers)), clazz);

    }
}
