/**
 * @Title: ClientService.java
 * @Package com.enlinkmob.ucenterapi.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-4-24 下午2:16:11
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.service;


import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import com.enlinkmob.ucenterapi.model.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author Zhaowy
 * @ClassName: ClientService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-4-24 下午2:16:11
 */
public interface ClientService {
    public MongoOAuthClientDetails getClient(String clientId, String clientSecret);

    public List<MongoOAuthClientDetails> getClientPage(PageBean<MongoOAuthClientDetails> pageModel,
                                                       Map<String, Object> conditions);
}
