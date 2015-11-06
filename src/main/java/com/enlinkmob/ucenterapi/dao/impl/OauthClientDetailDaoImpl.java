package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.OauthClientDetailDao;
import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "oauthClientDetailDao")
public class OauthClientDetailDaoImpl extends BaseDaoImpl<MongoOAuthClientDetails> implements OauthClientDetailDao {


    public MongoOAuthClientDetails getByClientId(String client_id) {
        return this.getObjectByCondition(new Query(Criteria.where("client_id").is(client_id)));
    }

    public boolean ifexist(String client_id) {
        return this.getByClientId(client_id) != null;
    }

    public ObjectId addClientDetail(MongoOAuthClientDetails mocd) {
        this.saveObject(mocd);
        return mocd.get_id();
    }


    public void deleteClientDetail(String client_id) {
        this.mongoTemplate.remove(new Query(Criteria.where("client_id").is(client_id)), clazz);
    }

    @Override
    public List<MongoOAuthClientDetails> getListByClientId(String clientId) {
        return this.mongoTemplate.find(new Query(Criteria.where("client_id").is(clientId)), clazz);
    }


}
