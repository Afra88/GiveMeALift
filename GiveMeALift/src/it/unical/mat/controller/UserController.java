package it.unical.mat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 
//	 @RequestMapping(value = "/signUp")
//	    public String userSignUp() {
//		 	//TODO
//	        return "userSignUp";
//	 }
	 
	 @RequestMapping(value = "/userPage", method = RequestMethod.GET)
	 public String userSignedUp(String name, String surname, String email,
			 String password, String confirmPassword, Model model){
		 return "";
	 }
	 
	
}
