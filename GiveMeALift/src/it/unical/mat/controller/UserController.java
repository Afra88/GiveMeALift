package it.unical.mat.controller;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	 
	 @RequestMapping(value = "/SignUp", method= RequestMethod.POST )
	    public String userLogIn(@RequestParam("email") String email, @RequestParam("psw")String psw,
	    		@RequestParam("name") String name, @RequestParam("surname") String surname,
	    		@RequestParam("gender") String gender, @RequestParam("year") Integer birthYear,
	    		Model model, HttpSession session) {
		 if(email!="" && email!=null && psw!="" && psw!=null && name!="" && name!=null 
				 && gender!="" && gender!=null && surname!=null && surname!="" && birthYear!=null){
		 	RegisteredUser u=new RegisteredUser(email, psw, name, surname,gender,birthYear);
		 	RegisteredUserMapper rm=new RegisteredUserMapper();
		 	if(rm.insert(u)!=0){
		 		session.setAttribute("user", u);
		 		return "userloggedHome";	
		 	}
		 	else{
		 		return "errorRegistration";
		 	}
		 }
		 else{
		 		return "errorRegistration";
		 	}
	}
	 
	 @RequestMapping(value = "/LogIn", method = RequestMethod.POST)
	 public String userSignedUp(@RequestParam("email") String email, @RequestParam("psw")String psw,
			 Model model, HttpSession session){
		RegisteredUserMapper rm=new RegisteredUserMapper();
		if(email!="" && email!=null && psw!="" && psw!=null){
			RegisteredUser u=rm.findUserByEmailAndPassword(email,psw);
			if(u!=null){
				session.setAttribute("user",u);
				return "userloggedHome";	
			}
			else{
		 		return "errorRegistration";
		 	}
	 	}
	 	else{
	 		return "errorRegistration";
	 	}
	 }
	 
	 @RequestMapping(value = "/LogOut", method = RequestMethod.POST)
	 public String userSignedOut(Model model, HttpSession session){
		User u=(User) session.getAttribute("user");
		if(u!=null){
			session.removeAttribute("user");
			return "home";	
		}
		else{
	 		return "errorRegistration";
	 	}
	 }
	
}
