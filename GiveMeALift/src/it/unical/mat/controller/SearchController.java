package it.unical.mat.controller;

import java.util.List;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.domain.Lift;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

	@RequestMapping(value="/ResultSearch", method = RequestMethod.GET)
	public String handleSearch(@RequestParam("mapFrom") String input1, 
    		@RequestParam("mapTo") String input2,
    		@RequestParam("date") String input3,
    		@RequestParam(value="page",required=false) String p,
    		Model model){
		
			//			Lift l=new Lift();
//			LiftPoint lp=new LiftPoint();
//			lp.setCity(input1);
//			LiftPoint lp2=new LiftPoint();
//			lp2.setCity(input2);
//			LiftPointMapper lpm=new LiftPointMapper();
//			long idLp=lpm.insert(lp);
//			long idLp2=lpm.insert(lp2);
//			l.setPickUpPoint(lp);
//			l.setDropOffPoint(lp2);
//			l.setCost(2);
//			l.setnSeat(2);
//			l.setPossibleDetour(true);
//			lm.insert(l);
			
			LiftMapper lm=new LiftMapper();
			
			if(p==null || p==""){
				p="1";
			}
			PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(lm.findLiftByFromAndTo(input1, input2, input3));		
			pageHolder.setPage(Integer.parseInt(p));
			pageHolder.setPageSize(10);
			
			model.addAttribute("from",input1);
			model.addAttribute("to",input2);
			
			model.addAttribute("pages", pageHolder.getPageCount());
			model.addAttribute("page",pageHolder.getPage());
			
			model.addAttribute("pageHolder",pageHolder);
			
			return "resultSearch";			
	}
	
	@RequestMapping(value="/AdvancedSearchOptions", method = RequestMethod.GET)
	public String handleAdvancedSearch(@RequestParam("from") String input1, 
    		@RequestParam("to") String input2,
    		@RequestParam("date") String input3,
    		@RequestParam("range") String range,
    		@RequestParam("radio") String radio,
    		@RequestParam(value="page",required=false) String p,
    		Model model){
		
		LiftMapper lm=new LiftMapper();
		
		if(p==null || p==""){
			p="1";
		}
		String[] rangeValues=range.split(" ");
		int timeFrom=Integer.parseInt(rangeValues[1]);
		int timeTo=Integer.parseInt(rangeValues[3]);
		
		List<Lift> listResults=lm.findLiftByFromAndToAndCostAndTimeAndDate(input1, input2, input3, radio, timeTo, timeFrom);
		if(listResults!=null){
			
			PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>();		
			pageHolder.setPage(Integer.parseInt(p));
			pageHolder.setPageSize(10);	
			model.addAttribute("pages", pageHolder.getPageCount());
			model.addAttribute("page",pageHolder.getPage());
			model.addAttribute("pageHolder",pageHolder);
		
		}
		
		model.addAttribute("from",input1);
		model.addAttribute("to",input2);
		
		return "resultSearch";	
	}
}
