package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.SMSConfig;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/23.
 */
public interface SMSDao extends BaseDao<SMSConfig> {
    public ObjectId addConfig(SMSConfig smsConfig);

    public void deleteConfig(SMSConfig smsConfig);

    public List<SMSConfig> getSMSConfig(String client_id);
}
