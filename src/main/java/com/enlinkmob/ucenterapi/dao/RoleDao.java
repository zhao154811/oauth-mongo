package com.enlinkmob.ucenterapi.dao;


import com.enlinkmob.ucenterapi.model.Role;
import org.bson.types.ObjectId;

public interface RoleDao extends BaseDao<Role> {
    public ObjectId addRole(Role role);

    Role getRole(String roleName);
}
