package it.unical.mat.controller;

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
    		@RequestParam("page") String p,
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
			PagedListHolder<Lift> pageHolder=new PagedListHolder<Lift>(lm.findLiftByFromAndTo(input1, input2, input3));		
			pageHolder.setPage(Integer.parseInt(p));
			pageHolder.setPageSize(10);
			
			model.addAttribute("pageHolder",pageHolder);
			
			return "resultSearch";			
	}
}
