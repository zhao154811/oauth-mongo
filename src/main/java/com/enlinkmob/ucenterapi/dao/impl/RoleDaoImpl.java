package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.RoleDao;
import com.enlinkmob.ucenterapi.model.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {


    public ObjectId addRole(Role role) {
        this.saveObject(role);
        return role.get_id();
    }

    public Role getRole(String roleName) {
//		String roleString=(String) this.getRedisTemplate().opsForHash().get("UC_ROLE", roleName);
        Role role = null;
//		if(roleString==null||roleString.length()==0){
        Query query = new Query();
        query.addCriteria(Criteria.where("roleName").is(roleName));
        role = this.getObjectByCondition(query);
        return role;
    }

}
