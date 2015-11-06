package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.exception.ResponseException;
import com.enlinkmob.ucenterapi.model.CustomerUserInfo;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.ResultMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * Created by Zhaowy on 2015/2/2.
 */
public interface NewUserService {
    public ResultMessage customerLogin(String clientId, CustomerUserInfo customerUserInfo) throws ResponseException, UnsupportedEncodingException;

    public ResultMessage userRegist(MongoUser user, String client_id, String birth) throws UnsupportedEncodingException, ParseException;

    public ResultMessage login(MongoUser user, String client_id) throws UnsupportedEncodingException;

    public ResultMessage cancelSubcribe(String openId);

    public ResultMessage uploadHeadIcon(String userId, MultipartFile filedatas, String serverPath, String storeChannel) throws Exception;

    public ResultMessage getUserInfo(String objId, String userSign, String client_id);

    public ResultMessage updateUserInfo(String objId, String json, String userSign, String client_id, String birth) throws Exception;

    public ResultMessage updateUserPwd(MongoUser user) throws Exception;

    public ResultMessage checkUserName(MongoUser user);

    public ResultMessage getUserByCustomerId(String appId, String sourceApp);

    public ResultMessage loginById(String userId, String client_id) throws UnsupportedEncodingException;

    public ResultMessage sendVerifyCode(String phoneNum) throws Exception;
}
