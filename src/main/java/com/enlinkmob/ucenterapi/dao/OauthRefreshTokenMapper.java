package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.OauthRefreshToken;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository(value = "oauthRefreshTokenMapper")
public interface OauthRefreshTokenMapper {
    public ObjectId addRefreshToken(OauthRefreshToken refreshToken);

    public OauthRefreshToken getRefreshTokenByTokenId(String token_id);

    public void deleteRefreshTokenByTokenId(String token_id);
}
