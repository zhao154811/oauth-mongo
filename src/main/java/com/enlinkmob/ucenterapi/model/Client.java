package com.enlinkmob.ucenterapi.model;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhaowy on 15/5/13.
 */
@Document(collection = "client")
public class Client extends BaseEntity {
    private static final long serialVersionUID = 7398504721768796821L;
    @Indexed(unique = true, background = true, direction = IndexDirection.ASCENDING, dropDups = true, name = "clientId")
    private String clientId;
    private String clientKey;
    private int clientType;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }
}
