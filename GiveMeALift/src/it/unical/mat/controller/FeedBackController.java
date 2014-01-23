package it.unical.mat.controller;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.RegisteredUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedBackController {

	@RequestMapping(value="/UserSearchForFeedback")
	public String userSearchForFeedback(Model model, HttpSession session){
		if(session.getAttribute("user")!=null)
			return "userSearchForFeedback";
		return "error";
	}

	@RequestMapping(value="/UserInsertFeedback",method=RequestMethod.POST)
	public String userInsertFeedback(@RequestParam("telephone") String telephone, Model model, HttpSession session){
		if(telephone.matches("[0-9]+")){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			RegisteredUser r=rm.findRegisteredUserByTelephone(telephone);
			if(r!=null){
				model.addAttribute("receiver",r);
				return "UserInsertFeedback";
			}
		}
		model.addAttribute("error", "Il numero di telefono indicato non è valido");
		return "UserSearchForFeedback";
	}
}
