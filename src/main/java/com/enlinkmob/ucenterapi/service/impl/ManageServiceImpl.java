package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.ClientDao;
import com.enlinkmob.ucenterapi.model.Client;
import com.enlinkmob.ucenterapi.service.ManageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhaowy on 15/5/19.
 */
@Service("manageService")
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private Client suclient;


    public Client addClient(Client client) {
        //生成随机key
        String appkey = DigestUtils.md5Hex(UUID.randomUUID().toString());
        client.setClientKey(appkey);
        return this.clientDao.saveObject(client);
    }

    @Override
    public List<Client> getClients(boolean status) {
        return this.clientDao.getAllObjects(status);
    }

    public Client getClientByClientId(String clientId) {
//		if(clientId.equals("enlink_su_admin")){
//			return suclient;
//		}else{
        return this.clientDao.getClientByQuery(clientId);
//		}
    }

    @Override
    public Client getClientByConditon(Map<String, Object> condition) {
        Criteria criteria = new Criteria();

        for (Map.Entry<String, Object> entry : condition.entrySet()) {
            criteria.andOperator(Criteria.where(entry.getKey()).is(entry.getValue()));
        }
        return this.clientDao.getObjectByCondition(new Query(criteria));
    }

    @Override
    public void updateClient(Client client) {

        clientDao.updateObj(client);
    }

    @Override
    public boolean updateClients(List<Integer> ids, Client client) {
        if (client != null && client.getClientType() != 0) {
            return this.clientDao.updateMulti(new Query(Criteria.where("_id").in(ids)), Update.update("clientType", client.getClientType()));
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteClients(List<Integer> ids) {
        return this.clientDao.updateMulti(new Query(Criteria.where("_id").in(ids)), Update.update("status", 0));
    }
}
