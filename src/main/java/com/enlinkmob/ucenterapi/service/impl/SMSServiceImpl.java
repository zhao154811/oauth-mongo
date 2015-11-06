package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.SMSDao;
import com.enlinkmob.ucenterapi.model.SMSConfig;
import com.enlinkmob.ucenterapi.service.SMSService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/24.
 */
@Service("smsServiceImpl")
public class SMSServiceImpl implements SMSService {
    @Autowired
    private SMSDao smsDaoImpl;

    @Override
    public ObjectId addConfig(SMSConfig smsConfig) {
        return smsDaoImpl.addConfig(smsConfig);
    }


    @Override
    public void deleteConfig(SMSConfig smsConfig) {
        this.smsDaoImpl.deleteConfig(smsConfig);
    }

    @Override
    public List<SMSConfig> getSMSConfig(String client_id) {
        return smsDaoImpl.getSMSConfig(client_id);
    }
}
