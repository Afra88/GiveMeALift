package it.unical.mat.service;

import it.unical.mat.domain.Lift;

import java.util.LinkedList;
import java.util.List;

public class DetoursCostConverterFacade {
	
	public String createStringCost(List<Integer> costs, Lift l){
		String detoursCost=l.getDetoursCost();
		for (Integer integer : costs) {
			detoursCost+=integer;
		}
		l.setDetoursCost(detoursCost);
		return detoursCost;
	}
	
	public List<Integer> getListDetourCosts(Lift l){
		String costs[]=l.getDetoursCost().split(";");
		List<Integer> integerCosts=new LinkedList<Integer>();
		for (String string : costs) {
			integerCosts.add(Integer.parseInt(string));
		}
		return integerCosts;
	}

}
