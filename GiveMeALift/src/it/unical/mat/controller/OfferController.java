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
		String completeGoingDate = dates[0];
		
		int gmins = Integer.parseInt(goingMins);
		long goingTime = gmins + (Integer.parseInt(goingHour)*60);
			
		// ************** UTILI SOLO SE SE C'è DATA DI RITORNO	
		String completeReturnDate = "NULL";
		
		Date dG = null;
		long dateGoingMillis = 0;
		
		Date dR = null;
		long dateReturnMillis = Integer.MAX_VALUE;;
		int rmins = Integer.MAX_VALUE;
		long returnTime = Integer.MAX_VALUE;
		// ************** UTILI SOLO SE SE C'è DATA DI RITORNO	
		
		boolean returnIsPresent=false;
		
		if(dates.length>1 /*and controllo dell'ora di ritorno*/){                      // SE C'è DATA DI RITORNO	
			returnIsPresent=true;
			
//			for (String string : dates) {
//				System.out.println("---"+string);
//			}
//		String[] goingDate = dates[0].split("/");
//		String[] returnDate = dates[1].split("/");
			
			completeReturnDate = dates[1];		
			
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			dG = null;
			dR = null;
			try {
				dG = f.parse(dates[0]);
				dR = f.parse(dates[1]);
			} catch (ParseException e) {		e.printStackTrace();	}
			
			dateGoingMillis = dG.getTime();
			dateReturnMillis = dR.getTime();
			
//			gmins = Integer.parseInt(goingMins);
//			goingTime = gmins + (Integer.parseInt(goingHour)*60);
			
			rmins = Integer.parseInt(returnMins);
			returnTime = rmins + (Integer.parseInt(returnHour)*60);
			
	//		String goingDay = goingDate[0];
	//		String goingMonth = goingDate[1];
	//		String goingYear = goingDate[2];
	//		String returnDay = returnDate[0];
	//		String returnMonth = returnDate[1];
	//		String returnYear = returnDate[2];
			
	//		System.out.println("partenza: "+goingDay + goingMonth + goingYear +"ritorno:" + returnDay + returnMonth + returnYear);
	//		
	
			
		}//if return date is present
		
		
		
		if(mapFrom == "" || mapTo == ""){
			/*
			 *  mettere un attributo al model per far capire all'utente che c'è stato un errore
			 *  (lanciare un alert)
			 */
			System.out.println("Scegli luogo Partenza/Arrivo");			
			return "offerALift";
		}
		
		
		ArrayList<String> inputs = new ArrayList<String> ();
		ArrayList<String> path = new ArrayList<String>();
		
//		inputs.add(mapFrom);				 
//		inputs.add(mapTo);					
		
		
		inputs.add(completeGoingDate);	        //i=0
		// se non c'è è gia settata a "NULL"
		inputs.add(completeReturnDate);		    //i=1 
		
		inputs.add(goingHour);				    //i=2		
		if(gmins>=10)	inputs.add(goingMins);	//i=3
		else inputs.add("0"+goingMins);
		
		if(returnIsPresent){
			inputs.add(returnHour);				    //i=4		
			if(rmins>=10)	inputs.add(returnMins); //i=5		
			else		inputs.add("0"+returnMins);		
		}
		else {
			inputs.add("NULL"); //i=4
			inputs.add("NULL"); //i=5
		}		
		
//		if(detour0!="")
//			inputs.add(detour0);			//i=8
//		if(detour1!="")
//			inputs.add(detour1);			//i=9
//		if(detour2!="")
//			inputs.add(detour2);			//i=10
//		if(detour3!="")
//			inputs.add(detour3);			//i=11
//		if(detour4!="")
//			inputs.add(detour4);			//i=12
		
		System.out.println(mapFrom);
		System.out.println(mapTo);
		
		System.out.println(detour0);
		System.out.println(detour1);
		System.out.println(detour2);
		System.out.println(detour3);
		System.out.println(detour4);
		
		path.add(mapFrom);
		if(detour0 != "" && detour0!=(null))
			path.add(detour0);
		if(detour1 != "" && detour1!=(null))
			path.add(detour1);
		if(detour2 != "" && detour2!=(null))
			path.add(detour2);
		if(detour3 != "" && detour3!=(null))
			path.add(detour3);
		if(detour4 != "" && detour4!=(null))
			path.add(detour4);
		path.add(mapTo);
		
		
		model.addAttribute("inputs", inputs);
		model.addAttribute("path", path);
		
		int last = path.size()-1;
		System.out.println(".."+path.get(last));
		System.out.println(path.get(path.size()-1));
		
		
		if(returnIsPresent){
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
		
		//se non è presente la data di ritorno
		model.addAttribute("inputs",inputs);
		return "insertALift";
		
		
				
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/SubmitALift")
	public String submitOffer(			
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
    		
    		@RequestParam("price") String price,
    		@RequestParam(value="drivingLicence",required=false) String checkLicence,
    		
			Model m){
		
		System.out.println(mapFrom);
//		System.out.println(detour0);
		System.out.println(mapTo);
		
//		if(checkLicence == null)
//			return "insertALift";
		
//		System.out.println(price);
//		System.out.println(checkLicence);
		
		
//		System.out.println(mapFrom);
		
		return "submitALift";
	}
	
}

