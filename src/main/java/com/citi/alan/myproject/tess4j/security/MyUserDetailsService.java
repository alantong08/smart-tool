package com.citi.alan.myproject.tess4j.security;


import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.citi.alan.myproject.tess4j.dao.UserInfoDao;
import com.citi.alan.myproject.tess4j.entity.Role;
import com.citi.alan.myproject.tess4j.entity.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByMobile(mobile);

        if(userInfo == null){
             throw new UsernameNotFoundException("用户名："+ mobile + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        for (Role role: userInfo.getRoles()){
            collection.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(userInfo.getMobile(),userInfo.getPassword(),collection);
    }
}
