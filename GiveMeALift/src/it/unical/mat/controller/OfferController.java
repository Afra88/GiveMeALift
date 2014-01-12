package it.unical.mat.controller;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.LiftPointMapper;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftPoint;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.RETURN;

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
	public String insertAnOffer(Model model){
		
		
		return "insertALift";
	}
	
	
}
