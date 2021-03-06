/**
 * @Title: UserInfoServiceImpl.java
 * @Package com.enlinkmob.ucenterapi.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-5-4 下午2:55:58
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.UserInfoDao;
import com.enlinkmob.ucenterapi.model.UserInfo;
import com.enlinkmob.ucenterapi.service.UserInfoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Zhaowy
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-5-4 下午2:55:58
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * (非 Javadoc)
     * <p>Title: addUserInfo</p>
     * <p>Description: 添加用户资料</p>
     *
     * @param userInfo
     * @return
     * @see com.enlinkmob.ucenterapi.service.UserInfoService#addUserInfo(com.enlinkmob.ucenterapi.model.UserInfo)
     */
    @Override
    public ObjectId addUserInfo(UserInfo userInfo) {
        return this.userInfoDao.addUserInfo(userInfo);
    }

    /**
     * (非 Javadoc)
     * <p>Title: updateUserInfo</p>
     * <p>Description: 更新用户资料</p>
     *
     * @param updateMap
     * @param field
     * @param key
     * @return
     * @see com.enlinkmob.ucenterapi.service.UserInfoService#updateUserInfo(java.util.Map, java.lang.String, java.lang.Object)
     */
    @Override
    public int updateUserInfo(Map<String, Object> updateMap, String field,
                              Object key) {
        return this.userInfoDao.commonUpdate(updateMap, field, key);
    }

    @Override
    public UserInfo getUserById(String objId) {
        return this.userInfoDao.getUserById(objId);
    }


}
