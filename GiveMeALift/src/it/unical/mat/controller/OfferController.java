package it.unical.mat.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.LiftPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

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
			
		// ************** UTILI SOLO SE SE C'� DATA DI RITORNO	
		String completeReturnDate = "NULL";
		
		Date dG = null;
		long dateGoingMillis = 0;
		
		Date dR = null;
		long dateReturnMillis = Integer.MAX_VALUE;;
		int rmins = Integer.MAX_VALUE;
		long returnTime = Integer.MAX_VALUE;
		// ************** UTILI SOLO SE SE C'� DATA DI RITORNO	
		
		boolean returnIsPresent=false;
		
		if(dates.length>1 /*and controllo dell'ora di ritorno*/){                      // SE C'� DATA DI RITORNO	
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
			 *  mettere un attributo al model per far capire all'utente che c'� stato un errore
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
		// se non c'� � gia settata a "NULL"
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
		
		//se non � presente la data di ritorno
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
    		@RequestParam("seats") String seats,
    		@RequestParam("luggage") String luggage,
    		@RequestParam("delay") String delay,
    		@RequestParam("deviation") String deviation,
    		@RequestParam("timesForThisRoute") String timesForThisRoute,
    		@RequestParam("roadType") String roadType,
    		@RequestParam("pinkTrip") String pinkTrip,
    		@RequestParam(value="drivingLicence",required=false) String checkLicence,
			Model m){

		
		/* Parametri da inserire in Lift*/	
		String[] costs = price.split(",");     //COST
		Integer cost = 0;
		for (int i = 0; i < costs.length; i++) {
			cost += Integer.parseInt(costs[i]);
		}
		
		Integer nSeats = Integer.parseInt(seats);    //NSEATS
		String possibleDetour = deviation;
		
		if(goingMins.length()==2 && goingMins.charAt(0)=='0')
			goingMins=goingMins.charAt(1)+"";   //eccezione "00"
		
		long mstimeG = (1000*60*Integer.parseInt(goingMins)) + (1000*60*60*Integer.parseInt(goingHour));		
		Time departureTime = new Time(mstimeG);       //DEPARTURETIME
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date departureDate = null;   
		Date returnDate = null;
		String[] dates = date.split(",");
		boolean returnIsPresent = false;

		try {
			departureDate = f.parse(dates[0]);            //DEPARTUREDATE
			if (dates.length > 1) {
				returnIsPresent = true;
				returnDate = f.parse(dates[1]);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		
		
		ArrayList <LiftPoint> path = new ArrayList<>();
		path.add(new LiftPoint(mapFrom));    //		pickUpPoint  0

		if(detour0 != "" && detour0!=(null))
			path.add(new LiftPoint(detour0));
		if(detour1 != "" && detour1!=(null))
			path.add(new LiftPoint(detour1));
		if(detour2 != "" && detour2!=(null))
			path.add(new LiftPoint(detour2));
		if(detour3 != "" && detour3!=(null))
			path.add(new LiftPoint(detour3));
		if(detour4 != "" && detour4!=(null))
			path.add(new LiftPoint(detour4));
		path.add(new LiftPoint(mapTo));  		//dropOffPoint path.size()-1
				LiftPoint pickUpPoint = path.get(0);
		LiftPoint dropOffPoint = path.get(path.size()-1);
		
		
		LiftMapper lm = new LiftMapper();
		
		/////////////////////////UTENTE TEMPORANEO////////////////////////////////////		
		Address addr = new Address("via Bucci", "Rende", "Italia");
		RegisteredUser user = new RegisteredUser("email","psw","name","surname","M",1970,"012345","0123456",addr);				
		/////////////////////////UTENTE TEMPORANEO////////////////////////////////////
		user.setBirthYear(2009);
		lm.insert(user); //da eccezione qua
		
				
//		/////////////////////////LIFT PREFERENCES////////////////////////////////////
//		Integer luggageSize;
//		Boolean pink;
//		Integer times = Integer.parseInt(timesForThisRoute);
//		
//		if(luggage == "piccolo")
//			luggageSize = 1;
//		else if(luggage == "medio")
//			luggageSize = 2;
//		else
//			luggageSize = 3;	
//		
//		if(pinkTrip.equals("viaggio con uomini e donne"))
//			pink = false;
//		else
//			pink = true;
//		LiftPreference lp = new LiftPreference(roadType,times,luggageSize,delay,pink);
//		/////////////////////////LIFT PREFERENCES////////////////////////////////////
//		
//		lm.insert(lp);

		java.sql.Date sd = new java.sql.Date(departureDate.getTime()); // cambiare
						
		/////////////////////////LIFT  ////////////////////////////////////
		Lift l = new Lift(cost, nSeats, possibleDetour, departureTime, sd, pickUpPoint, dropOffPoint);				
		
		l.setUserOffering(user);		
//		l.setLiftPreferences(lp);
		/////////////////////////LIFT  ////////////////////////////////////
		lm.insert(l);

		
		/////////////////////////LIFT  DETOURS////////////////////////////////////
		List<LiftDetour> detours = new ArrayList<>();
		for (int i = 0; i < path.size()-1; i++) {
			for (int j = i+1; j < path.size(); j++) {				

				//TODO fare funzione find dai campi pickuppoint e dropoffpoint ? 				
				LiftDetour tmpDetour = new LiftDetour(path.get(i),path.get(j));								
				//una volta trovato o creato va associato al lift di partenza				
				tmpDetour.getLiftList().add(l); 				
				
				lm.insert(tmpDetour);  // controllare se � giusto
//				se c'� gia 
//				lm.update(tmpDetour);
				
				detours.add(tmpDetour);
			}			
		}

		/////////////////////////LIFT  DETOURS////////////////////////////////////
		
		l.setDetours(detours); 		
		lm.update(l); 			// controllare se � giusto
			
		
		if(returnIsPresent){
			//TODO fare ritorno
			//TODO fare ritorno
			//TODO fare ritorno
			//TODO fare ritorno
		}

		
//		System.out.println(mapFrom);
//		System.out.println(detour0);
//		System.out.println(mapTo);
		
//		if(checkLicence == null)
//			return "insertALift";
		
//		System.out.println(price);
//		System.out.println(checkLicence);
		
		
//		System.out.println(mapFrom);
		
		
		
		
		return "submitALift";
	}	
	

	
}

