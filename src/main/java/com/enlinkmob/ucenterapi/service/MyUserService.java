package com.enlinkmob.ucenterapi.service;

import com.enlinkmob.ucenterapi.dao.UserMapper;
import com.enlinkmob.ucenterapi.model.Authority;
import com.enlinkmob.ucenterapi.model.OauthUserDetails;
import com.enlinkmob.ucenterapi.model.User;
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
    private UserMapper userMapper;


    public OauthUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userMapper.selectByName(username);
        OauthUserDetails userdetail = new OauthUserDetails(user == null ? new User() : user);
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        if (user != null && user.getAuthorities() != null) {
            for (Authority role : user.getAuthorities()) {
                list.add(new SimpleGrantedAuthority(role.getAuthorityName()));
            }
        }
        userdetail.setAuthorities(list);

        return userdetail;
    }


}
