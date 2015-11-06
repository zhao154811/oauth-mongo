package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.OauthRefreshTokenDao;
import com.enlinkmob.ucenterapi.model.MongoOauthRefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "oauthRefreshTokenDao")
public class OauthRefreshTokenDaoImpl extends BaseDaoImpl<MongoOauthRefreshToken> implements
        OauthRefreshTokenDao {


    public ObjectId addRefreshToken(MongoOauthRefreshToken refreshToken) {
        this.saveObject(refreshToken);
        return refreshToken.get_id();
    }

    public MongoOauthRefreshToken getRefreshTokenByTokenId(String token_id) {
        return this.getObjectByCondition(new Query(Criteria.where("token_id").is(token_id)));
    }

    public void deleteRefreshTokenByTokenId(String token_id) {
        this.mongoTemplate.remove(new Query(Criteria.where("token_id").is(token_id)), clazz);
    }

}
