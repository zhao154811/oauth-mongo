/**
 * @Title: ResourceDao.java
 * @Package com.enlinkmob.ucenterapi.mongooauth.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-4-21 上午11:46:06
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.Resource;

import java.util.List;

/**
 * @author Zhaowy
 * @ClassName: ResourceDao
 * @Description: resource持久层
 * @date 2014-4-21 上午11:46:06
 */
public interface ResourceDao extends BaseDao<Resource> {
    public void addResource(Resource resource);

    public List<Resource> getResources();

}
