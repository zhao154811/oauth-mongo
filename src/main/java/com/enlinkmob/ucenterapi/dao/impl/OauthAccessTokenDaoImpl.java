package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.OauthAccessTokenDao;
import com.enlinkmob.ucenterapi.model.MongoOauthAccessToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "oauthAccessTokenDao")
public class OauthAccessTokenDaoImpl extends BaseDaoImpl<MongoOauthAccessToken> implements
        OauthAccessTokenDao {


    public MongoOauthAccessToken getByAuthenticationId(String authentication_id) {
        return this.getObjectByCondition(new Query(Criteria.where("authentication_id").is(authentication_id)));

    }

    public void deleteOauthAccessToken(String token_id) {
        this.mongoTemplate.remove(new Query(Criteria.where("token_id").is(token_id)), clazz);

    }

    public MongoOauthAccessToken getOauthAccessToken(String token_id) {
        return this.getObjectByCondition(new Query(Criteria.where("token_id").is(token_id)));

    }

    public ObjectId addOauthAccessToken(MongoOauthAccessToken token) {
        this.saveObject(token);
        return token.get_id();
    }

    public void deleteOauthAccessTokenByRefreshToken(String refreshToken) {
        this.mongoTemplate.remove(new Query(Criteria.where("refresh_token").is(refreshToken)), clazz);
    }

    public List<MongoOauthAccessToken> getOauthAccessTokens(String client_id) {
        return this.mongoTemplate.find(new Query(Criteria.where("client_id").is(client_id)), clazz);
    }

    public List<MongoOauthAccessToken> getOauthAccessTokenByUsername(String username) {
        return null;
    }

    @Override
    public List<MongoOauthAccessToken> findTokensByClientIdAndUserName(String ClientId, String username) {
        return this.mongoTemplate.find(new Query(Criteria.where("client_id").is(ClientId).and("username").is(username)), clazz);
    }

}
