package com.citi.alan.myproject.tess4j.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.model.UserLoginDetail;
import com.citi.alan.myproject.tess4j.service.api.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("mobile") String mobile,@RequestParam("password") String password ) {
        ModelAndView mView = new ModelAndView("login");
        String viewName = "";
        try {
           
            UserLoginDetail userLoginDetail = userInfoService.login(mobile, password);
            if (userLoginDetail.getMobile() != null) {
                viewName = "/mobileForm1";
            }else{
                userLoginDetail.setRegistered(false);
                userLoginDetail.setMobile(mobile);
                userLoginDetail.setPassword(password);
                viewName = "/login";
            }
           
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userLoginDetail);
            mView.addObject("userLogin", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        mView.setViewName(viewName);
        return mView;
    }

    
    @RequestMapping(value = "/registerPage", method = RequestMethod.POST)
    public UserLoginDetail registerPage(@RequestParam Map<String, String> data) {
        
        String viweName = null;
        UserLoginDetail userLoginDetail = null;
        try {
            UserInfo userInfo = new UserInfo();
            BeanUtils.populate(userInfo, data);
            userLoginDetail = userInfoService.register(userInfo);
            if (userLoginDetail.isRegistered()) {
                viweName = "/register";
            }else{
                viweName = "/mobileForm1";
            }
            userLoginDetail.setView(viweName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLoginDetail;
    }

}
