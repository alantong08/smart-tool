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
        return "mobileForm1";
    }
    
    @RequestMapping(value={"registerSuccess"})
    public String registerSuccess(){
        return "registerSuccess";
    }
}
