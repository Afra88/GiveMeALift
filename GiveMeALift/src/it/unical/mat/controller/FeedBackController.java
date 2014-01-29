package it.unical.mat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
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
			
			
			//TODO DELETE
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
			u.setMobilePhone("3333333333");
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
		
		User u=(User) session.getAttribute("user");
		
		if(telephone.matches("[0-9]+") && telephone!=u.getMobilePhone()){
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser r = rm.findRegisteredUserByTelephone(telephone);
			
			if(r!=null){
				System.out.println("cerca: " + r);
				model.addAttribute("receiver",r);
				model.addAttribute("found", true);
				
				boolean exist= false;
				for (Feedback f : r.getReceivedFeedback()) {
					if(f.getSender().getId()==u.getId());
						exist = true;	
				} 
				
				model.addAttribute("released", exist);
				
				//return "userInsertFeedback";
				return "showFoundUserProfile";
			}
		}
		//model.addAttribute("error", "Il numero di telefono indicato non è valido");
		model.addAttribute("found", false);
		
//		return "userSearchForFeedback";
		return "showMsgUserNotFound";
	}
	
	
	@RequestMapping(value="/SubmitFeedback",method=RequestMethod.POST)
	public String submitFeedback(
			@RequestParam("idReceiver") String idReceiver, 
			@RequestParam("rating") String rating,
			Model model, HttpSession session){
		
		User u=(User) session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			RegisteredUser sender = (RegisteredUser)u;
			RegisteredUser receiver = rm.findRegisteredUserById(Long.parseLong(idReceiver));
			
			RegisteredUser newRE = new RegisteredUser();
			
			Feedback f = new Feedback();
			f.setReceiver(newRE);
			f.setSender(sender);
			f.setRating(Integer.parseInt(rating));
			
			List<Feedback> l = newRE.getReceivedFeedback();
			l.add(f);
			
			newRE.setReceivedFeedback(l);
	
			if(rm.update(receiver, receiver.getId())){
				
			//	model.addAttribute("updated", true);
				return "showFoundUserProfile";
			}
			
			//model.addAttribute("updated", false);
			return "error";
		}
		
		return "home";
	}
	
	@RequestMapping(value="/ReceivedFeedback")
	public String showMyReceivedFeedback(Model model, HttpSession session){
		
		User u = (User) session.getAttribute("user");
		
		if(u!=null){
			
			RegisteredUser r = (RegisteredUser) u;
			model.addAttribute("user", r);
			
			List<Feedback> l = r.getReceivedFeedback(); 
			List<RegisteredUser> senders = new ArrayList<RegisteredUser>();
			List<Integer> ratings = new ArrayList<Integer>();
			
			for (Feedback f : l) {
		
				senders.add((RegisteredUser)f.getSender());
				ratings.add(f.getRating());
				
			}
			// prendere direttamente i feedback per scorrerli insieme
			model.addAttribute("senders", senders);
			model.addAttribute("ratings", ratings);
				
			
			
			return "showReceivedFeedback";
			
		}
		
		return "home";
	}
	
	@RequestMapping(value="/ReleasedFeedback")
	public String showReceivedFeedback(Model model, HttpSession session){
		
		User u = (User) session.getAttribute("user");
		
		if(u!=null){
			
			RegisteredUser r = (RegisteredUser) u;
			model.addAttribute("user", r);
			
			List<Feedback> l = r.getReceivedFeedback(); 
			List<RegisteredUser> senders = new ArrayList<RegisteredUser>();
			List<Integer> ratings = new ArrayList<Integer>();
			
			for (Feedback f : l) {
				if(f.getReceiver() == r){
					senders.add((RegisteredUser)f.getSender());
					ratings.add(f.getRating());
				}
			}
			
			model.addAttribute("senders", senders);
			model.addAttribute("ratings", ratings);		
			
			return "showReceivedFeedback";
			
		}
		
		return "home";
	}
	
	
	
}
