package it.unical.mat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.service.LiftToViewFacade;
import it.unical.mat.service.ParseDate;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

//	@RequestMapping(value="/ResultSearch", method = RequestMethod.GET)
//	public String handleSearch(@RequestParam("mapFrom") String input1, 
//    		@RequestParam("mapTo") String input2,
//    		@RequestParam("date") String input3,
//    		@RequestParam(value="page",required=false) String p,
//    		Model model){
//		
//			
//			LiftMapper lm=new LiftMapper();
//			
//			if(p==null || p==""){
//				p="1";
//			}
//			PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(lm.findLiftByFromAndTo(input1, input2, input3));		
//			pageHolder.setPage(Integer.parseInt(p));
//			pageHolder.setPageSize(10);
//			
//			model.addAttribute("from",input1);
//			model.addAttribute("to",input2);
//			
//			model.addAttribute("pages", pageHolder.getPageCount());
//			model.addAttribute("page",pageHolder.getPage());
//			
//			model.addAttribute("pageHolder",pageHolder);
//			
//			return "resultSearch";			
//	}
	
	@RequestMapping(value="/ResultSearch", method = RequestMethod.GET)
	public String handleAdvancedSearch(@RequestParam("mapFrom") String from, 
    		@RequestParam("mapTo") String to,
    		@RequestParam("date") String date,
    		@RequestParam(value="sort",required=false) String sort,
    		@RequestParam(value="range",required=false) String range,
    		@RequestParam(value="radio",required=false) String radio,
    		@RequestParam(value="page",required=false) String nPage,
    		Model model){
		
		LiftMapper lm=new LiftMapper();

//		Lift l=new Lift();
//		LiftPoint lp=new LiftPoint();
//		lp.setCity(from);
//		LiftPoint lp2=new LiftPoint();
//		lp2.setCity(to);
//		LiftPointMapper lpm=new LiftPointMapper();
//		long idLp=lpm.insert(lp);
//		long idLp2=lpm.insert(lp2);
//		l.setPickUpPoint(lp);
//		l.setDropOffPoint(lp2);
//		l.setCost(2);
//		l.setnSeat(2);
//		l.setPossibleDetour(false);
//		lm.insert(l);
		
		if(nPage==null || nPage==""){
			nPage="1";
		}
		
		if(radio=="" || radio==null){
			radio=null;
		}
		Integer timeFrom=null;
		Integer timeTo=null;
		
		if(range!=null && range!=""){
			String[] rangeValues=range.split(" ");
			timeFrom=Integer.parseInt(rangeValues[1]);
			timeTo=Integer.parseInt(rangeValues[3]);
			if(timeFrom==0 && timeTo==23)
				timeFrom=timeTo=null;
		}
		
		if(sort==null || sort==""){
			sort="date";
		}
		
		if(from!=null && from!="" && to!=null && to!="" && date!=null && date!=""){		
			List<Lift> listResults=lm.findLiftByFromAndToAndCostAndTimeAndDate(from, to, date, range, timeTo, timeFrom);
//			List<Lift> listResultDetours=lm.findDetourByFromAndToAndCostAndTimeAndDate(from, to, date, range, timeTo, timeFrom);
//			if(listResults!=null)
//				listResults.addAll(listResultDetours);
			
			int noResult;
			
			if(listResults!=null && !listResults.isEmpty()){
				
				PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(listResults);		
				pageHolder.setPage(Integer.parseInt(nPage));
				pageHolder.setPageSize(10);	
				model.addAttribute("pages", pageHolder.getPageCount());
				model.addAttribute("page",pageHolder.getPage());
				model.addAttribute("pageHolder",pageHolder);
				noResult=0;
				model.addAttribute("noResult",noResult);
				System.out.println("res");
			
			}
			else{
				noResult=1;
				System.out.println("noRes");
				model.addAttribute("noResult",1);
			}
			model.addAttribute("from",from);
			model.addAttribute("to",to);
		}
		else{
			model.addAttribute("noResult",1);
		}
		
		return "resultSearch";	
	}

	@RequestMapping(value="/HandleShowLiftDetail", method = RequestMethod.GET)
	public String retriveLiftDetails(@RequestParam("lift") String lift, Model model, HttpSession session){
		LiftMapper lm=new LiftMapper();
		if(lift.matches("[0-9]+")){
			Lift l=lm.findById(lift);
			LiftToViewFacade lc=new LiftToViewFacade();
			model.addAttribute("lift",lc.convert(l));
			model.addAttribute("route",l.computeRoute());
			RegisteredUser u=(RegisteredUser) session.getAttribute("user");
			if(u==null || l.getUserOffering().getId()!=u.getId()){				
				RegisteredUserMapper rm=new RegisteredUserMapper();
				RegisteredUser r=rm.findRegisteredUserById(l.getUserOffering().getId());
				model.addAttribute("userOffering",r);
				model.addAttribute("userNickName",r.computeNickName());
				model.addAttribute("userAge",r.computeAge());
			}
			else{
				model.addAttribute("userOffering",u);
				model.addAttribute("userNickName",u.computeNickName());
				model.addAttribute("userAge",u.computeAge());
				if(u.getUserActivity()!=null){
					if(u.getUserActivity().getMemberSince()!=null)
						model.addAttribute("memberSince",ParseDate.getItalianFormat(u.getUserActivity().getMemberSince().toString()));
					if(u.getUserActivity().getLastOnline()!=null)
						model.addAttribute("lastOnline",ParseDate.getItalianFormat(u.getUserActivity().getLastOnline().toString()));
				}
			}
//			else{
//				RegisteredUserMapper rm=new RegisteredUserMapper();
//				RegisteredUser r=rm.findRegisteredUserById(l.getUserOffering().getId());
//				model.addAttribute("userOffering",r);
//				model.addAttribute("userNickName",r.computeNickName());
//				model.addAttribute("userAge",r.computeAge());
//			}
			System.out.println(l.getDetours().size());
			return "showLiftDetails";
		}
		return "error";
	}
	
	@RequestMapping(value="ContactUser")
	public String contactAnOtherUser(@RequestParam String lift, @RequestParam String seat, Model m, HttpSession session){
		if(session.getAttribute("user")!=null){
			m.addAttribute("lift",lift);
			m.addAttribute("seat", seat);
			return "bookALift";
		}
		return "userLogIn"; //TODO
	}
	
	@RequestMapping(value="BookALift")
	public String handleBooking(@RequestParam String lift, @RequestParam String seat,Model model, HttpSession session){
		if(session.getAttribute("user")!=null && lift!=null && lift!=""){
			LiftMapper lm=new LiftMapper();
			Lift l=new Lift();
			int nSeat=Integer.parseInt(seat)-1;
			l.setnSeat(nSeat);
			boolean updated=lm.update(l, Long.parseLong(lift));
			if(updated)
				model.addAttribute("out", "La prenotazione � avvenuta con successo.");
			else
				model.addAttribute("out", "La prenotazione non � avvenuta. Riprovare in seguito.");
			return "bookALift";
		}
		return "error"; //TODO
	}

}
