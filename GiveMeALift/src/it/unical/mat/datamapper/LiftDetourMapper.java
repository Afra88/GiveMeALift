package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;

public class LiftDetourMapper extends AbstractMapper{

	
	
	public List<LiftDetour> findDetourFromPickUpAndDropOffPoints(String pPoint, String dPoint){
		
		List<LiftDetour> res = new LinkedList<LiftDetour>();
		
		String findStatement = "from LiftDetour, LiftPoint"
				+ " where " 			
				+ "(pickUpPoint.city like :par1 "
				+ "or pickUpPoint.street like :par1 "
				+ "or pickUpPoint.state like :par1 "
				+ "or pickUpPoint.province like :par1 "
				+ "or pickUpPoint.region like :par1) "
				+ "and (dropOffPoint.city like :par2 "
				+ "or dropOffPoint.street like :par2 "
				+ "or dropOffPoint.state like :par2 "
				+ "or dropOffPoint.province like :par2 "
				+ "or dropOffPoint.region like :par2)"
				+ "and LiftDetour.pickUpPoint.id like LiftPoint.id"
				+ "and LiftDetour.dropOffPoint.id like LiftPoint.id"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", pPoint);
		parameters.put("par2", dPoint);
		
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			res.add((LiftDetour) object);
		}		

		return res;
		
	}
}
