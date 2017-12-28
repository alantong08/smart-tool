package com.citi.alan.myproject.tess4j.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.model.UserLoginDetail;
import com.citi.alan.myproject.tess4j.service.api.UserInfoService;
@RequestMapping("/loginRegister")
@RestController
public class UserLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserLoginDetail login(@RequestParam("mobile") String mobile,@RequestParam("password") String password, HttpServletRequest request ) {
        
        request.getSession().setAttribute("mobile", mobile);
        String viewName = "";
        UserLoginDetail userLoginDetail = null;  
        try { 
            userLoginDetail = userInfoService.login(mobile, password);
            if (userLoginDetail.getMobile() != null) {               
                viewName = "/tabbar";
            }else{
            	userLoginDetail.setMessage("failed");
                viewName = "/login";
            }
           
            userLoginDetail.setView(viewName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLoginDetail;
    }

    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserLoginDetail registerPage(@RequestParam Map<String, String> data, HttpServletRequest request) {
       
        String viweName = null;
        UserLoginDetail userLoginDetail = null;
        try {
            UserInfo userInfo = new UserInfo();
            BeanUtils.populate(userInfo, data);
            userLoginDetail = userInfoService.register(userInfo);
            if (userLoginDetail.isRegistered()) {
                viweName = "/register";
            }else{
                request.getSession().setAttribute("mobile", userInfo.getMobile());
                viweName = "/user/uploadOrderChart";
            }
            userLoginDetail.setView(viweName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLoginDetail;
    }

}
