package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.MongoOauthAccessToken;
import org.bson.types.ObjectId;

import java.util.List;

public interface OauthAccessTokenDao extends BaseDao<MongoOauthAccessToken> {
    public MongoOauthAccessToken getByAuthenticationId(String authentication_id);

    public void deleteOauthAccessToken(String token_id);

    public MongoOauthAccessToken getOauthAccessToken(String token_id);

    public ObjectId addOauthAccessToken(MongoOauthAccessToken token);

    void deleteOauthAccessTokenByRefreshToken(String refreshToken);

    public List<MongoOauthAccessToken> getOauthAccessTokens(String client_id);

    public List<MongoOauthAccessToken> getOauthAccessTokenByUsername(String username);

    public List<MongoOauthAccessToken> findTokensByClientIdAndUserName(String ClientId, String username);

}
