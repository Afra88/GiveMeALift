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

@Controller
public class SearchController {

	@RequestMapping(value="/ResultSearch", method = RequestMethod.GET)
	public String handleSearch(@RequestParam("mapFrom") String input1, 
    		@RequestParam("mapTo") String input2, @RequestParam("date") String datr, Model model){
		
		//if(input1.matches("[A-z0-9]") && input2.matches("[A-z0-9]")){		
			List<Lift> result;
			LiftMapper mapper=new LiftMapper();
			
			Lift l=new Lift();
			LiftPoint lp=new LiftPoint();
			lp.setCity("Roma");
			LiftPoint lp2=new LiftPoint();
			lp2.setCity("Milano");
			LiftPointMapper lpm=new LiftPointMapper();
			long idLp=lpm.insert(lp);
			long idLp2=lpm.insert(lp2);
			l.setPickUpPoint(lp);
			l.setDropOffPoint(lp2);
			l.setCost(2);
			l.setnSeat(2);
			l.setPossibleDetour(true);
			LiftMapper lm=new LiftMapper();
			lm.insert(l);
			
			result=mapper.findFromTo(input1, input2);
			model.addAttribute("result",result);
		//}
			return "resultSearch";			
	}
}
