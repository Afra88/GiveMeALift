package it.unical.mat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import it.unical.mat.datamapper.FeedbackMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.datamapper.SocialNetworkProfileMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.SocialNetworkProfile;
import it.unical.mat.domain.User;
import it.unical.mat.domain.UserActivity;
import it.unical.mat.service.ParseDate;

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
			return "userSearchForFeedback";
		}
		return "error";
	}

	@RequestMapping(value="/UserInsertFeedback")//,method=RequestMethod.POST
	public String userInsertFeedback(
			@RequestParam("telephone") String telephone, 
			Model model, HttpSession session){
		
		RegisteredUser u=(RegisteredUser) session.getAttribute("user");
		if(telephone.matches("[0-9]+") && !telephone.equals(u.getMobilePhone())){
			System.out.println(telephone);
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser r = rm.findRegisteredUserByTelephone(telephone);
			
			if(r!=null){
				
				model.addAttribute("receiver",r);
				model.addAttribute("found", true);
								
				if(r.getUserActivity()!=null){
					if(r.getUserActivity().getMemberSince()!=null)
						model.addAttribute("memberSince",ParseDate.getItalianFormat(r.getUserActivity().getMemberSince().toString()));
					if(r.getUserActivity().getLastOnline()!=null)
						model.addAttribute("lastOnline",ParseDate.getItalianFormat(r.getUserActivity().getLastOnline().toString()));
				}
				
//				if(r.getListSocialNetworkProfiles()!=null)
//				{
//					SocialNetworkProfileMapper sm = new SocialNetworkProfileMapper();
//					List<SocialNetworkProfile> social = sm.findSocialNetwork(r);
//					
//					for (SocialNetworkProfile s: social) {
//						social.add(s);
//					}
//					
//					model.addAttribute("list", social);
//				}
				
				
				FeedbackMapper fm = new FeedbackMapper();
				List<Feedback> l = fm.findGivenFeedback(u);
				
				// controllo se utente già votato
				boolean exist= false;
				for (Feedback f : l) {
					if(f.getFeedbackReceiver().getId()==r.getId());
						exist = true;	
				} 
				
				model.addAttribute("released", exist);
				
//				List<SocialNetworkProfile> social = r.getListSocialNetworkProfiles();
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
		//model.addAttribute("error", "Il numero di telefono indicato non è valido");
		model.addAttribute("found", false);
		
//		return "userSearchForFeedback";
		return "showMsgUserNotFound";
	}
	
	@RequestMapping(value="/AlertUser",method=RequestMethod.POST)
	public String alertUser(Model model, HttpSession session){
		RegisteredUser u=(RegisteredUser) session.getAttribute("user");
		if(u!=null){
			
			RegisteredUser r =new RegisteredUser();
			
			Integer count = 0 ;
			Integer currentAlert = r.getCountAlert();
			if(currentAlert==null)
				count = 1;
			else
				count++;
			
			r.setCountAlert(count);
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			rm.update(r, u.getId());
			
			if(!r.getCountAlert().equals(currentAlert))
				model.addAttribute("alerted", true);
			else
				model.addAttribute("alerted", false);
//			
//			if(rm.update(r,u.getId()))
//				model.addAttribute("alerted", true);
//			else
//				model.addAttribute("alerted", false);
			
			return "showAlertSignalation";
		
		}
		
		return "error";
	}
	
	
	@RequestMapping(value="/SubmitFeedback",method=RequestMethod.POST)
	public String submitFeedback(
			@RequestParam("idReceiver") String idReceiver, 
			@RequestParam(value="rating", required=false) String rating,
//			@RequestParam(value="feed", required=false) String role,
			@RequestParam(value="feedComment", required=false) String text,
			Model model, HttpSession session){
		
		User u=(User) session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			RegisteredUser sender = (RegisteredUser)u;
			RegisteredUser receiver = rm.findRegisteredUserById(Long.parseLong(idReceiver));
			
			//RegisteredUser newRE = new RegisteredUser();
			
			
			Feedback f = new Feedback();
			f.setFeedbackReceiver(receiver);
			f.setFeedbackSender(sender);
			//f.setRating(Integer.parseInt(rating));
			
			Integer rate = null;
			if(rating==" Pessimo :( ")
				rate = 0;
			if(rating==" Passabile :| ")
				rate = 1;
			if(rating==" Buono :) ")
				rate = 2;
			if(rating==" Ottimo ;) ")
				rate = 3;
			if(rating==" Grande! ;D ")
				rate = 4;
			
			f.setRating(rate);
				
			System.out.println("rating: "+ f.getRating());
			System.out.println("text: "+text);
		
			if(f.getText() == null || f.getText().equals(""))	
				f.setText(text);
			
//			System.out.println("ruolo:"+role);
			
			FeedbackMapper fm = new FeedbackMapper();
//			List<Feedback> l = fm.findReceivedFeedback(newRE.getId());
			
			if(fm.insert(f)>0){
			
				System.out.println("Rat:"+f.getRating());
			
				model.addAttribute("receiver", receiver); 
				model.addAttribute("memberSince",ParseDate.getItalianFormat(receiver.getUserActivity().getMemberSince().toString()));
				model.addAttribute("lastOnline",ParseDate.getItalianFormat(receiver.getUserActivity().getLastOnline().toString()));
			
				return "showReleasedFeedback";
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
			
					senders.add((RegisteredUser)f.getFeedbackSender());
					ratings.add(f.getRating());
					
				}
				
				model.addAttribute("senders", senders);
				model.addAttribute("ratings", ratings);
				model.addAttribute("list", l);
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
		
		User u=(User) session.getAttribute("user");
		
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
					receivers.add((RegisteredUser)f.getFeedbackReceiver());
					ratings.add(f.getRating());
				}
				model.addAttribute("list", l);
				model.addAttribute("receivers", receivers);
				model.addAttribute("ratings", ratings);		
				
				Double average = 0.0;
				List<Double> avgs = new ArrayList<Double>();
				
				for (RegisteredUser ru : receivers) {
					average = fm.computeAvgRating(ru.getId());
					avgs.add(average);
					
				}
				model.addAttribute("avg", avgs);
				return "showReleasedFeedback";
				
			}
		
		}
		return "home";
	
	}
	
}
