/**
 * @Title: ResourceDaoImpl.java
 * @Package com.enlinkmob.ucenterapi.mongooauth.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-4-21 上午11:47:52
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.ResourceDao;
import com.enlinkmob.ucenterapi.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhaowy
 * @ClassName: ResourceDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-4-21 上午11:47:52
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements
        ResourceDao {


    /**
     * (非 Javadoc)
     * <p>Title: addResource</p>
     * <p>Description:添加资源 </p>
     *
     * @param resource
     */
    @Override
    public void addResource(Resource resource) {
        this.saveObject(resource);
    }

    /**
     * (非 Javadoc)
     * <p>Title: getResources</p>
     * <p>Description: </p>
     *
     * @return
     */
    @Override
    public List<Resource> getResources() {
        return this.mongoTemplate.findAll(clazz);
    }

}
