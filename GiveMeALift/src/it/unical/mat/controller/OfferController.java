package it.unical.mat.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import it.unical.mat.datamapper.LiftDetourMapper;
import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.LiftPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.service.LiftToViewFacade;
import it.unical.mat.service.ParseDate;

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
	
	@RequestMapping(value ="/Step1UpdateLiftOfferALift")
	public String step1UpdateLiftOfferALift(@RequestParam String lift, Model model, HttpSession session){
		if(session.getAttribute("user")!=null && lift.matches("[0-9]+")){
				LiftMapper lm=new LiftMapper();
				Lift l=lm.findById(lift);
				LiftToViewFacade lc=new LiftToViewFacade();
				List<Object> lConverted=lc.convert(l);
				Lift returnLift=l.getReturnLift();
				if(returnLift!=null){
					List<Object> lrConverted=lc.convert(returnLift);
					model.addAttribute("liftReturn",returnLift);
					model.addAttribute("returnDate", lrConverted.get(1));
					model.addAttribute("returnTimeH",Integer.parseInt((String) lrConverted.get(2)));
					model.addAttribute("returnTimeM",Integer.parseInt((String) lConverted.get(3)));					
				}
				model.addAttribute("lift",l);
				model.addAttribute("goingDate", lConverted.get(1));
				model.addAttribute("goingTimeH",Integer.parseInt((String) lConverted.get(2)));
				model.addAttribute("goingTimeM",Integer.parseInt((String) lConverted.get(3)));

				model.addAttribute("path",l.computeRouteOnlyDetours());
				System.out.println(l.computeRouteOnlyDetours());
				return "step1_updateLift_offerALift";
		}
		return "error";
	}	
	
	@RequestMapping(value ="/Step2UpdateLiftInsertALift")
	public String step2UpdateLiftInsertALift(
			@RequestParam("lift") String lift,
			@RequestParam("liftReturn") String liftReturnId,
			@RequestParam("mapFrom") String mapFrom, 
    		@RequestParam("mapTo") String mapTo,
    		@RequestParam(value="detour",required=false) String[] detour,
//    		@RequestParam(value="detour1",required=false) String detour1,
//    		@RequestParam(value="detour2",required=false) String detour2,
//    		@RequestParam(value="detour3",required=false) String detour3,
//    		@RequestParam(value="detour4",required=false) String detour4,
    		@RequestParam(value="checkReturn",required=false) String thereIsReturn,
    		@RequestParam("goingDate") String goingDate,
    		@RequestParam(value="returnDate",required=false) String returnDate,
    		@RequestParam("goingTimeH") String goingHour,
    		@RequestParam("goingTimeM") String goingMins,
    		@RequestParam(value="returnTimeH",required=false) String returnHour,
    		@RequestParam(value="returnTimeM",required=false) String returnMins,
    		Model model,HttpSession session){
		
			if(session.getAttribute("user")!=null && lift.matches("[0-9]+") && goingDate!=null && !goingDate.equals("") && goingHour!=null && goingHour!=null && goingMins!=null && !goingMins.equals("")){		
				Calendar today = Calendar.getInstance();
				Calendar dG = ParseDate.getUtilDateFormat(goingDate,Integer.parseInt(goingHour), Integer.parseInt(goingMins));
				ArrayList<String> inputs = new ArrayList<String> ();						

				if(dG.compareTo(today)<=0){
					System.out.println(dG.compareTo(today));
					model.addAttribute("error", "La data di andata non è valida.");
					return "step1_updateLift_offerALift";
				}
				
				inputs.add(goingDate);	        				
				inputs.add(goingHour);				    		
				inputs.add(goingMins);

				if(returnDate!=null && !returnDate.equals("") && thereIsReturn=="true"){
					Calendar dR = ParseDate.getUtilDateFormat(returnDate, Integer.parseInt(goingHour), Integer.parseInt(goingMins));
					if(dR!=null && dR.compareTo(today)<=0){
						model.addAttribute("error", "La data di ritorno non è valida.");
						return "step1_updateLift_offerALift";
					}
					if(dR.compareTo(dG)<=0){
						model.addAttribute("error", "La data di ritorno non può essere precedente alla data di andata.");
						return "step1_updateLift_offerALift";
					}
					inputs.add(returnDate);		    
					inputs.add(returnHour);				    		
					inputs.add(returnMins);
			}
			
			if(mapFrom == "" || mapTo == "" || mapFrom==null || mapTo==null){
				model.addAttribute("error", "Scegli i luoghi di Partenza e Arrivo.");
				return "step1_updateLift_offerALift";
			}
			
			List<String> path=composePath(mapFrom, mapTo, detour);
			
			model.addAttribute("inputs", inputs);
			model.addAttribute("path", path);
			
			LiftMapper lm=new LiftMapper();
			Lift l=lm.findById(lift);
			
			List<Double> costs=new ArrayList<Double>();
			for (LiftDetour ld : l.getDetours()) 
				costs.add(ld.getCost());
			
			model.addAttribute("lift",l);
			return "step2_updateLift_insertALift";	
		}
		return "error";
	}
	
	
	
	@RequestMapping(value ="/InsertALift")
	public String insertAnOffer(
			@RequestParam("mapFrom") String mapFrom, 
    		@RequestParam("mapTo") String mapTo,
    		@RequestParam(value="detour",required=false) String[] detour,
//    		@RequestParam(value="detour1",required=false) String detour1,
//    		@RequestParam(value="detour2",required=false) String detour2,
//    		@RequestParam(value="detour3",required=false) String detour3,
//    		@RequestParam(value="detour4",required=false) String detour4,
    		@RequestParam(value="checkReturn",required=false) String thereIsReturn,
    		@RequestParam("goingDate") String goingDate,
    		@RequestParam(value="returnDate",required=false) String returnDate,
    		@RequestParam("goingTimeH") String goingHour,
    		@RequestParam("goingTimeM") String goingMins,
    		@RequestParam(value="returnTimeH",required=false) String returnHour,
    		@RequestParam(value="returnTimeM",required=false) String returnMins,
    		Model model,HttpSession session){
		
		if(session.getAttribute("user")!=null && goingDate!=null && !goingDate.equals("") && goingHour!=null && goingHour!=null && goingMins!=null && !goingMins.equals("")){		
			Calendar today = Calendar.getInstance();
			Calendar dG = ParseDate.getUtilDateFormat(goingDate,Integer.parseInt(goingHour), Integer.parseInt(goingMins));
			ArrayList<String> inputs = new ArrayList<String> ();						

			if(dG.compareTo(today)<=0){
				System.out.println(dG.compareTo(today));
				model.addAttribute("error", "La data di andata non è valida.");
				return "step1_updateLift_offerALift";
			}
			
			inputs.add(goingDate);	        				
			inputs.add(goingHour);				    		
			inputs.add(goingMins);

			if(returnDate!=null && !returnDate.equals("") && thereIsReturn=="true"){
				Calendar dR = ParseDate.getUtilDateFormat(returnDate, Integer.parseInt(goingHour), Integer.parseInt(goingMins));
				if(dR!=null && dR.compareTo(today)<=0){
					model.addAttribute("error", "La data di ritorno non è valida.");
					return "step1_updateLift_offerALift";
				}
				if(dR.compareTo(dG)<=0){
					model.addAttribute("error", "La data di ritorno non può essere precedente alla data di andata.");
					return "step1_updateLift_offerALift";
				}
				inputs.add(returnDate);		    
				inputs.add(returnHour);				    		
				inputs.add(returnMins);
		}
		
		if(mapFrom == "" || mapTo == "" || mapFrom==null || mapTo==null){
			model.addAttribute("error", "Scegli i luoghi di Partenza e Arrivo.");
			return "step1_updateLift_offerALift";
		}
		
		List<String> path=composePath(mapFrom, mapTo, detour);
		
		model.addAttribute("inputs", inputs);
		model.addAttribute("path", path);	
				
				return "insertALift";	
		}
		return "error";			
	}

	private List<String> composePath(String mapFrom, String mapTo, String detour0,
			String detour1, String detour2, String detour3, String detour4) {
		List<String> path=new ArrayList<String>();
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
		return path;
	}
	
	private List<String> composePath(String mapFrom, String mapTo, String[] detour) {
		List<String> path=new ArrayList<String>();
		path.add(mapFrom);
		for (String string : detour) {
			if(string != "" && string!=null && !string.equals(",,,"))
				path.add(string);
		}
//		if(detour0 != "" && detour0!=(null))
//			path.add(detour0);
//		if(detour1 != "" && detour1!=(null))
//			path.add(detour1);
//		if(detour2 != "" && detour2!=(null))
//			path.add(detour2);
//		if(detour3 != "" && detour3!=(null))
//			path.add(detour3);
//		if(detour4 != "" && detour4!=(null))
//			path.add(detour4);
		path.add(mapTo);
		return path;
	}
	
	
	@RequestMapping(value="/Step3UpdateLiftSubmitALift")
	public String step3UpdateLiftSubmitOffer(
			@RequestParam("lift") String liftId,
			@RequestParam("liftReturn") String liftReturnId,
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
    		@RequestParam("description") String description,
    		@RequestParam("deviation") String deviation,
    		@RequestParam("roadType") String roadType,
    		@RequestParam("pinkTrip") String pinkTrip,
    		@RequestParam(value="drivingLicence",required=false) String checkLicence,
			Model m, HttpSession session){

		LiftMapper lm = new LiftMapper();
						
		String[] costs = price.split(",");     //COST
		Double totcost = 0.0;
		for (int i = 0; i < costs.length; i++) {
			totcost += Double.parseDouble(costs[i]);
		}
		
		Integer nSeats = Integer.parseInt(seats);    //NSEATS
		
		Boolean possibleDetour = false;
		
		if(deviation != null && !deviation.equals(""))
			possibleDetour = true;
		
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

		/////////////////////////LIFT PREFERENCES////////////////////////////////////
		Integer luggageSize;
		Boolean pink;
		
		if(luggage.equalsIgnoreCase("piccolo"))
			luggageSize = 1;
		else if(luggage.equalsIgnoreCase("medio"))
			luggageSize = 2;
		else
			luggageSize = 3;	
		
		if(pinkTrip.equalsIgnoreCase("viaggio con uomini e donne"))
			pink = false;
		else
			pink = true;
		
		if(deviation==null)
			deviation="nothing";
		
		LiftPreference lp = new LiftPreference(roadType,luggageSize,delay,pink,deviation);
		
		/////////////////////////LIFT PREFERENCES////////////////////////////////////
	
		ArrayList <LiftPoint> path = new ArrayList<LiftPoint>();
		
		LiftPoint from = new LiftPoint(mapFrom);
		path.add(from);

		if(detour0 != "" && detour0!=(null)){
			LiftPoint point0 = new LiftPoint(detour0);	
			path.add(point0);
		}
		if(detour1 != "" && detour1!=(null)){
			LiftPoint point1 = new LiftPoint(detour1);
			path.add(point1);
		}
		if(detour2 != "" && detour2!=(null)){
			LiftPoint point2 = new LiftPoint(detour2);	
			path.add(point2);
		}
		if(detour3 != "" && detour3!=(null)){
			LiftPoint point3 = new LiftPoint(detour3);
			path.add(point3);
		}
		if(detour4 != "" && detour4!=(null)){
			LiftPoint point4 = new LiftPoint(detour4);
			path.add(point4);
		}
		LiftPoint to = new LiftPoint(mapTo);
		path.add(to);
		
		if(checkLicence == null || checkLicence == "" || checkLicence=="false"){
			List<String> pathString=composePath(mapFrom, mapTo, detour0, detour1, detour2, detour3, detour4);
			m.addAttribute("path",pathString);
			m.addAttribute("error", "Attenzione, devi dichiarare di essere in possesso di patente di guida valida ed assicurazione RCA.");
			return "step2_updateLift_insertALift"; // ti fanno la multa ! :D
		}
				
		java.sql.Date sd = new java.sql.Date(departureDate.getTime()); // TODO controllare e fare una conversione precisa

		Lift l = new Lift(totcost, nSeats, possibleDetour, departureTime, sd, path.get(0), path.get(path.size()-1));

		List<LiftDetour> detours = new ArrayList<LiftDetour>();
		for (int i = 0; i < path.size()-1; i++) {
			for (int j = i+1; j < path.size(); j++) {
				System.out.println("Detour: "+path.get(i).getCity()+" "+path.get(j).getCity());
				double cost = 0;
				if(j-i==1)
					cost=Integer.parseInt(costs[i]);
				else{
					for (int k = i; k < j; k++)
						cost+=Integer.parseInt(costs[k]);						
				}
				LiftDetour tmpDetour=new LiftDetour(path.get(i), path.get(j),cost);
				tmpDetour.setLift(l);
				detours.add(tmpDetour);
			}			
		}

		System.out.println("Partenza data: "+sd);
		System.out.println("tempo partenze: "+departureTime);
		
		RegisteredUser user = 	(RegisteredUser) session.getAttribute("user");		
		l.setDetours(detours);
		l.setLiftPreferences(lp);
		l.setUserOffering(user);
//		DetoursCostConverterFacade.createStringCost(prices, l);

		if(returnIsPresent){
			long mstimeR = (1000*60*Integer.parseInt(returnMins)) + (1000*60*60*Integer.parseInt(returnHour));		
			Time returnTime = new Time(mstimeR);       //RETURNTIME
			java.sql.Date srd = new java.sql.Date(returnDate.getTime()); // cambiare
			
			System.out.println("Partenza data: "+srd);
			System.out.println("tempo partenze: "+returnTime);
			
			Lift lr = new Lift(totcost, nSeats, possibleDetour, returnTime, srd,  path.get(path.size()-1), path.get(0));		
			List<LiftDetour> detoursReturn = new ArrayList<LiftDetour>();
			for (int i = path.size()-1; i>0 ; i--) {
				for (int j = i-1; j >=0; j--) {
					System.out.println("Detour Ritorno: "+path.get(i).getCity()+" "+path.get(j).getCity());
					double cost = 0;
					if(i-j==1)
						cost=Integer.parseInt(costs[j]);
					else{
						for (int k = j; k < i; k++)
							cost+=Integer.parseInt(costs[k]);						
					}
					LiftDetour tmpDetour=new LiftDetour(path.get(i), path.get(j),cost);
					tmpDetour.setLift(lr);
					detoursReturn.add(tmpDetour);
				}			
			}
			
			lr.setDetours(detoursReturn);
			lr.setLiftPreferences(lp);
			lr.setUserOffering(user);
			lr.setIsReturn(true);
			lm.update(lr, Long.parseLong(liftReturnId));		
		}		

		l.setIsReturn(false);
		l.setDescription(description);
		lm.update(l,Long.parseLong(liftId));		
		
		return "step3_updateLift_submitALift";
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
    		@RequestParam("description") String description,
    		@RequestParam("deviation") String deviation,
    		@RequestParam("delay") String delay,
    		@RequestParam("roadType") String roadType,
    		@RequestParam("pinkTrip") String pinkTrip,
    		@RequestParam(value="drivingLicence",required=false) String checkLicence,
			Model m, HttpSession session){

		LiftMapper lm = new LiftMapper();
		LiftDetourMapper ldm = new LiftDetourMapper();
				
		String[] costs = price.split(",");     //COST
		double totcost = 0;
		for (int i = 0; i < costs.length; i++) {
			totcost += Double.parseDouble(costs[i]);
		}
		
		Integer nSeats = Integer.parseInt(seats);    //NSEATS
		
		Boolean possibleDetour = false;
		
		if(deviation != null && !deviation.equals(""))
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

		/////////////////////////LIFT PREFERENCES////////////////////////////////////
		Integer luggageSize;
		Boolean pink;
		
		if(luggage.equalsIgnoreCase("piccolo"))
			luggageSize = 1;
		else if(luggage.equalsIgnoreCase("medio"))
			luggageSize = 2;
		else
			luggageSize = 3;	
		
		if(pinkTrip.equalsIgnoreCase("viaggio con uomini e donne"))
			pink = false;
		else
			pink = true;
		
		if(deviation==null)
			deviation="nothing";
		
		LiftPreference lp = new LiftPreference(roadType,luggageSize,delay,pink,deviation);
		
		/////////////////////////LIFT PREFERENCES////////////////////////////////////
		
		ArrayList <LiftPoint> path = new ArrayList<LiftPoint>();
		
		LiftPoint from = new LiftPoint(mapFrom);
		path.add(from);

		if(detour0 != "" && detour0!=(null)){
			LiftPoint point0 = new LiftPoint(detour0);	
			path.add(point0);
		}
		if(detour1 != "" && detour1!=(null)){
			LiftPoint point1 = new LiftPoint(detour1);
			path.add(point1);
		}
		if(detour2 != "" && detour2!=(null)){
			LiftPoint point2 = new LiftPoint(detour2);	
			path.add(point2);
		}
		if(detour3 != "" && detour3!=(null)){
			LiftPoint point3 = new LiftPoint(detour3);
			path.add(point3);
		}
		if(detour4 != "" && detour4!=(null)){
			LiftPoint point4 = new LiftPoint(detour4);
			path.add(point4);
		}
		LiftPoint to = new LiftPoint(mapTo);
		path.add(to);
		
		if(checkLicence == null || checkLicence == "" || checkLicence=="false"){
			List<String> pathString=composePath(mapFrom, mapTo, detour0, detour1, detour2, detour3,
					detour4);
			m.addAttribute("path",pathString);
			m.addAttribute("error", "Attenzione, devi dichiarare di essere in possesso di patente di guida valida ed assicurazione RCA.");
			return "insertALift"; // ti fanno la multa ! :D
		}
				
		java.sql.Date sd = new java.sql.Date(departureDate.getTime()); // TODO controllare e fare una conversione precisa

		Lift l = new Lift(totcost, nSeats, possibleDetour, departureTime, sd, path.get(0), path.get(path.size()-1));

		List<LiftDetour> detours = new ArrayList<LiftDetour>();
		for (int i = 0; i < path.size()-1; i++) {
			for (int j = i+1; j < path.size(); j++) {
				System.out.println("Detour: "+path.get(i).getCity()+" "+path.get(j).getCity());
				double cost = 0;
				if(j-i==1)
					cost=Integer.parseInt(costs[i]);
				else{
					for (int k = i; k < j; k++)
						cost+=Integer.parseInt(costs[k]);						
				}
				LiftDetour tmpDetour=new LiftDetour(path.get(i), path.get(j),cost);
				tmpDetour.setLift(l);
				ldm.insert(tmpDetour);
				detours.add(tmpDetour);
			}			
		}

		System.out.println("Partenza data: "+sd);
		System.out.println("tempo partenze: "+departureTime);
		
		RegisteredUser user = 	(RegisteredUser) session.getAttribute("user");		
		l.setDetours(detours);
		l.setLiftPreferences(lp);
		l.setUserOffering(user);
//		DetoursCostConverterFacade.createStringCost(prices, l);

		if(returnIsPresent){
			long mstimeR = (1000*60*Integer.parseInt(returnMins)) + (1000*60*60*Integer.parseInt(returnHour));		
			Time returnTime = new Time(mstimeR);       //RETURNTIME
			java.sql.Date srd = new java.sql.Date(returnDate.getTime()); // cambiare
			
			System.out.println("Partenza data: "+srd);
			System.out.println("tempo partenze: "+returnTime);
			
			Lift lr = new Lift(totcost, nSeats, possibleDetour, returnTime, srd,  path.get(path.size()-1), path.get(0));	
			List<LiftDetour> detoursReturn = new ArrayList<LiftDetour>();
			for (int i = path.size()-1; i>0 ; i--) {
				for (int j = i-1; j >=0; j--) {
					System.out.println("Detour Ritorno: "+path.get(i).getCity()+" "+path.get(j).getCity());
					double cost = 0;
					if(i-j==1)
						cost=Integer.parseInt(costs[j]);
					else{
						for (int k = j; k < i; k++)
							cost+=Integer.parseInt(costs[k]);						
					}
					LiftDetour tmpDetour=new LiftDetour(path.get(i), path.get(j),cost);
					tmpDetour.setLift(lr);
					ldm.insert(tmpDetour);
					detoursReturn.add(tmpDetour);
				}			
			}
			
			lr.setDetours(detoursReturn);
			lr.setLiftPreferences(lp);
			lr.setIsReturn(true);
			lr.setUserOffering(user);
//			DetoursCostConverterFacade.createStringCost(prices, lr);
			l.setReturnLift(lr);
		
		}
		
		l.setIsReturn(false);
		l.setDescription(description);
		
		System.out.println(deviation);
		System.out.println(description);

		if(lm.insert(l)!=0){			
			return "submitALift";
		}	
		return "error";		
		
	}
	
	
	
}

