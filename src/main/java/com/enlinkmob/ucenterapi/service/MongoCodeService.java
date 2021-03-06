package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.OauthCodeDao;
import com.enlinkmob.ucenterapi.model.MongoOauthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

@Service(value = "myCodeService")
public class MongoCodeService extends RandomValueAuthorizationCodeServices {
    private static final String DEFAULT_SELECT_STATEMENT = "select code, authentication from oauth_code where code = ?";
    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_code (code, authentication) values (?, ?)";
    private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_code where code = ?";

    private String selectAuthenticationSql = DEFAULT_SELECT_STATEMENT;
    private String insertAuthenticationSql = DEFAULT_INSERT_STATEMENT;
    private String deleteAuthenticationSql = DEFAULT_DELETE_STATEMENT;
    @Autowired
    private OauthCodeDao oauthCodeDao;


    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        MongoOauthCode mongocode = new MongoOauthCode();
        mongocode.setCode(code);
        mongocode.setAuthentication(SerializationUtils.serialize(authentication));
        oauthCodeDao.addCode(mongocode);
    }

    public OAuth2Authentication remove(String code) {
        OAuth2Authentication authentication = null;
//		select code, authentication from oauth_code where code = ?
        MongoOauthCode mongocode = oauthCodeDao.getByCode(code);
        if (mongocode != null) {
            authentication = SerializationUtils.deserialize(mongocode.getAuthentication());
        }
        if (authentication != null) {
//			delete from oauth_code where code = ?
            oauthCodeDao.deletesByCode(code);
        }

        return authentication;
    }

    public void setSelectAuthenticationSql(String selectAuthenticationSql) {
        this.selectAuthenticationSql = selectAuthenticationSql;
    }

    public void setInsertAuthenticationSql(String insertAuthenticationSql) {
        this.insertAuthenticationSql = insertAuthenticationSql;
    }

    public void setDeleteAuthenticationSql(String deleteAuthenticationSql) {
        this.deleteAuthenticationSql = deleteAuthenticationSql;
    }

}
