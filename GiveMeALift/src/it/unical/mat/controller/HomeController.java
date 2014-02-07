package it.unical.mat.controller;

import java.util.List;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.domain.Lift;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
	
	 @RequestMapping(value = "/ShowNextLifts")
		public String showNextLifts(Model model,HttpSession session){
			
			LiftMapper lm = new LiftMapper();
			
			List<Lift> lifts = lm.findNextLifts();
			
			for (Lift l : lifts) {
				System.out.println("*******l "+l.getDepartureDate());
			}
			
			
//			if(lifts.size()!=0){
//				model.addAttribute("lifts", lifts);
//				return "showNextLifts";
//			}
			return "showNextLifts";
			
		}
	
	
}

