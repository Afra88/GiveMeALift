package it.unical.mat.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.LiftDetourMapper;
import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.datamapper.SortOption;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
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
	
	@RequestMapping(value="/ResultSearch", method = RequestMethod.GET)
	public String handleAdvancedSearch(@RequestParam("mapFrom") String from, 
    		@RequestParam("mapTo") String to,
    		@RequestParam("date") String date,
    		@RequestParam(value="sort",required=false) String sort,
    		@RequestParam(value="range",required=false) String range,
    		@RequestParam(value="radio",required=false) String radio,
    		@RequestParam(value="page",required=false) String nPage,
    		@RequestParam(value="flexibleDate",required=false) String flexibleDate,
    		@RequestParam(value="chatness",required=false) String chatness,
    		@RequestParam(value="smoking",required=false) String smok,
    		@RequestParam(value="music",required=false) String music,
    		@RequestParam(value="pets",required=false) String pets,
    		Model model){
		
		LiftDetourMapper ldm=new LiftDetourMapper();
		
		if(nPage==null || nPage.equals("")){
			nPage="1";
		}
		
		if(radio.equals("") || radio==null){
			radio=null;
		}
		Integer timeFrom=null;
		Integer timeTo=null;
		
		if(range!=null && !range.equals("")){
			String[] rangeValues=range.split(" ");
			timeFrom=Integer.parseInt(rangeValues[1]);
			timeTo=Integer.parseInt(rangeValues[3]);
			if(timeFrom==0 && timeTo==23)
				timeFrom=timeTo=null;
		}
		
		if(sort==null || sort.equals("")){
			sort="date";
		}
		
		Boolean mus=null;
		if(music!=null && !music.equals("")){
			if(music.equals("noMus"))
				mus=false;
			if(music.equals("yesMus"))
				mus=true;
		}
		
		Boolean pets1=null;
		if(pets!=null && !pets.equals("")){
			if(pets.equals("noPets"))
				pets1=false;
			if(music.equals("yesPets"))
				pets1=true;
		}
		
		Boolean smoking=null;
		if(smok!=null && !smok.equals("")){
			if(smok.equals("noSmok"))
				smoking=false;
			if(smok.equals("yesSmok"))
				smoking=true;
		}
		Integer chatnessLevel=null;
		if(chatness!=null && chatness.equals("")){
			if(chatness.equals("1"))
				chatnessLevel=1;
			if(chatness.equals("2"))
				chatnessLevel=2;
			if(chatness.equals("3"))
				chatnessLevel=3;
		}
		
		if(from!=null && !from.equals("") && to!=null && !to.equals("") && date!=null && !date.equals("")){
			List<LiftDetour> listResults=null;
			if(sort.equals("date")){
				listResults=ldm.findDetourByFromAndToAndCostAndTimeAndDate(from, to, date, range, timeTo, timeFrom, flexibleDate, SortOption.DATE, mus, smoking, pets1, chatnessLevel);
			}
			if(sort.equals("cost")){				
				listResults=ldm.findDetourByFromAndToAndCostAndTimeAndDate(from, to, date, range, timeTo, timeFrom, flexibleDate, SortOption.COST, mus, smoking, pets1, chatnessLevel);
			}
			else{
				listResults=ldm.findDetourByFromAndToAndCostAndTimeAndDate(from, to, date, range, timeTo, timeFrom, flexibleDate, null, mus, smoking, pets1, chatnessLevel);
			}

			int noResult;
			
			if(listResults!=null && !listResults.isEmpty()){				
				PagedListHolder<LiftDetour> pageHolder=new PagedListHolder<LiftDetour>(listResults);		
				pageHolder.setPage(Integer.parseInt(nPage));
				pageHolder.setPageSize(10);	
				model.addAttribute("pages", pageHolder.getPageCount());
				model.addAttribute("page",pageHolder.getPage());
				model.addAttribute("pageHolder",pageHolder);
				noResult=0;
				model.addAttribute("noResult",noResult);
			
			}
			else{
				noResult=1;
				model.addAttribute("noResult",1);
			}
			model.addAttribute("from",from);
			model.addAttribute("to",to);
			model.addAttribute("date",date);
			model.addAttribute("flexibleDate", flexibleDate);
			model.addAttribute("timeFrom",timeFrom);
			model.addAttribute("timeTo",timeTo);
			model.addAttribute("radio",radio);
			model.addAttribute("music", music);
			model.addAttribute("pets", pets);
			model.addAttribute("chatness", chatness);
			model.addAttribute("smoking", smok);
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
	public String handleBooking(@RequestParam String lift, @RequestParam String seat, Model model, HttpSession session){
		RegisteredUser user=(RegisteredUser) session.getAttribute("user");
		if(user!=null && lift!=null && !lift.equals("")){
			LiftMapper lm=new LiftMapper();
			Lift originalLift=lm.findById(lift);
			if(originalLift.getLiftPreferences().getPinkTrip()==true && !user.getGender().equals("F")){
				model.addAttribute("out", "La prenotazione non è avvenuta, è un viaggio rosa, per sole donne.");
				return "bookALift";
			}
			if(originalLift.getnSeat()>0){
				model.addAttribute("out", "La prenotazione non è avvenuta, i posti sono esauriti.");
				return "bookALift";
			}
			Lift l=new Lift();
			int nSeat=Integer.parseInt(seat)-1;
			l.setnSeat(nSeat);
			Lift updated=(Lift) lm.update(l, Long.parseLong(lift));
			if(updated!=null)
				model.addAttribute("out", "La prenotazione è avvenuta con successo.");
			else
				model.addAttribute("out", "La prenotazione non è avvenuta. Riprovare in seguito.");
			return "bookALift";
		}
		return "error"; //TODO
	}

}
