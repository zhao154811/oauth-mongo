package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.MongoOauthRefreshToken;
import org.bson.types.ObjectId;

public interface OauthRefreshTokenDao extends BaseDao<MongoOauthRefreshToken> {
    public ObjectId addRefreshToken(MongoOauthRefreshToken refreshToken);

    public MongoOauthRefreshToken getRefreshTokenByTokenId(String token_id);

    public void deleteRefreshTokenByTokenId(String token_id);
}
