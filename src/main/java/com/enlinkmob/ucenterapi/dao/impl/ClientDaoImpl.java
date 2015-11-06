package com.enlinkmob.ucenterapi.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.enlinkmob.ucenterapi.dao.ClientDao;
import com.enlinkmob.ucenterapi.model.Client;
import com.enlinkmob.ucenterapi.util.MySimplePropertyPreFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by zhaowy on 15/5/19.
 */
@Repository("clientDao")
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao {
    @Autowired
    private Client suclient;

    @Override
    public Client getClientByQuery(String clientId) {
        Client cli = null;
        String clientjson = this.redisTemplate.opsForValue().get("AUTH:CLIENT:" + clientId);
        if (StringUtils.isNotEmpty(clientjson)) {
            try {
                cli = JSON.parseObject(clientjson, clazz);
            } catch (Exception e) {
                if (clientId.equals("enlink_su_admin")) {
                    cli = suclient;
                } else {
                    Query q = new Query(Criteria.where("clientId").is(clientId));
                    cli = super.getObjectByCondition(q);
                }
                if (cli != null) {
                    String clijson = JSON.toJSONString(cli, new MySimplePropertyPreFilter(MySimplePropertyPreFilter.JsonFitler.ex, "_id", "id"), SerializerFeature.NotWriteDefaultValue);
                    this.redisTemplate.opsForValue().set("AUTH:CLIENT:" + clientId, clijson);
                }

            }
        } else {
            if (clientId.equals("enlink_su_admin")) {
                cli = suclient;
            } else {
                Query q = new Query(Criteria.where("clientId").is(clientId));
                cli = super.getObjectByCondition(q);
            }
            if (cli != null) {
                String clijson = JSON.toJSONString(cli, new MySimplePropertyPreFilter(MySimplePropertyPreFilter.JsonFitler.ex, "_id", "id"), SerializerFeature.NotWriteDefaultValue);
                this.redisTemplate.opsForValue().set("AUTH:CLIENT:" + clientId, clijson);
            }
        }

        return cli;
    }
}
