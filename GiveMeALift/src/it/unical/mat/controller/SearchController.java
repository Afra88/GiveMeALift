package it.unical.mat.controller;

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
		model.addAttribute("out",datr);
		return "resultSearch";
	}
}
