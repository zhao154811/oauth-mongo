package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.SMSDao;
import com.enlinkmob.ucenterapi.model.SMSConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/24.
 */
@Repository(value = "smsDaoImpl")
public class SMSDaoImpl extends BaseDaoImpl<SMSConfig> implements SMSDao {

    @Override
    public ObjectId addConfig(SMSConfig smsConfig) {
        this.saveObject(smsConfig);
        return smsConfig.get_id();
    }


    @Override
    public void deleteConfig(SMSConfig smsConfig) {
        this.mongoTemplate.remove(smsConfig);
    }

    @Override
    public List<SMSConfig> getSMSConfig(String client_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("client_id").is(client_id));
        return this.mongoTemplate.find(query, clazz);

    }
}
