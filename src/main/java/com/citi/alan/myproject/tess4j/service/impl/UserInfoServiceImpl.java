package com.citi.alan.myproject.tess4j.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.alan.myproject.tess4j.dao.UserInfoDao;
import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.model.UserLoginDetail;
import com.citi.alan.myproject.tess4j.service.api.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public boolean creatUserAccount(UserLoginDetail userLoginDetail) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public UserLoginDetail login(String mobile, String password) {
        UserInfo userInfo = userInfoDao.findByMobileAndPassword(mobile, password);
        UserLoginDetail userLoginDetail = new UserLoginDetail();
        if(userInfo!=null)
            BeanUtils.copyProperties(userInfo, userLoginDetail);
        return userLoginDetail;
    }

    @Override
    public UserLoginDetail register(UserInfo data) {
        UserInfo userInfo = userInfoDao.findByMobile(data.getMobile());
        UserLoginDetail userLoginDetail = new UserLoginDetail();
        if(userInfo!=null){
            userLoginDetail.setRegistered(true);
        }else{
            userInfoDao.save(data);
        }
        return userLoginDetail;
    }

}
