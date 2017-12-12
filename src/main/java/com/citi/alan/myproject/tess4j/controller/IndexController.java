package com.citi.alan.myproject.tess4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value={"","/","index"})
	public String index(){
		return "submit.jsp";
	}
}
