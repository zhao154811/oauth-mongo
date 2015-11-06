package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.Client;

/**
 * Created by zhaowy on 15/5/19.
 */
public interface ClientDao extends BaseDao<Client> {
    public Client getClientByQuery(String clientId);
}
