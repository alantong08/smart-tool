package com.citi.alan.myproject.tess4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @RequestMapping(value={"","/","index"})
    public String index(){
        return "login";
    }
    
    @RequestMapping(value={"register"})
    public String register(){
        return "register";
    }
    
    @RequestMapping(value={"upload"})
    public String uplaod(){
        return "/user/uploadOrderChart";
    }
    
    @RequestMapping(value={"registerSuccess"})
    public String registerSuccess(){
        return "/user/registerSuccess";
    }
    
    @RequestMapping(value={"/user/uploadOrderChart"})
    public String uploadOrderChart(){
        return "/user/uploadOrderChart";
    }
}
