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
    
    @RequestMapping(value={"weui-register"})
    public String weuiRegister(){
        return "weui-register";
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
    
    @RequestMapping(value={"/user/reportResult"})
    public String reportResult(){ 
        return "/user/reportResult";
    }
    
    @RequestMapping(value={"/user/confirm"})
    public String confirm(){ 
        return "/user/confirm";
    }
    
    @RequestMapping(value={"tabbar"})
    public String tabbar(){ 
        return "tabbar";
    }
    
    @RequestMapping(value={"weuiLogin"})
    public String weuiLogin(){ 
        return "weui-login";
    }
}
