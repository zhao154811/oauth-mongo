package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.CustomerUserDao;
import com.enlinkmob.ucenterapi.model.CustomerUserInfo;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.service.CustomerUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhaowy on 2014/6/12.
 */
@Service("customerUserServiceImpl")
@Scope(value = "singleton")
public class CustomerUserServiceImpl implements CustomerUserService {
    @Autowired
    private CustomerUserDao customerUserDaoImpl;

    @Override
    public ObjectId addCustomerUserInfo(CustomerUserInfo customerUserInfo) {
        return (ObjectId) this.customerUserDaoImpl.addCustomerUserInfo(customerUserInfo).get_id();
    }

    @Override
    public int updateCustomerUserInfo(CustomerUserInfo customerUserInfo) {
        return this.customerUserDaoImpl.updateCustomerUserInfo(customerUserInfo);
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoById(String customerId) {
        return (CustomerUserInfo) this.customerUserDaoImpl.getCustomerUserInfoById(customerId);
    }

    @Override
    public int updateCustomerBindUser(CustomerUserInfo customerUserInfo) {
        return this.customerUserDaoImpl.updateCustomerBindUser(customerUserInfo);
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoByUser(MongoUser user, String sourceApp) {
        return (CustomerUserInfo) this.customerUserDaoImpl.getCustomerUserInfoByUser(user, sourceApp);
    }

    @Override
    public CustomerUserInfo getCustomerUserInfoById(String appUniqueId, String sourceApp) {
        return (CustomerUserInfo) this.customerUserDaoImpl.getCustomerUserInfoById(appUniqueId, sourceApp);
    }

    @Override
    public CustomerUserInfo getInfoByOpenId(String openId) {
        return (CustomerUserInfo) this.customerUserDaoImpl.getInfoByOpenId(openId);
    }

    @Override
    public int cancelSubcribe(String openId) {
        return this.customerUserDaoImpl.cancelSubcribe(openId);
    }

    @Override
    public void deleteUsers(List<MongoUser> mongoUsers) {
        this.customerUserDaoImpl.deleteUsers(mongoUsers);

    }
}
