package it.unical.mat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.AdministratorMapper;
import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Administrator;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
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
	    public String userSignUp(@RequestParam("email") String email, @RequestParam("psw")String psw,
	    		@RequestParam("name") String name, @RequestParam("surname") String surname,
	    		@RequestParam("gender") String gender, @RequestParam("year") Integer birthYear,
	    		Model model, HttpSession session) {
				if(email!=null && !email.equals("") && psw!=null  && !psw.equals("") && name!=null && !name.equals("")
						&& gender!=null && !gender.equals("") && surname!=null && !surname.equals("") && birthYear!=null){
				 	RegisteredUser u=new RegisteredUser(email, psw, name, surname,gender,birthYear);
				 	RegisteredUserMapper rm=new RegisteredUserMapper();
				 	if(rm.insert(u)!=0){
				 		session.setAttribute("user", u);
				 		return "home";	
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
	 public String userLogIn(@RequestParam("email") String email, @RequestParam("psw")String psw,
			 Model model, HttpSession session){
		RegisteredUserMapper rm=new RegisteredUserMapper();
		if(email!=null && !email.equals("") && !psw.equals("") && psw!=null && !psw.equals("")){
			RegisteredUser u=rm.findUserByEmailAndPassword(email,psw);
			if(u!=null){
				session.setAttribute("user",u);
				return "home";	
			}
			else{
				AdministratorMapper am=new AdministratorMapper();
//				Administrator user=new Administrator();
//				user.setName("Admin");
//				user.setSurname("Admin");
//				user.setEmail("admin@a.aa");
//				user.setGender("M");
//				user.setBirthYear(1975); 
//				user.setPhone("0984000001");
//				user.setPassword("aa");
////				Address ad = new Address("Viale Mancini", "Cosenza", "Italia");
////				user.setAddress(ad);
//				am.insert(user);
				System.out.println("Admin");
				Administrator a=(Administrator) am.findAdministratorByEmailAndPassword(email, psw);
				System.out.println(a);
				if(a!=null){
					session.setAttribute("admin",a);
					return "home";	
				}
		 	}
	 	}
	 	return "errorRegistration";
	 }
	 
	 @RequestMapping(value = "/LogOut")
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
	 
	 @RequestMapping(value="/ShowUserOfferedLift")
	 private String retriveUserOfferedLift(@RequestParam(value="page",required=false) String nPage, Model model, HttpSession session){
		 User u=(User) session.getAttribute("user");
			if(u!=null){
				if(nPage==null || nPage.equals(""))
					nPage="1";				
				LiftMapper lm=new LiftMapper();
				List<Lift> result=lm.findLiftOfferedByUser(u.getId());
				if(result.size()>0){
					PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(result);		
					pageHolder.setPageSize(12);	
					pageHolder.setPage(Integer.parseInt(nPage));
					model.addAttribute("pages", pageHolder.getPageCount());
					model.addAttribute("page",pageHolder.getPage());
					model.addAttribute("pageHolder",pageHolder);
					//TODO ora e data in formato italiano
				}
				else
					model.addAttribute("noRes",true);
				return "userOfferedLift";	
			}
			else{
		 		return "errorRegistration";
		 	}
	 }
	
	 @RequestMapping(value="/ShowUserBookedLift")
	 private String retriveUserBookedLift(@RequestParam(value="page",required=false) String nPage, Model model, HttpSession session){
		 User u=(User) session.getAttribute("user");
			if(u!=null){
				if(nPage==null || nPage.equals(""))
					nPage="1";				
				LiftMapper lm=new LiftMapper();
				List<Lift> result=lm.findLiftBookedByUser(u.getId());
				if(result.size()>0){
					PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(result);		
					pageHolder.setPageSize(12);	
					pageHolder.setPage(Integer.parseInt(nPage));
					model.addAttribute("pages", pageHolder.getPageCount());
					model.addAttribute("page",pageHolder.getPage());
					model.addAttribute("pageHolder",pageHolder);
					model.addAttribute("noRes",false);
				}
				else
					model.addAttribute("noRes",true);
				return "userBookedLift";	
			}
			else{
		 		return "errorRegistration";
		 	}
	 }
	 
	 @RequestMapping(value="/ShowLiftOfferedDetails")
	 private String retriveAOfferedLift(@RequestParam String lift, Model model, HttpSession session){
		 User u=(User) session.getAttribute("user");
			if(u!=null){			
				LiftMapper lm=new LiftMapper();
				Lift result=lm.findById(lift);
				if(result!=null){
					model.addAttribute("lift",result);
					model.addAttribute("noRes",false);
				}
				else
					model.addAttribute("noRes",true);
				return "userOfferedLiftDetails";	
			}
			else{
		 		return "errorRegistration";
		 	}
	 }
	 
	 @RequestMapping(value="/DeleteLiftOffered")
	 private String deleteLiftOffered(@RequestParam String lift, Model model, HttpSession session){
		 User u=(User) session.getAttribute("user");
			if(u!=null){			
				LiftMapper lm=new LiftMapper();
				Lift l=lm.findById(lift);
				if(l.getIsReturn()){
					Lift lgoing=lm.findLiftByLiftReturn(Long.parseLong(lift));
//					Lift ll=new Lift();
//	TODO				System.out.println(lgoing.getReturnLift());
//	riprovare così-->			ll.setReturnLift(null); 
//					lm.update(ll, lgoing.getId());
//					if(ll.getReturnLift()!=null)
//						System.out.println(lgoing.getReturnLift());
//					else
//						System.out.println("è nullo");
					boolean deleted=lm.deleteReturnLift(lgoing, l); 
					if(deleted){
						model.addAttribute("error",false);
						return "userDeleteLiftOffered";	
					}
					else
						model.addAttribute("error",true);
				}
				else{
					boolean result=lm.delete(Long.parseLong(lift),Lift.class);
					if(result){
						model.addAttribute("error",false);
					}
					else
						model.addAttribute("error",true);
				}
			}
			return "userDeleteLiftOffered";	
//	 		return "errorRegistration";
	 }

}
