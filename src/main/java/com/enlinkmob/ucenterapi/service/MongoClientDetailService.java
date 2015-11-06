package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.OauthClientDetailDao;
import com.enlinkmob.ucenterapi.model.MongoOAuthClientDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoClientDetailService implements ClientDetailsService,
        ClientRegistrationService {

    private static final Log logger = LogFactory.getLog(MongoClientDetailService.class);
    @Autowired
    private OauthClientDetailDao OauthClientDetailDao;

    private ObjectMapper mapper = new ObjectMapper();

    private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information";

    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from oauth_client_details";

    private static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    private static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_client_details (" + CLIENT_FIELDS
            + ", client_id) values (?,?,?,?,?,?,?,?,?,?)";

    private static final String DEFAULT_UPDATE_STATEMENT = "update oauth_client_details " + "set "
            + CLIENT_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where client_id = ?";

    private static final String DEFAULT_UPDATE_SECRET_STATEMENT = "update oauth_client_details "
            + "set client_secret = ? where client_id = ?";

    private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_client_details where client_id = ?";

//	private RowMapper<ClientDetails> rowMapper = new ClientDetailsRowMapper();

    private String deleteClientDetailsSql = DEFAULT_DELETE_STATEMENT;

    private String findClientDetailsSql = DEFAULT_FIND_STATEMENT;

    private String updateClientDetailsSql = DEFAULT_UPDATE_STATEMENT;

    private String updateClientSecretSql = DEFAULT_UPDATE_SECRET_STATEMENT;

    private String insertClientDetailsSql = DEFAULT_INSERT_STATEMENT;

    private String selectClientDetailsSql = DEFAULT_SELECT_STATEMENT;

    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

//	private final JdbcTemplate jdbcTemplate;
//
//	private JdbcListFactory listFactory;
//
//	public JdbcClientDetailsService(DataSource dataSource) {
//		Assert.notNull(dataSource, "DataSource required");
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.listFactory = new DefaultJdbcListFactory(new NamedParameterJdbcTemplate(jdbcTemplate));
//	}

    /**
     * @param passwordEncoder the password encoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;
        MongoOAuthClientDetails mocd = OauthClientDetailDao.getByClientId(clientId);
        if (mocd != null) {
            clientDetails = this.mongo2CD(mocd);
        } else {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        return clientDetails;
    }

    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        MongoOAuthClientDetails mocd = new MongoOAuthClientDetails();
        Object[] objs = getFields(clientDetails);
        mocd.setClient_secret(com.enlinkmob.ucenterapi.util.StringUtils.hash((String) objs[0], "md5"));
        mocd.setResource_ids((String) objs[1]);
        mocd.setScope((String) objs[2]);
        mocd.setAuthorized_grant_types((String) objs[3]);
        mocd.setWeb_server_redirect_uri((String) objs[4]);
        mocd.setAuthorities((String) objs[5]);
        mocd.setAccess_token_validity((Integer) objs[6]);
        mocd.setRefresh_token_validity((Integer) objs[7]);
        mocd.setAdditional_information((String) objs[8]);
        mocd.setClient_id((String) objs[9]);
        boolean insertflag = OauthClientDetailDao.ifexist(mocd.getClient_id());
        if (!insertflag) {
            OauthClientDetailDao.addClientDetail(mocd);
        } else {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), new RuntimeException());
        }
    }

    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
//		resource_ids, scope, "
//				+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
//				+ "refresh_token_validity, additional_information
        Object[] objs = getFieldsForUpdate(clientDetails);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("resource_ids", objs[0]);
        map.put("scope", objs[1]);
        map.put("authorized_grant_types", objs[2]);
        map.put("web_server_redirect_uri", objs[3]);
        map.put("authorities", objs[4]);
        map.put("access_token_validity", objs[5]);
        map.put("refresh_token_validity", objs[6]);
        map.put("additional_information", objs[7]);
        int count = OauthClientDetailDao.commonUpdate(map, "client_id", clientDetails.getClientId());
        if (count != 1) {
            throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }
    }

    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        Map<String, Object> updatemap = new HashMap<String, Object>();
        updatemap.put("client_secret", com.enlinkmob.ucenterapi.util.StringUtils.hash(secret, "md5"));
        int count = OauthClientDetailDao.commonUpdate(updatemap, "client_id", clientId);
        if (count != 1) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    public void removeClientDetails(String clientId) throws NoSuchClientException {
        boolean insertflag = OauthClientDetailDao.ifexist(clientId);
        if (insertflag) {
            OauthClientDetailDao.deleteClientDetail(clientId);
        } else {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    public List<ClientDetails> listClientDetails() {
//		client_id,client_secret,resource_ids, scope, "
//				+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
//				+ "refresh_token_validity, additional_information
        List<ClientDetails> cdlist = new ArrayList<ClientDetails>();
        List<MongoOAuthClientDetails> mocdlist = OauthClientDetailDao.getListByClientId("client_id");
        if (mocdlist != null && mocdlist.size() != 0) {
            for (MongoOAuthClientDetails mongoOAuthClientDetails : mocdlist) {
                cdlist.add(this.mongo2CD(mongoOAuthClientDetails));
            }
        }
        return cdlist;
    }

    private Object[] getFields(ClientDetails clientDetails) {
        Object[] fieldsForUpdate = getFieldsForUpdate(clientDetails);
        Object[] fields = new Object[fieldsForUpdate.length + 1];
        System.arraycopy(fieldsForUpdate, 0, fields, 1, fieldsForUpdate.length);
        fields[0] = clientDetails.getClientSecret() != null ? com.enlinkmob.ucenterapi.util.StringUtils.hash(clientDetails.getClientSecret(), "md5")
                : null;
        return fields;
    }

    private Object[] getFieldsForUpdate(ClientDetails clientDetails) {
        String json = null;
        try {
            json = mapper.writeValueAsString(clientDetails.getAdditionalInformation());
        } catch (Exception e) {
            logger.warn("Could not serialize additional information: " + clientDetails, e);
        }
        return new Object[]{
                clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                        .getResourceIds()) : null,
                clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                        .getScope()) : null,
                clientDetails.getAuthorizedGrantTypes() != null ? StringUtils
                        .collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null,
                clientDetails.getRegisteredRedirectUri() != null ? StringUtils
                        .collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null,
                clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                        .getAuthorities()) : null, clientDetails.getAccessTokenValiditySeconds(),
                clientDetails.getRefreshTokenValiditySeconds(), json, clientDetails.getClientId()};
    }

    public void setSelectClientDetailsSql(String selectClientDetailsSql) {
        this.selectClientDetailsSql = selectClientDetailsSql;
    }

    public void setDeleteClientDetailsSql(String deleteClientDetailsSql) {
        this.deleteClientDetailsSql = deleteClientDetailsSql;
    }

    public void setUpdateClientDetailsSql(String updateClientDetailsSql) {
        this.updateClientDetailsSql = updateClientDetailsSql;
    }

    public void setUpdateClientSecretSql(String updateClientSecretSql) {
        this.updateClientSecretSql = updateClientSecretSql;
    }

    public void setInsertClientDetailsSql(String insertClientDetailsSql) {
        this.insertClientDetailsSql = insertClientDetailsSql;
    }

    public void setFindClientDetailsSql(String findClientDetailsSql) {
        this.findClientDetailsSql = findClientDetailsSql;
    }

//	/**
//	 * @param listFactory the list factory to set
//	 */
//	public void setListFactory(JdbcListFactory listFactory) {
//		this.listFactory = listFactory;
//	}
//
//	/**
//	 * @param rowMapper the rowMapper to set
//	 */
//	public void setRowMapper(RowMapper<ClientDetails> rowMapper) {
//		this.rowMapper = rowMapper;
//	}

    /**
     * Row mapper for ClientDetails.
     *
     * @author Dave Syer
     */

    public ClientDetails mongo2CD(MongoOAuthClientDetails mocd) {
        BaseClientDetails details = null;
        if (mocd != null) {
            details = new BaseClientDetails(mocd.getClient_id(), mocd.getResource_ids(), mocd.getScope(),
                    mocd.getAuthorized_grant_types(), mocd.getAuthorities(), mocd.getWeb_server_redirect_uri());
            details.setClientSecret(mocd.getClient_secret());
            if (mocd.getAccess_token_validity() != null) {
                details.setAccessTokenValiditySeconds(mocd.getAccess_token_validity());
            }
            if (mocd.getRefresh_token_validity() != null) {
                details.setRefreshTokenValiditySeconds(mocd.getRefresh_token_validity());
            }
            String json = mocd.getAdditional_information();
            if (json != null) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> additionalInformation = mapper.readValue(json, Map.class);
                    details.setAdditionalInformation(additionalInformation);
                } catch (Exception e) {
                    logger.warn("Could not decode JSON for additional information: " + details, e);
                }
            }
        }
        return details;
    }

    private static class ClientDetailsRowMapper implements RowMapper<ClientDetails> {
        private ObjectMapper mapper = new ObjectMapper();

        public ClientDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseClientDetails details = new BaseClientDetails(rs.getString(1), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(7), rs.getString(6));
            details.setClientSecret(rs.getString(2));
            if (rs.getObject(8) != null) {
                details.setAccessTokenValiditySeconds(rs.getInt(8));
            }
            if (rs.getObject(9) != null) {
                details.setRefreshTokenValiditySeconds(rs.getInt(9));
            }
            String json = rs.getString(10);
            if (json != null) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> additionalInformation = mapper.readValue(json, Map.class);
                    details.setAdditionalInformation(additionalInformation);
                } catch (Exception e) {
                    logger.warn("Could not decode JSON for additional information: " + details, e);
                }
            }
            return details;
        }
    }
}
