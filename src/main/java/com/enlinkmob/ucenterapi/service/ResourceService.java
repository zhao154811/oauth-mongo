/**
 * @Title: ResourceService.java
 * @Package com.enlinkmob.ucenterapi.mongooauth.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-4-21 上午11:53:46
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.ResourceDao;
import com.enlinkmob.ucenterapi.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zhaowy
 * @ClassName: ResourceService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-4-21 上午11:53:46
 */
@Service("resourceService")
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    public void addResource(Resource resource) {
        this.resourceDao.addResource(resource);
    }

    public List<Resource> getResources() {
        return this.resourceDao.getResources();
    }
}
