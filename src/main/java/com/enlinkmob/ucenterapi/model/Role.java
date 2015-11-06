package com.enlinkmob.ucenterapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role extends BaseEntity {


    private static final long serialVersionUID = -4396904174903311013L;
    //	@Id
//	private ObjectId _id;
    private String roleName;//角色名  (ROLE_**)
    private String roleNameCN;
    private String clientId;//属于哪个应用

    //	public ObjectId get_id() {
//		return _id;
//	}
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRoleNameCN() {
        return roleNameCN;
    }

    public void setRoleNameCN(String roleNameCN) {
        this.roleNameCN = roleNameCN;
    }

    public Role() {
        super();
    }

    public Role(ObjectId _id) {
        super();
        super.set_id(_id);
    }


}
