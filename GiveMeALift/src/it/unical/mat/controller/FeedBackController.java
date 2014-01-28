package it.unical.mat.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.UserActivity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedBackController {

	@RequestMapping(value="/UserSearchForFeedback")
	public String userSearchForFeedback(Model model, HttpSession session){
		if(session.getAttribute("user")!=null){
			
			RegisteredUser u = new RegisteredUser();
			RegisteredUserMapper rm = new RegisteredUserMapper();
			u.setName("Aaaa");
			u.setSurname("B");
			u.setBirthYear(1985);
			Address a = new Address();
			a.setCity("Torino");
			a.setStreet("Via Nazionale");
			a.setState("Italia");
			u.setAddress(a);
			u.setEmail("ab@mail.it");
			u.setPassword("aaaaaaaa");
			u.setGender("M");
			u.setDescription("ciaociao ciao");
			u.setMobilePhone("3333333333");
			u.setMobilePhone("4444444444");
			u.setPhone("0909090909");
			PersonalPreference pref = new PersonalPreference();
			pref.setChatnessLevel(1);
			pref.setMusic(true);
			pref.setPetsOnBoard(true);
			pref.setSmoking(false);
			u.setPersonalPreference(pref);
			rm.insert(u);
			
			
			return "userSearchForFeedback";
		}
		return "error";
	}

	@RequestMapping(value="/UserInsertFeedback",method=RequestMethod.POST)
	public String userInsertFeedback(
			@RequestParam("telephone") String telephone, 
			Model model, HttpSession session){
		
		if(telephone.matches("[0-9]+")){
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser r = rm.findRegisteredUserByTelephone(telephone);
			System.out.println("cerca: " + r);
			
			if(r!=null){
				model.addAttribute("receiver",r);
				model.addAttribute("found", true);
			
				//return "userInsertFeedback";
				return "showFoundUserProfile";
			}
		}
		//model.addAttribute("error", "Il numero di telefono indicato non è valido");
		model.addAttribute("found", false);
		
//		return "userSearchForFeedback";
		return "showMsgUserNotFound";
	}
}
