package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.model.Role;
import org.bson.types.ObjectId;

public interface RoleService {
    public ObjectId addRole(Role role);

    Role getRole(String roleName);

}
