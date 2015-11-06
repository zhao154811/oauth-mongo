package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.OauthClientDetailDao;
import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {

    @Autowired
    private OauthClientDetailDao oauthClientDetailDao;


    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        MongoOAuthClientDetails clientDetails = oauthClientDetailDao.getByClientId(authorizationRequest.getClientId());
        return clientDetails != null;

    }
}
