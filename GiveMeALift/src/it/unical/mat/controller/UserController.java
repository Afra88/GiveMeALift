package it.unical.mat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	 @RequestMapping(value = "/")
	    public String home() {
	        return "home";
	    }
	 
	 @RequestMapping(value = "/userRegistration")
	    public String userRegistration() {
	        return "userRegistration";
	    }
	 
	 @RequestMapping(value = "/signUp")
	    public String userSignUp() {
		 	//TODO
	        return "userSignUp";
	    }
	
}
