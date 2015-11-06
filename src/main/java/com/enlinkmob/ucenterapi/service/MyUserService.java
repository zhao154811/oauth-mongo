package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.UserDao;
import com.enlinkmob.ucenterapi.model.MongoUser;
import com.enlinkmob.ucenterapi.model.OauthUserDetails;
import com.enlinkmob.ucenterapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("myUserService")
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserDao userdao;


    public OauthUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        MongoUser user = userdao.getUser(username);
        OauthUserDetails userdetail = new OauthUserDetails(user == null ? new MongoUser() : user);
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        if (user != null && user.getAuthorities() != null) {
            for (Role role : user.getAuthorities()) {
                list.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        userdetail.setAuthorities(list);

        return userdetail;
    }


}
