package com.enlinkmob.ucenterapi.service.impl;

import com.enlinkmob.ucenterapi.dao.RoleDao;
import com.enlinkmob.ucenterapi.model.Role;
import com.enlinkmob.ucenterapi.service.RoleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public ObjectId addRole(Role role) {
        return this.roleDao.addRole(role);
    }

    public Role getRole(String roleName) {
        return this.roleDao.getRole(roleName);
    }

}
