/**
 * @Title: ClientServiceImpl.java
 * @Package com.enlinkmob.ucenterapi.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-4-24 下午2:17:23
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.ClientDao;
import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import com.enlinkmob.ucenterapi.model.PageBean;
import com.enlinkmob.ucenterapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Zhaowy
 * @ClassName: ClientServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-4-24 下午2:17:23
 */
@Service("clientDetailService")
@Scope(value = "singleton")
public class ClientServiceImpl implements ClientService {

    /**
     * (非 Javadoc)
     * <p>Title: getClient</p>
     * <p>Description: 获取MongoOAuthClientDetails</p>
     *
     * @param clientId
     * @param clientSecret
     * @return MongoOAuthClientDetails
     * @see com.enlinkmob.ucenterapi.service.ClientService#getClient(java.lang.String, java.lang.String)
     */
    @Autowired
    private ClientDao clientDetailDao;//clientdao注入


    @Override
    public MongoOAuthClientDetails getClient(String clientId,
                                             String clientSecret) {
//		return clientDetailDao.getClient(clientId, clientSecret);
        return null;
    }

    public List<MongoOAuthClientDetails> getClientPage(PageBean<MongoOAuthClientDetails> pageModel,
                                                       Map<String, Object> conditions) {
//		return clientDetailDao.getClientPage(pageModel, conditions);
        return null;
    }

}
