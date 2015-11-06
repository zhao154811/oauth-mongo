/**
 * @Title: UserInfoDaoImpl.java
 * @Package com.enlinkmob.ucenterapi.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author A18ccms A18ccms_gmail_com
 * @date 2014-5-4 下午2:12:15
 * @version V1.0
 */
package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.UserInfoDao;
import com.enlinkmob.ucenterapi.model.UserInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Zhaowy
 * @ClassName: UserInfoDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-5-4 下午2:12:15
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo>
        implements UserInfoDao {


    /**
     * (非 Javadoc)
     * <p>Title: addUserInfo</p>
     * <p>Description:添加用户资料 </p>
     *
     * @param userInfo
     * @return
     */
    @Override
    public ObjectId addUserInfo(UserInfo userInfo) {
        this.saveObject(userInfo);
        return userInfo.get_id();
    }


    @Override
    public UserInfo getUserById(String objId) {
        return this.getObjectByCondition(new Query(Criteria.where("_id").is(new ObjectId(objId))));
    }


}
