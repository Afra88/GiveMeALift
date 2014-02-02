package it.unical.mat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import it.unical.mat.datamapper.FeedbackMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.SocialNetworkProfile;
import it.unical.mat.domain.User;
import it.unical.mat.domain.UserActivity;

import org.hibernate.Hibernate;
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
			
			
//			//TODO DELETE
//			RegisteredUser u = new RegisteredUser();
//			RegisteredUserMapper rm = new RegisteredUserMapper();
//			u.setName("Aaaa");
//			u.setSurname("B");
//			u.setBirthYear(1985);
//			Address a = new Address();
//			a.setCity("Torino");
//			a.setStreet("Via Nazionale");
//			a.setState("Italia");
//			u.setAddress(a);
//			u.setEmail("ab@mail.it");
//			u.setPassword("aaaaaaaa");
//			u.setGender("M");
//			u.setDescription("ciaociao ciao");
//			u.setMobilePhone("3");
//			u.setPhone("0");
//			PersonalPreference pref = new PersonalPreference();
//			pref.setChatnessLevel(1);
//			pref.setMusic(true);
//			pref.setPetsOnBoard(true);
//			pref.setSmoking(false);
//			u.setPersonalPreference(pref);
//			rm.insert(u);
			
			
			return "userSearchForFeedback";
		}
		return "error";
	}

	@RequestMapping(value="/UserInsertFeedback",method=RequestMethod.POST)
	public String userInsertFeedback(
			@RequestParam("telephone") String telephone, 
			Model model, HttpSession session){
		
		RegisteredUser u=(RegisteredUser) session.getAttribute("user");
		if(telephone.matches("[0-9]+") && !telephone.equals(u.getMobilePhone())){
			System.out.println(telephone);
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser r = rm.findRegisteredUserByTelephone(telephone);
			
			if(r!=null){
				System.out.println("cerca: " + r);
				model.addAttribute("receiver",r);
				model.addAttribute("found", true);
				
				FeedbackMapper fm = new FeedbackMapper();
				List<Feedback> l = fm.findGivenFeedback(u);
				
				// controllo se utente gi� votato
				boolean exist= false;
				for (Feedback f : l) {
					if(f.getReceiver().getId()==r.getId());
						exist = true;	
				} 
				
				model.addAttribute("released", exist);

				List<SocialNetworkProfile> social = r.getListSocialNetworkProfiles();
//				if(social != null){
//						model.addAttribute("social", social);
//				}
				Double avg = fm.computeAvgRating(r.getId());
				System.out.println("AVG = " + avg);
				
				model.addAttribute("avg", avg);
				
				
				//return "userInsertFeedback";
				return "showFoundUserProfile";
			}
		}
		//model.addAttribute("error", "Il numero di telefono indicato non � valido");
		model.addAttribute("found", false);
		
//		return "userSearchForFeedback";
		return "showMsgUserNotFound";
	}
	
	
	@RequestMapping(value="/SubmitFeedback",method=RequestMethod.POST)
	public String submitFeedback(
			@RequestParam("idReceiver") String idReceiver, 
			@RequestParam(value="rating", required=false) String rating,
			@RequestParam(value="feed", required=false) String role,
			@RequestParam(value="feedComment", required=false) String text,
			Model model, HttpSession session){
		
		User u=(User) session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			RegisteredUser sender = (RegisteredUser)u;
			RegisteredUser receiver = rm.findRegisteredUserById(Long.parseLong(idReceiver));
			
			//RegisteredUser newRE = new RegisteredUser();
			
			
			Feedback f = new Feedback();
			f.setNumAlertSegnalation(0);	
//			f.setReceiver(newRE);
			f.setReceiver(receiver);
			f.setSender(sender);
			f.setRating(Integer.parseInt(rating));
		
			if(f.getText() == null || f.getText()=="")	
				f.setText(text);
			
			System.out.println("ruolo:"+role);
//			if(role == "P")
//				newRE.setOnlyPassenger(true);
//			else
//				newRE.setOnlyPassenger(false);
			
			
			FeedbackMapper fm = new FeedbackMapper();
//			List<Feedback> l = fm.findReceivedFeedback(newRE.getId());
			
			if(fm.insert(f)>0){
				System.out.println("Rat:"+f.getRating());
				
//			if(rm.update(receiver, receiver.getId())){
				session.setAttribute("receiver", receiver);
				
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
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser r = rm.findRegisteredUserById(u.getId());
			Long id = r.getId();
			
			session.setAttribute("user", r);
			
			FeedbackMapper fm = new FeedbackMapper();
			List<Feedback> l = fm.findReceivedFeedback(id);
			boolean noFeed= false;		
			
			if(l.size()!=0){
				noFeed = false;
				//model.addAttribute("noFeed", true);
				//return "showReceivedFeedback";
			
				List<RegisteredUser> senders = new ArrayList<RegisteredUser>();
				List<Integer> ratings = new ArrayList<Integer>();
				
				for (Feedback f : l) {
			
					senders.add((RegisteredUser)f.getSender());
					ratings.add(f.getRating());
					
				}
				// prendere direttamente i feedback per scorrerli insieme
				model.addAttribute("senders", senders);
				model.addAttribute("ratings", ratings);
				
	//			model.addAttribute("feedback", l);
			}
			else
				noFeed = true;
			
			Double avg = fm.computeAvgRating(id);
			
			System.out.println("AVG = " + avg);
			
			model.addAttribute("avg", avg);
			
		
			model.addAttribute("noFeed", noFeed);
			
			return "showReceivedFeedback";
			
		}
		
		return "home";
	}
	
	@RequestMapping(value="/ReleasedFeedback")
	public String showGivenFeedback(Model model, HttpSession session){
		
		RegisteredUser u=(RegisteredUser) session.getAttribute("user");
		
		if(u!=null){			
			FeedbackMapper fm = new FeedbackMapper();			
			List<Feedback> l = fm.findGivenFeedback(u);
		
			System.out.println("size"+l.size());
			
			if(l.size()==0){
				model.addAttribute("noFeed", true);
				return "userSearchForFeedback";
			}
			else{
				
				model.addAttribute("noFeed", false);
				List<RegisteredUser> receivers = new ArrayList<RegisteredUser>();
				List<Integer> ratings = new ArrayList<Integer>();
				
				for (Feedback f : l) {
					receivers.add((RegisteredUser)f.getReceiver());
					ratings.add(f.getRating());
				}
				model.addAttribute("feedback", l);
				model.addAttribute("senders", receivers);
				model.addAttribute("ratings", ratings);		
				
				return "showReleasedFeedback";
				
			}
		
		}
		return "home";
	
	}
	
}
