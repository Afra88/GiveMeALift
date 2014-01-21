package it.unical.mat.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.LiftDetourMapper;
import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.LiftPointMapper;
import it.unical.mat.datamapper.LiftPreferenceMapper;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.LiftPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.service.DetoursCostConverterFacade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfferController {
	
	@RequestMapping(value ="/OfferALift")
	public String insertAnOffer(Model model,HttpSession session){
			if(session.getAttribute("user")!=null){
			return "offerALift";
		}
		else{
			return "signUp";
		}
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
    		Model model,HttpSession session){
		
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
		String completeReturnDate = "";
		
		Date dG = null;
		long dateGoingMillis = 0;
		
		Date dR = null;
		long dateReturnMillis = Integer.MAX_VALUE;
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
				dG.setHours(Integer.parseInt(goingHour));
				dG.setMinutes(Integer.parseInt(goingMins));
				dR = f.parse(dates[1]);
				dR.setHours(Integer.parseInt(returnHour));
				dR.setMinutes(Integer.parseInt(returnMins));
			} catch (ParseException e) {		e.printStackTrace();	}
			
			dateGoingMillis = dG.getTime();
			dateReturnMillis = dR.getTime();
			
			Date today = new Date();			
			System.out.println(today);
			if(dG.compareTo(today)<=0){
				System.out.println(dG.compareTo(today));
				model.addAttribute("error", "I dati inseriti non sono validi, riprova.");
				return "offerALift";
			}
			if(dR!=null && dR.compareTo(today)<=0){
				model.addAttribute("error", "I dati inseriti non sono validi, riprova.");
				return "offerALift";
			}
			if(dR.compareTo(dG)<=0){
				model.addAttribute("error", "I dati inseriti non sono validi, riprova.");
				return "offerALift";
			}
			
			
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
		
		
		
		if(mapFrom == "" || mapTo == "" || mapFrom==null || mapTo==null){
			/*
			 *  mettere un attributo al model per far capire all'utente che c'è stato un errore
			 *  (lanciare un alert)
			 */
			System.out.println("Scegli luogo Partenza/Arrivo");
			model.addAttribute("error", "Scegli i luoghi di Partenza e Arrivo.");
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
		
		System.out.println(" FROM: " + mapFrom);
		System.out.println(" TO: " + mapTo);
		
		System.out.println(" DET0: " + detour0);
		System.out.println(" DET1: " + detour1);
		System.out.println(" DET2: " + detour2);
		System.out.println(" DET3: " + detour3);
		System.out.println(" DET4: " + detour4);
		
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
		
//		int last = path.size()-1;
//		System.out.println(".."+path.get(last));
//		System.out.println(path.get(path.size()-1));
		
		
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
				else{
					model.addAttribute("error", "I dati inseriti non sono validi, riprova.");
					return "offerALift";	
				}
			}
			

		
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
    		@RequestParam("seats") String seats,
    		@RequestParam("luggage") String luggage,
    		@RequestParam("delay") String delay,
    		@RequestParam("deviation") String deviation,
    		@RequestParam("roadType") String roadType,
    		@RequestParam("pinkTrip") String pinkTrip,
    		@RequestParam(value="drivingLicence",required=false) String checkLicence,
			Model m, HttpSession session){
		
		
		if(checkLicence == null)
			return "insertALift"; // ti fanno la multa ! :D

		
		///////////////////////// MAPPER ////////////////////////////////////
	
		LiftPointMapper pm = new LiftPointMapper();
		LiftDetourMapper ldm = new LiftDetourMapper(); 
		LiftMapper lm = new LiftMapper();
		LiftPreferenceMapper lpm = new LiftPreferenceMapper();
		
		///////////////////////// MAPPER ////////////////////////////////////
		
				
		/* Parametri da inserire in Lift*/	
		String[] costs = price.split(",");     //COST
		Integer totcost = 0;
		for (int i = 0; i < costs.length; i++) {
			totcost += Integer.parseInt(costs[i]);
		}
		
		Integer nSeats = Integer.parseInt(seats);    //NSEATS
		
		/*
		 * TODO
		 */
		
		
		Boolean possibleDetour = false;
		
		if(deviation != null && deviation!="")
			possibleDetour = true;
		
//		if(goingMins.length()==2 && goingMins.charAt(0)=='0')
//			goingMins=goingMins.charAt(1)+"";   //eccezione "00"
		
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
		
		
		LiftPoint point0 = new LiftPoint(detour0);	LiftPoint point1 = new LiftPoint(detour0);
		LiftPoint point2 = new LiftPoint(detour0);	LiftPoint point3 = new LiftPoint(detour0);
		LiftPoint point4 = new LiftPoint(detour0);

		ArrayList <LiftPoint> path = new ArrayList<LiftPoint>();
	//	path.add(new LiftPoint(mapFrom));    //		pickUpPoint  0


		LiftPoint from = new LiftPoint(mapFrom);
		pm.insert(from);
		path.add(from);
		
//		path.add(new LiftPoint(mapTo));  		//dropOffPoint path.size()-1
		
		LiftPoint to = new LiftPoint(mapTo);
		path.add(to);
		pm.insert(to);

		
		if(detour0 != "" && detour0!=(null)){
			
			path.add(point0);
//			path.add(new LiftPoint(detour0));
			pm.insert(point0);

		}
		if(detour1 != "" && detour1!=(null)){
			
			path.add(point1);
			pm.insert(point1);

		}
		if(detour2 != "" && detour2!=(null)){
			
			path.add(point2);
			pm.insert(point2);

		}
		if(detour3 != "" && detour3!=(null)){
			path.add(point3);
			pm.insert(point3);

		}
		if(detour4 != "" && detour4!=(null)){
			
			path.add(point4);
			pm.insert(point4);
			
		}


		LiftPoint pickUpPoint = path.get(0);
		LiftPoint dropOffPoint = path.get(path.size()-1);
		
		/////////////////////////UTENTE ////////////////////////////////////		
		RegisteredUser user = 	(RegisteredUser) session.getAttribute("user");	
		/////////////////////////UTENTE ////////////////////////////////////		
			
				
		/////////////////////////LIFT PREFERENCES////////////////////////////////////
		Integer luggageSize;
		Boolean pink;

		
		if(luggage == "piccolo")
			luggageSize = 1;
		else if(luggage == "medio")
			luggageSize = 2;
		else
			luggageSize = 3;	
		
		if(pinkTrip.equals("viaggio con uomini e donne"))
			pink = false;
		else
			pink = true;
		
		LiftPreference lp = new LiftPreference(roadType,luggageSize,delay,pink);
		lpm.insert(lp);
		
		/////////////////////////LIFT PREFERENCES////////////////////////////////////
		
		java.sql.Date sd = new java.sql.Date(departureDate.getTime()); // TODO controllare e fare una conversione precisa
						
		/////////////////////////LIFT  ////////////////////////////////////
		Lift l = new Lift(totcost, nSeats, possibleDetour, departureTime, sd, pickUpPoint, dropOffPoint);				
		l.setUserOffering(user);		
		l.setLiftPreferences(lp);
		/////////////////////////LIFT  ////////////////////////////////////

		DetoursCostConverterFacade df = new DetoursCostConverterFacade();
		List<Integer> prices = new ArrayList<Integer>();
		
		
		 //ADD SINGLE COST OF EACH DETOUR TO THE LIFT
		for (int i = 0; i < costs.length; i++) {
			prices.add(Integer.parseInt(costs[i]));
		}
		
		df.createStringCost(prices, l);

		
		/////////////////////////Ad ogni LIFT  DETOUR trovato assegno il lift che sto inserendo ////////////////////////////////////
		List<LiftDetour> detours = new ArrayList<LiftDetour>();
		for (int i = 0; i < path.size()-1; i++) {
			for (int j = i+1; j < path.size(); j++) {				

				//funzione find dai campi pickUpPoint e dropOffPoint 				
				List<LiftDetour> list = ldm.findDetourFromPickUpAndDropOffPoints(path.get(i).getCity(), path.get(j).getCity());
				
				LiftDetour tmpDetour;
				
				// se esiste lo prendo altrimenti lo creo
				if(list.size() > 0)
					 tmpDetour = list.get(0);
				else
					tmpDetour = new LiftDetour(path.get(i),path.get(j));								
				//una volta trovato o creato va associato al lift di partenza				
				tmpDetour.getLiftList().add(l); 				
				
				// se esiste lo aggiorno altrimenti lo inserisco
				if(list.size() > 0)
					ldm.update(tmpDetour);  //check update
				else
					ldm.insert(tmpDetour);
				
				detours.add(tmpDetour);
			}			
		}

		/////////////////////////LIFT  DETOURS////////////////////////////////////
		//set deours e salvo
		l.setDetours(detours); 		
		lm.insert(l);			
		
		///////////////////////// RETURN LIFT IF PRESENT ////////////////////////////////////
		
		if(returnIsPresent){
			long mstimeR = (1000*60*Integer.parseInt(returnMins)) + (1000*60*60*Integer.parseInt(returnHour));		
			Time returnTime = new Time(mstimeR);       //RETURNTIME
			java.sql.Date srd = new java.sql.Date(returnDate.getTime()); // cambiare
			
			//Creo lift ritorno
			Lift lr = new Lift(totcost, nSeats, possibleDetour, returnTime, srd,  pickUpPoint, dropOffPoint);

			//metto utente e preferenze che sono gli stessi dell'andata
			lr.setUserOffering(user);
			lr.setLiftPreferences(lp);			
			
			List<LiftDetour> detoursReturn = new ArrayList<LiftDetour>();
			
//			Scorro gli stessi detour e li inverto
			
			for (int i = 0; i < detours.size(); i++) {									
				
				List<LiftDetour> list = ldm.findDetourFromPickUpAndDropOffPoints(detours.get(i).getDropOffPoint().getCity(), detours.get(i).getPickUpPoint().getCity());
				LiftDetour temp;
				
				// se esiste lo prendo altrimenti lo creo
				if(list.size() > 0)
					 temp = list.get(0);
				else
					temp = new LiftDetour(detours.get(i).getDropOffPoint() ,detours.get(i).getPickUpPoint());		// Inversione						
				//una volta trovato o creato va associato al lift di partenza				
				temp.getLiftList().add(lr); 				
				
				// se esiste lo aggiorno altrimenti lo inserisco
				if(list.size() > 0)
					ldm.update(temp);
				else
					ldm.insert(temp);
				
				detoursReturn.add(temp);
		
			}
			//set deours e salvo
			lr.setDetours(detoursReturn);
			lm.insert(lr);			
		}		
		
		return "submitALift";
	}	
	
	
	
}

