package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import org.bson.types.ObjectId;

import java.util.List;


public interface OauthClientDetailDao extends BaseDao<MongoOAuthClientDetails> {
    public MongoOAuthClientDetails getByClientId(String client_id);

    public boolean ifexist(String client_id);

    public ObjectId addClientDetail(MongoOAuthClientDetails mocd);

    public void deleteClientDetail(String client_id);

    public List<MongoOAuthClientDetails> getListByClientId(String clientId);
}
