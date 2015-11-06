package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.model.SMSConfig;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/24.
 */
public interface SMSService {
    public ObjectId addConfig(SMSConfig smsConfig);

    public void deleteConfig(SMSConfig smsConfig);

    public List<com.enlinkmob.ucenterapi.model.SMSConfig> getSMSConfig(String client_id);
}
