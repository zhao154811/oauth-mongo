package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.CustomerUserInfo;
import com.enlinkmob.ucenterapi.model.MongoUser;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/12.
 */
public interface CustomerUserDao extends BaseDao<CustomerUserInfo> {
    public CustomerUserInfo addCustomerUserInfo(CustomerUserInfo customerUserInfo);

    public int updateCustomerUserInfo(CustomerUserInfo customerUserInfo);

    public CustomerUserInfo getCustomerUserInfoById(String customerId);

    public int updateCustomerBindUser(CustomerUserInfo customerUserInfo);

    public CustomerUserInfo getCustomerUserInfoByUser(MongoUser user, String sourceApp);

    public CustomerUserInfo getCustomerUserInfoById(String appUniqueId, String sourceApp);

    public CustomerUserInfo getInfoByOpenId(String openId);

    public int cancelSubcribe(String openId);

    public void deleteUsers(List<MongoUser> mongoUsers);
}
