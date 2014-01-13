package it.unical.mat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.domain.Lift;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfferController {
	
	@RequestMapping(value ="/OfferALift")
	public String directToOffers(Model model){
		
		/*
		 * Controlli sull'utente registrato
		 * 
		 */
		
		return "offerALift";
	}
	
	@RequestMapping(value ="/InsertALift")
	public String insertAnOffer(
			@RequestParam("mapFrom") String mapFrom, 
    		@RequestParam("mapTo") String mapTo,
    		@RequestParam(value="detour0",required=false) String detour0,
    		@RequestParam(value="detour1",required=false) String detour1,
    		@RequestParam(value="detour2",required=false) String detour2,
    		@RequestParam(value="detour3",required=false) String detour3,
    		@RequestParam(value="detour4",required=false) String detour4,
    		@RequestParam("date") String date,
    		@RequestParam("goingTimeH") String goingHour,
    		@RequestParam("goingTimeM") String goingMins,
    		@RequestParam(value="returnTimeH",required=false) String returnHour,
    		@RequestParam(value="returnTimeM",required=false) String returnMins,
    		Model model){
		
		/*split of date parameters for checking*/
//		12/01/2014,12/01/2014
//		System.out.println(date);
//		System.out.println(goingHour);
//		System.out.println(goingMins);
//		System.out.println(returnHour);
//		System.out.println(returnMins);
		
		String[] dates = date.split(",");
		
		for (String string : dates) {
			System.out.println("---"+string);
		}
		
//		String[] goingDate = dates[0].split("/");
//		String[] returnDate = dates[1].split("/");
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date dG = null;
		Date dR = null;
		try {
			dG = f.parse(dates[0]);
			dR = f.parse(dates[1]);
		} catch (ParseException e) {		e.printStackTrace();	}
		long dateGoingMillis = dG.getTime();
		long dateReturnMillis = dR.getTime();
		
		long goingTime = Integer.parseInt(goingMins)+ (Integer.parseInt(goingHour)*60);
		long returnTime = Integer.parseInt(returnMins)+ (Integer.parseInt(returnHour)*60);
		
//		String goingDay = goingDate[0];
//		String goingMonth = goingDate[1];
//		String goingYear = goingDate[2];
//		String returnDay = returnDate[0];
//		String returnMonth = returnDate[1];
//		String returnYear = returnDate[2];
		
//		System.out.println("partenza: "+goingDay + goingMonth + goingYear +"ritorno:" + returnDay + returnMonth + returnYear);
//		
		ArrayList<String> inputs = new ArrayList<String> ();
		inputs.add(mapFrom);
		inputs.add(mapTo);
		inputs.add(date);
		inputs.add(goingHour);
		inputs.add(goingMins);
		inputs.add(returnHour);
		inputs.add(returnMins); //i= 6
		if(detour0!=null)
			inputs.add(detour0);
		if(detour1!=null)
			inputs.add(detour1);
		if(detour2!=null)
			inputs.add(detour2);
		if(detour3!=null)
			inputs.add(detour3);
		if(detour4!=null)
			inputs.add(detour4);
		
		
		if( dateGoingMillis > dateReturnMillis ){
			System.out.println("errore Data");			
			return "offerALift";
		}
		else if( dateGoingMillis < dateReturnMillis ){			
			model.addAttribute("inputs",inputs);
			return "insertALift";			
		}else{
			if (goingTime<returnTime) {
				model.addAttribute("inputs",inputs);
				return "insertALift";	
			}
		}
		System.out.println("errore Ora/minuti");			
		return "offerALift";
				
	}
	
	@RequestMapping(value="/SubmitALift")
	public String submitOffer(
			Model m){
		
		return "submitALift";
	}
	
}

