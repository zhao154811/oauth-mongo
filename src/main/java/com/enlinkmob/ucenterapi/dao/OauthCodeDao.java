package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.MongoOauthCode;
import org.bson.types.ObjectId;

public interface OauthCodeDao extends BaseDao<MongoOauthCode> {
    public ObjectId addCode(MongoOauthCode code);

    public MongoOauthCode getByCode(String code);

    public void deletesByCode(String code);
}
