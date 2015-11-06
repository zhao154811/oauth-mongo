/**
 * @Title: UserInfoDao.java
 * @Package com.enlinkmob.ucenterapi.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-5-4 下午2:09:46
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.UserInfo;
import org.bson.types.ObjectId;

/**
 * @author Zhaowy
 * @ClassName: UserInfoDao
 * @Description: TODO(用户资料dao)
 * @date 2014-5-4 下午2:09:46
 */
public interface UserInfoDao extends BaseDao<UserInfo> {

    /**
     * @param @param userName 用户名
     * @return UserInfo
     * @throws
     * @Title: getUserByName
     * @Description: TODO(根据用户名获取用户资料)
     */
    public ObjectId addUserInfo(UserInfo userInfo);


    /**
     * @param @param objId 用户objid
     * @return UserInfo
     * @throws
     * @Title: getUserById
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public com.enlinkmob.ucenterapi.model.UserInfo getUserById(String objId);
}
