package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.OauthCodeDao;
import com.enlinkmob.ucenterapi.model.MongoOauthCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("oauthCodeDao")
public class OauthCodeDaoImpl extends BaseDaoImpl<MongoOauthCode> implements OauthCodeDao {


    public ObjectId addCode(MongoOauthCode code) {
        this.saveObject(code);
        return code.get_id();
    }

    public MongoOauthCode getByCode(String code) {
        return this.getObjectByCondition(new Query(Criteria.where("code").is(code)));
    }

    public void deletesByCode(String code) {
        this.mongoTemplate.remove(new Query(Criteria.where("code").is(code)), clazz);
    }

}
