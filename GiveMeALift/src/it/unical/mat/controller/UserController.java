package it.unical.mat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	 @RequestMapping(value = "/")
	    public String home() {
	        return "index";//Redirect to home.jsp
	    }
	
}
